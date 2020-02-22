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
 * The {@code DijkstraAllPairsSP} class represents a data type for solving the
 * all-pairs shortest paths problem in edge-weighted digraphs
 * where the edge weights are nonnegative.
 * <p>
 * This implementation runs Dijkstra's algorithm from each vertex.
 * The constructor takes time proportional to <em>V</em> (<em>E</em> log <em>V</em>)
 * and uses space proprtional to <em>V</em><sup>2</sup>,
 * where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 * Afterwards, the {@code dist()} and {@code hasPath()} methods take
 * constant time and the {@code path()} method takes time proportional to the
 * number of edges in the shortest path returned.
 * <p>
 *
 * @author mingle
 */
public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    /**
     * Computes a shortest paths tree from each vertex to every other vertex in
     * the edge-weighted digraph {@code G}.
     *
     * @param G the edge-weighted digraph
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++)
            all[v] = new DijkstraSP(G, v);
    }

    /**
     * Returns a shortest path from vertex {@code s} to vertex {@code t}.
     *
     * @param s the source vertex
     * @param t the destination vertex
     * @return a shortest path from vertex {@code s} to vertex {@code t}
     * as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     * @throws IllegalArgumentException unless {@code 0 <= t < V}
     */
    public Iterable<DirectedEdge> path(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return all[s].pathTo(t);
    }

    /**
     * Is there a path from the vertex {@code s} to vertex {@code t}?
     *
     * @param s the source vertex
     * @param t the destination vertex
     * @return {@code true} if there is a path from vertex {@code s}
     * to vertex {@code t}, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     * @throws IllegalArgumentException unless {@code 0 <= t < V}
     */
    public boolean hasPath(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return dist(s, t) < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns the length of a shortest path from vertex {@code s} to vertex {@code t}.
     *
     * @param s the source vertex
     * @param t the destination vertex
     * @return the length of a shortest path from vertex {@code s} to vertex {@code t};
     * {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     * @throws IllegalArgumentException unless {@code 0 <= t < V}
     */
    public double dist(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return all[s].distTo(t);
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = all.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}
