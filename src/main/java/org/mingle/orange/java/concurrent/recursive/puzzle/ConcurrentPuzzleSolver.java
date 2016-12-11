/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mingle.orange.java.concurrent.recursive.puzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并行解决方案
 * 
 * @author mingle
 */
public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentHashMap<P, Boolean> seen;
    private final AtomicInteger taskCount = new AtomicInteger(0);
    final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();
    
    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
        this.exec = initThreadPool();
        this.seen = new ConcurrentHashMap<P, Boolean>();
        if (exec instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor tpe = (ThreadPoolExecutor) exec;
            tpe.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        }
    }
    
    private ExecutorService initThreadPool() {
        return Executors.newCachedThreadPool();
    }
    
    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            // 阻塞直到找到解答
            Node<P, M> solnNode = solution.getValue();
            return solnNode == null ? null : solnNode.asMoveList();
        } finally {
            exec.shutdown();
        }
    }
    
    protected Runnable newTask(P p, M m, Node<P, M> n) {
        return new SolverTask(p, m, n);
    }

    class SolverTask extends Node<P, M> implements Runnable {

        public SolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                if (solution.isSet() && seen.putIfAbsent(pos, true) != null)
                    return;    // 已经找到答案或者已经遍历了这个位置
                if (puzzle.isGoal(pos))
                    solution.setValue(this);
                else
                    for (M m : puzzle.legalMoves(pos))
                        exec.execute(newTask(puzzle.move(pos, m), m, this));
            } finally {
                if (taskCount.decrementAndGet() == 0)
                    solution.setValue(null);
            }
        }
        
    }
    
    static class Node<P, M> {
        /**
         * prev -> move -> pos
         */
        final P pos;
        final M move;
        final Node<P, M> prev;
        
        public Node(P pos, M move, Node<P, M> prev) {
            this.pos = pos;
            this.move = move;
            this.prev = prev;
        }
        
        public List<M> asMoveList() {
            List<M> solution = new LinkedList<>();
            for (Node<P, M> n = this; n.move != null; n = n.prev)
                solution.add(n.move);
            
            return solution;
        }
    }
}
