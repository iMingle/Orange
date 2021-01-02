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
 * The {@code DepthFirstOrder} class represents a data type for
 * determining depth-first search ordering of the vertices in a digraph
 * or edge-weighted digraph, including preorder, postorder, and reverse postorder.
 * <p>
 * This implementation uses depth-first search.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>
 * (in the worst case),
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 * Afterwards, the <em>preorder</em>, <em>postorder</em>, and <em>reverse postorder</em>
 * operation takes take time proportional to <em>V</em>.
 * <p>
 *
 * @author mingle
 */
public class DepthFirstOrder {
    private final boolean[] marked;          // marked[v] = has v been marked in dfs?
    private final int[] pre;                 // pre[v]    = preorder number of v
    private final int[] post;                // post[v]   = postorder number of v
    private final Queue<Integer> preorder;   // vertices in preorder
    private final Queue<Integer> postorder;  // vertices in postorder
    private int preCounter;                 // counter or preorder numbering
    private int postCounter;                // counter for postorder numbering

    /**
     * Determines a depth-first order for the digraph {@code graph}.
     *
     * @param graph the digraph
     */
    public DepthFirstOrder(Digraph graph) {
        pre = new int[graph.vertex()];
        post = new int[graph.vertex()];
        postorder = new Queue<>();
        preorder = new Queue<>();
        marked = new boolean[graph.vertex()];
        for (int v = 0; v < graph.vertex(); v++)
            if (!marked[v]) dfs(graph, v);

        assert check();
    }

    /**
     * Determines a depth-first order for the edge-weighted digraph {@code graph}.
     *
     * @param graph the edge-weighted digraph
     */
    public DepthFirstOrder(EdgeWeightedDigraph graph) {
        pre = new int[graph.vertex()];
        post = new int[graph.vertex()];
        postorder = new Queue<>();
        preorder = new Queue<>();
        marked = new boolean[graph.vertex()];
        for (int v = 0; v < graph.vertex(); v++)
            if (!marked[v]) dfs(graph, v);
    }

    // run DFS in digraph G from vertex v and compute preorder/postorder
    private void dfs(Digraph graph, int v) {
        marked[v] = true;
        pre[v] = preCounter++;
        preorder.enqueue(v);
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
        postorder.enqueue(v);
        post[v] = postCounter++;
    }

    // run DFS in edge-weighted digraph graph from vertex v and compute preorder/postorder
    private void dfs(EdgeWeightedDigraph graph, int v) {
        marked[v] = true;
        pre[v] = preCounter++;
        preorder.enqueue(v);
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
        postorder.enqueue(v);
        post[v] = postCounter++;
    }

    /**
     * Returns the preorder number of vertex {@code v}.
     *
     * @param v the vertex
     * @return the preorder number of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int pre(int v) {
        validateVertex(v);
        return pre[v];
    }

    /**
     * Returns the postorder number of vertex {@code v}.
     *
     * @param v the vertex
     * @return the postorder number of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    private int post(int v) {
        validateVertex(v);
        return post[v];
    }

    /**
     * Returns the vertices in postorder.
     *
     * @return the vertices in postorder, as an iterable of vertices
     */
    private Iterable<Integer> post() {
        return postorder;
    }

    /**
     * Returns the vertices in preorder.
     *
     * @return the vertices in preorder, as an iterable of vertices
     */
    public Iterable<Integer> pre() {
        return preorder;
    }

    /**
     * Returns the vertices in reverse postorder.
     *
     * @return the vertices in reverse postorder, as an iterable of vertices
     */
    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<>();
        for (int v : postorder)
            reverse.push(v);
        return reverse;
    }

    // check that pre() and post() are consistent with pre(v) and post(v)
    private boolean check() {
        // check that post(v) is consistent with post()
        int r = 0;
        for (int v : post()) {
            if (post(v) != r) {
                System.out.println("post(v) and post() inconsistent");
                return false;
            }
            r++;
        }

        // check that pre(v) is consistent with pre()
        r = 0;
        for (int v : pre()) {
            if (pre(v) != r) {
                System.out.println("pre(v) and pre() inconsistent");
                return false;
            }
            r++;
        }

        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}
