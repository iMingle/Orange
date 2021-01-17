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

import org.orange.arithmetic.base.bag.Bag;
import org.orange.arithmetic.base.stack.Stack;
import org.orange.arithmetic.util.RandomUtil;

/**
 * The {@code EdgeWeightedGraph} class represents an edge-weighted
 * graph of vertices named 0 through <em>V</em> - 1, where each
 * undirected edge is of type {@link Edge} and has a real-valued weight.
 * It supports the following two primary operations: add an edge to the graph,
 * iterate over all of the edges incident to a vertex. It also provides
 * methods for returning the number of vertices <em>V</em> and the number
 * of edges <em>E</em>. Parallel edges and self-loops are permitted.
 * <p>
 * This implementation uses an adjacency-lists representation, which
 * is a vertex-indexed array of @link{Bag} objects.
 * All operations take constant time (in the worst case) except
 * iterating over the edges incident to a given vertex, which takes
 * time proportional to the number of such edges.
 * <p>
 *
 * @author mingle
 */
public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int vertex;
    private int edge;
    private final Bag<Edge>[] adjacency;

    /**
     * Initializes an empty edge-weighted graph with {@code V} vertices and 0 edges.
     *
     * @param vertex the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    @SuppressWarnings("unchecked") public EdgeWeightedGraph(int vertex) {
        if (vertex < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.vertex = vertex;
        this.edge = 0;
        adjacency = (Bag<Edge>[]) new Bag[vertex];
        for (int v = 0; v < vertex; v++) {
            adjacency[v] = new Bag<>();
        }
    }

    /**
     * Initializes a random edge-weighted graph with {@code V} vertices and <em>E</em> edges.
     *
     * @param vertex the number of vertices
     * @param edge   the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public EdgeWeightedGraph(int vertex, int edge) {
        this(vertex);
        if (edge < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < edge; i++) {
            int v = RandomUtil.uniform(vertex);
            int w = RandomUtil.uniform(vertex);
            double weight = Math.round(100 * RandomUtil.uniform()) / 100.0;
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    /**
     * Initializes a new edge-weighted graph that is a deep copy of {@code graph}.
     *
     * @param graph the edge-weighted graph to copy
     */
    public EdgeWeightedGraph(EdgeWeightedGraph graph) {
        this(graph.vertex());
        this.edge = graph.edge();
        for (int v = 0; v < graph.vertex(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Edge> reverse = new Stack<>();
            for (Edge e : graph.adjacency[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adjacency[v].add(e);
            }
        }
    }

    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int vertex() {
        return vertex;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int edge() {
        return edge;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= vertex)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertex - 1));
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param e the edge
     * @throws IllegalArgumentException unless both endpoints are between {@code 0} and {@code V-1}
     */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adjacency[v].add(e);
        adjacency[w].add(e);
        edge++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adjacency[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
        validateVertex(v);
        return adjacency[v].size();
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph, use foreach notation:
     * {@code for (Edge e : G.edges())}.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<>();
        for (int v = 0; v < vertex; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // only add one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     * followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertex).append(" ").append(edge).append(NEWLINE);
        for (int v = 0; v < vertex; v++) {
            s.append(v).append(": ");
            for (Edge e : adjacency[v]) {
                s.append(e).append("  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
