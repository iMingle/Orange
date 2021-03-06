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

package org.orange.arithmetic.graph;

import org.orange.arithmetic.base.stack.Stack;

/**
 * The {@code DirectedCycle} class represents a data type for
 * determining whether a digraph has a directed cycle.
 * The <em>hasCycle</em> operation determines whether the digraph has
 * a directed cycle and, and of so, the <em>cycle</em> operation
 * returns one.
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 * Afterwards, the <em>hasCycle</em> operation takes constant time;
 * the <em>cycle</em> operation takes time proportional
 * to the length of the cycle.
 * <p>
 * See {@link Topological} to compute a topological order if the
 * digraph is acyclic.
 * <p>
 *
 * @author mingle
 */
public class DirectedCycle {
    private final boolean[] marked;        // marked[v] = has vertex v been marked?
    private final int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
    private final boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<Integer> cycle;         // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph {@code graph} has a directed cycle and, if so,
     * finds such a cycle.
     *
     * @param graph the digraph
     */
    public DirectedCycle(Digraph graph) {
        marked = new boolean[graph.vertex()];
        onStack = new boolean[graph.vertex()];
        edgeTo = new int[graph.vertex()];
        for (int v = 0; v < graph.vertex(); v++)
            if (!marked[v] && cycle == null) dfs(graph, v);
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(Digraph graph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : graph.adj(v)) {
            // short circuit if directed cycle found
            if (cycle != null) return;

                // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Does the digraph have a directed cycle?
     *
     * @return {@code true} if the digraph has a directed cycle, {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and {@code null} otherwise.
     *
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     * and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    // certify that digraph has a directed cycle if it reports one
    @SuppressWarnings("Duplicates") private boolean check() {
        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(3);
        digraph.addEdge(0, 1);
        digraph.addEdge(1, 2);
        digraph.addEdge(2, 0);
        DirectedCycle cycle = new DirectedCycle(digraph);
        System.out.println(cycle.hasCycle());
    }
}
