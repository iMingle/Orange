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

/**
 * The {@code TopologicalX} class represents a data type for
 * determining a topological order of a directed acyclic graph (DAG).
 * Recall, a digraph has a topological order if and only if it is a DAG.
 * The <em>hasOrder</em> operation determines whether the digraph has
 * a topological order, and if so, the <em>order</em> operation
 * returns one.
 * <p>
 * This implementation uses a nonrecursive, queue-based algorithm.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 * Afterwards, the <em>hasOrder</em> and <em>rank</em> operations takes constant time;
 * the <em>order</em> operation takes time proportional to <em>V</em>.
 * <p>
 * See {@link DirectedCycle}, {@link DirectedCycleX}, and
 * {@link EdgeWeightedDirectedCycle} to compute a
 * directed cycle if the digraph is not a DAG.
 * See {@link Topological} for a recursive version that uses depth-first search.
 * <p>
 *
 * @author mingle
 */
public class TopologicalX {
    private Queue<Integer> order;    // vertices in topological order
    private final int[] ranks;        // ranks[v] = order where vertex v appers in order

    /**
     * Determines whether the digraph {@code graph} has a topological order and, if so,
     * finds such a topological order.
     *
     * @param graph the digraph
     */
    public TopologicalX(Digraph graph) {
        // indegrees of remaining vertices
        int[] indegree = new int[graph.vertex()];
        for (int v = 0; v < graph.vertex(); v++) {
            indegree[v] = graph.indegree(v);
        }

        // initialize
        ranks = new int[graph.vertex()];
        order = new Queue<>();
        int count = 0;

        // initialize queue to contain all vertices with indegree = 0
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < graph.vertex(); v++)
            if (indegree[v] == 0) queue.enqueue(v);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            order.enqueue(v);
            ranks[v] = count++;
            for (int w : graph.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        if (count != graph.vertex()) {
            order = null;
        }

        assert check(graph);
    }

    /**
     * Determines whether the edge-weighted digraph {@code graph} has a
     * topological order and, if so, finds such a topological order.
     *
     * @param graph the digraph
     */
    public TopologicalX(EdgeWeightedDigraph graph) {
        // indegrees of remaining vertices
        int[] indegree = new int[graph.vertex()];
        for (int v = 0; v < graph.vertex(); v++) {
            indegree[v] = graph.indegree(v);
        }

        // initialize
        ranks = new int[graph.vertex()];
        order = new Queue<>();
        int count = 0;

        // initialize queue to contain all vertices with indegree = 0
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < graph.vertex(); v++)
            if (indegree[v] == 0) queue.enqueue(v);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            order.enqueue(v);
            ranks[v] = count++;
            for (DirectedEdge e : graph.adj(v)) {
                int w = e.to();
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        if (count != graph.vertex()) {
            order = null;
        }

        assert check(graph);
    }

    /**
     * Returns a topological order if the digraph has a topologial order,
     * and {@code null} otherwise.
     *
     * @return a topological order of the vertices (as an interable) if the
     * digraph has a topological order (or equivalently, if the digraph is a DAG),
     * and {@code null} otherwise
     */
    public Iterable<Integer> order() {
        return order;
    }

    /**
     * Does the digraph have a topological order?
     *
     * @return {@code true} if the digraph has a topological order (or equivalently,
     * if the digraph is a DAG), and {@code false} otherwise
     */
    public boolean hasOrder() {
        return order != null;
    }

    /**
     * The the rank of vertex {@code v} in the topological order;
     * -1 if the digraph is not a DAG
     *
     * @param v vertex
     * @return the position of vertex {@code v} in a topological order
     * of the digraph; -1 if the digraph is not a DAG
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int rank(int v) {
        validateVertex(v);
        if (hasOrder()) return ranks[v];
        else return -1;
    }

    // certify that digraph is acyclic
    private boolean check(Digraph G) {
        // digraph is acyclic
        if (hasOrder()) {
            // check that ranks are a permutation of 0 to V-1
            boolean[] found = new boolean[G.vertex()];
            for (int i = 0; i < G.vertex(); i++) {
                found[rank(i)] = true;
            }
            for (int i = 0; i < G.vertex(); i++) {
                if (!found[i]) {
                    System.err.println("No vertex with rank " + i);
                    return false;
                }
            }

            // check that ranks provide a valid topological order
            for (int v = 0; v < G.vertex(); v++) {
                for (int w : G.adj(v)) {
                    if (rank(v) > rank(w)) {
                        System.err.printf("%d-%d: rank(%d) = %d, rank(%d) = %d\n",
                                v, w, v, rank(v), w, rank(w));
                        return false;
                    }
                }
            }

            // check that order() is consistent with rank()
            int r = 0;
            for (int v : order()) {
                if (rank(v) != r) {
                    System.err.println("order() and rank() inconsistent");
                    return false;
                }
                r++;
            }
        }

        return true;
    }

    // certify that digraph is acyclic
    private boolean check(EdgeWeightedDigraph G) {
        // digraph is acyclic
        if (hasOrder()) {
            // check that ranks are a permutation of 0 to V-1
            boolean[] found = new boolean[G.vertex()];
            for (int i = 0; i < G.vertex(); i++) {
                found[rank(i)] = true;
            }
            for (int i = 0; i < G.vertex(); i++) {
                if (!found[i]) {
                    System.err.println("No vertex with rank " + i);
                    return false;
                }
            }

            // check that ranks provide a valid topological order
            for (int v = 0; v < G.vertex(); v++) {
                for (DirectedEdge e : G.adj(v)) {
                    int w = e.to();
                    if (rank(v) > rank(w)) {
                        System.err.printf("%d-%d: rank(%d) = %d, rank(%d) = %d\n",
                                v, w, v, rank(v), w, rank(w));
                        return false;
                    }
                }
            }

            // check that order() is consistent with rank()
            int r = 0;
            for (int v : order()) {
                if (rank(v) != r) {
                    System.err.println("order() and rank() inconsistent");
                    return false;
                }
                r++;
            }
        }

        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = ranks.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}
