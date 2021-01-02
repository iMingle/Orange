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
import org.orange.arithmetic.base.bag.Bag;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * The {@code Graph} class represents an undirected graph of vertices
 * named 0 through <em>V</em> - 1.
 * It supports the following two primary operations: add an edge to the graph,
 * iterate over all of the vertices adjacent to a vertex. It also provides
 * methods for returning the number of vertices <em>V</em> and the number
 * of edges <em>E</em>. Parallel edges and self-loops are permitted.
 * By convention, a self-loop <em>v</em>-<em>v</em> appears in the
 * adjacency list of <em>v</em> twice and contributes two to the degree
 * of <em>v</em>.
 * <p>
 * This implementation uses an adjacency-lists representation, which
 * is a vertex-indexed array of {@link Bag} objects.
 * All operations take constant time (in the worst case) except
 * iterating over the vertices adjacent to a given vertex, which takes
 * time proportional to the number of such vertices.
 * <p>
 *
 * @author mingle
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int vertex;
    private int edge;

    /**
     * 邻接表
     */
    private final Bag<Integer>[] adjacency;

    /**
     * 是否找到节点
     */
    private boolean found;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param vertex number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    @SuppressWarnings("unchecked")
    public Graph(int vertex) {
        if (vertex < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.vertex = vertex;
        this.edge = 0;
        adjacency = (Bag<Integer>[]) new Bag[vertex];
        for (int v = 0; v < vertex; v++) {
            adjacency[v] = new Bag<>();
        }
    }

    /**
     * Initializes a graph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    @SuppressWarnings("unchecked")
    public Graph(DataInputStream in) throws IOException {
        try {
            this.vertex = in.readInt();
            if (vertex < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adjacency = (Bag<Integer>[]) new Bag[vertex];
            for (int v = 0; v < vertex; v++) {
                adjacency[v] = new Bag<>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    /**
     * Initializes a new graph that is a deep copy of {@code graph}.
     *
     * @param graph the graph to copy
     */
    public Graph(Graph graph) {
        this(graph.vertex());
        this.edge = graph.edge();
        for (int v = 0; v < graph.vertex(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<>();
            for (int w : graph.adjacency[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adjacency[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int vertex() {
        return vertex;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
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
     * Adds the undirected edge v-w to this graph.
     *
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        edge++;
        adjacency[v].add(w);
        adjacency[w].add(v);
    }

    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
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
     * 广度优先
     *
     * @param s source vertex
     * @param t target vertex
     */
    public void bfs(int s, int t) {
        if (s == t) return;
        // visited是用来记录已经被访问的顶点，用来避免顶点被重复访问。
        boolean[] visited = new boolean[vertex];
        visited[s] = true;
        // queue是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点。
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // prev用来记录搜索路径。
        int[] prev = new int[vertex];
        for (int i = 0; i < vertex; ++i) {
            prev[i] = -1;
        }

        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int q : adj(w)) {
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }

                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    /**
     * 深度优先
     *
     * @param s source vertex
     * @param t target vertex
     */
    public void dfs(int s, int t) {
        found = false;
        // visited是用来记录已经被访问的顶点，用来避免顶点被重复访问。
        boolean[] visited = new boolean[vertex];
        visited[s] = true;
        // prev用来记录搜索路径。
        int[] prev = new int[vertex];
        for (int i = 0; i < vertex; ++i) {
            prev[i] = -1;
        }

        recursiveDfs(s, t, visited, prev);

        print(prev, s, t);
    }

    private void recursiveDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }

        for (int q : adj(w)) {
            if (!visited[q]) {
                prev[q] = w;
                recursiveDfs(q, t, visited, prev);
            }
        }
    }

    // 递归打印 s->t 的路径
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     * followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertex).append(" vertices, ").append(edge).append(" edges ").append(NEWLINE);
        for (int v = 0; v < vertex; v++) {
            s.append(v).append(": ");
            for (int w : adjacency[v]) {
                s.append(w).append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        System.out.println(graph);

        graph.bfs(0, 6);
        System.out.println();
        graph.dfs(0, 6);
    }
}
