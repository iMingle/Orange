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

import org.orange.arithmetic.base.queue.Queue;
import org.orange.arithmetic.base.stack.Stack;

/**
 *  The {@code DirectedCycleX} class represents a data type for
 *  determining whether a digraph has a directed cycle.
 *  The <em>hasCycle</em> operation determines whether the digraph has
 *  a directed cycle and, and of so, the <em>cycle</em> operation
 *  returns one.
 *  <p>
 *  This implementation uses a nonrecursive, queue-based algorithm.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Afterwards, the <em>hasCycle</em> operation takes constant time;
 *  the <em>cycle</em> operation takes time proportional
 *  to the length of the cycle.
 *  <p>
 *  See {@link DirectedCycle} for a recursive version that uses depth-first search.
 *  See {@link Topological} or {@link TopologicalX} to compute a topological order
 *  when the digraph is acyclic.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class DirectedCycleX {
    private Stack<Integer> cycle;     // the directed cycle; null if digraph is acyclic

    public DirectedCycleX(Digraph G) {

        // indegrees of remaining vertices
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }

        // initialize queue to contain all vertices with indegree = 0
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < G.V(); v++)
            if (indegree[v] == 0) queue.enqueue(v);

        while(!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        int[] edgeTo = new int[G.V()];
        int root = -1;  // any vertex with indegree >= -1
        for (int v = 0; v < G.V(); v++) {
            if (indegree[v] == 0) continue;
            else root = v;
            for (int w : G.adj(v)) {
                if (indegree[w] > 0) {
                    edgeTo[w] = v;
                }
            }
        }

        if (root != -1) {

            // find any vertex on cycle
            boolean[] visited = new boolean[G.V()];
            while (!visited[root]) {
                visited[root] = true;
                root = edgeTo[root];
            }

            // extract cycle
            cycle = new Stack<Integer>();
            int v = root;
            do {
                cycle.push(v);
                v = edgeTo[v];
            } while (v != root);
            cycle.push(root);
        }

        assert check();
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and {@code null} otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     *    and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    /**
     * Does the digraph have a directed cycle?
     * @return {@code true} if the digraph has a directed cycle, {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    // certify that digraph has a directed cycle if it reports one
    private boolean check() {

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
}
