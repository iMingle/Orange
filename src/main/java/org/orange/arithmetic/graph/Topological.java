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

/**
 * The {@code Topological} class represents a data type for
 * determining a topological order of a directed acyclic graph (DAG).
 * Recall, a digraph has a topological order if and only if it is a DAG.
 * The <em>hasOrder</em> operation determines whether the digraph has
 * a topological order, and if so, the <em>order</em> operation
 * returns one.
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 * Afterwards, the <em>hasOrder</em> and <em>rank</em> operations takes constant time;
 * the <em>order</em> operation takes time proportional to <em>V</em>.
 * <p>
 * See {@link DirectedCycle}, {@link DirectedCycleX}, and
 * {@link EdgeWeightedDirectedCycle} to compute a
 * directed cycle if the digraph is not a DAG.
 * See {@link TopologicalX} for a nonrecursive queue-based algorithm
 * to compute a topological order of a DAG.
 * <p>
 *
 * @author mingle
 */
public class Topological {
    private Iterable<Integer> order;  // topological order
    private int[] rank;               // rank[v] = position of vertex v in topological order

    public static void main(String[] args) {
        Digraph digraph = new Digraph(13);
        digraph.addEdge(0, 1).addEdge(0, 5).addEdge(0, 6).addEdge(2, 0)
                .addEdge(2, 3).addEdge(3, 5).addEdge(5, 4)
                .addEdge(6, 4).addEdge(6, 9).addEdge(7, 6).addEdge(8, 7)
                .addEdge(9, 10).addEdge(9, 11).addEdge(9, 12).addEdge(11, 12);
        Topological topological = new Topological(digraph);
        for (Integer o : topological.order())
            System.out.println(o);
    }

    /**
     * Determines whether the digraph {@code G} has a topological order and, if so finds such a topological order.
     *
     * @param G the digraph
     */
    public Topological(Digraph G) {
        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
            rank = new int[G.V()];
            int i = 0;
            for (int v : order)
                rank[v] = i++;
        }
    }

    /**
     * Determines whether the edge-weighted digraph {@code G} has a topological
     * order and, if so, finds such an order.
     *
     * @param G the edge-weighted digraph
     */
    public Topological(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
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
     * @param v the vertex
     * @return the position of vertex {@code v} in a topological order
     * of the digraph; -1 if the digraph is not a DAG
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int rank(int v) {
        validateVertex(v);
        if (hasOrder()) return rank[v];
        else return -1;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = rank.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}
