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
 * The {@code AcyclicLP} class represents a data type for solving the
 * single-source longest paths problem in edge-weighted directed
 * acyclic graphs (DAGs). The edge weights can be positive, negative, or zero.
 * <p>
 * This implementation uses a topological-sort based algorithm.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>,
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 * Afterwards, the {@code distTo()} and {@code hasPathTo()} methods take
 * constant time and the {@code pathTo()} method takes time proportional to the
 * number of edges in the longest path returned.
 * <p>
 *
 * @author mingle
 */
public class AcyclicLP {
    private final double[] distTo;          // distTo[v] = distance  of longest s->v path
    private final DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on longest s->v path

    /**
     * Computes a longest paths tree from {@code s} to every other vertex in
     * the directed acyclic graph {@code G}.
     *
     * @param G the acyclic digraph
     * @param s the source vertex
     * @throws IllegalArgumentException if the digraph is not acyclic
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.vertex()];
        edgeTo = new DirectedEdge[G.vertex()];

        validateVertex(s);

        for (int v = 0; v < G.vertex(); v++)
            distTo[v] = Double.NEGATIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in toplogical order
        Topological topological = new Topological(G);
        if (!topological.hasOrder())
            throw new IllegalArgumentException("Digraph is not acyclic.");
        for (int v : topological.order()) {
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
    }

    // relax edge e, but update if you find a *longer* path
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] < distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    /**
     * Returns the length of a longest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param v the destination vertex
     * @return the length of a longest path from the source vertex {@code s} to vertex {@code v};
     * {@code Double.NEGATIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Is there a path from the source vertex {@code s} to vertex {@code v}?
     *
     * @param v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     * {@code s} to vertex {@code v}, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    private boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }

    /**
     * Returns a longest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param v the destination vertex
     * @return a longest path from the source vertex {@code s} to vertex {@code v}
     * as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}
