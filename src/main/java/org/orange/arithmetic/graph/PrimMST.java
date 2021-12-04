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
import org.orange.arithmetic.sort.priorityQueue.IndexMinPQ;
import org.orange.arithmetic.unionfind.UF;

/**
 * The {@code PrimMST} class represents a data type for computing a
 * <em>minimum spanning tree</em> in an edge-weighted graph.
 * The edge weights can be positive, zero, or negative and need not
 * be distinct. If the graph is not connected, it computes a <em>minimum
 * spanning forest</em>, which is the union of minimum spanning trees
 * in each connected component. The {@code weight()} method returns the
 * weight of a minimum spanning tree and the {@code edges()} method
 * returns its edges.
 * <p>
 * This implementation uses <em>Prim's algorithm</em> with an indexed
 * binary heap.
 * The constructor takes &Theta;(<em>E</em> log <em>V</em>) time in
 * the worst case, where <em>V</em> is the number of
 * vertices and <em>E</em> is the number of edges.
 * Each instance method takes &Theta;(1) time.
 * It uses &Theta;(<em>V</em>) extra space (not including the
 * edge-weighted graph).
 * <p>
 * This {@code weight()} method correctly computes the weight of the MST
 * if all arithmetic performed is without floating-point rounding error
 * or arithmetic overflow.
 * This is the case if all edge weights are non-negative integers
 * and the weight of the MST does not exceed 2<sup>52</sup>.
 * <p>
 * For alternate implementations, see {@link LazyPrimMST}, {@link KruskalMST},
 * and {@link BoruvkaMST}.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class PrimMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private final Edge[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
    private final double[] distTo;      // distTo[v] = weight of shortest such edge
    private final boolean[] marked;     // marked[v] = true if v on tree, false otherwise
    private final IndexMinPQ<Double> pq;

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     *
     * @param G the edge-weighted graph
     */
    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.vertex()];
        distTo = new double[G.vertex()];
        marked = new boolean[G.vertex()];
        pq = new IndexMinPQ<>(G.vertex());
        for (int v = 0; v < G.vertex(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.vertex(); v++)      // run from each vertex to find
            if (!marked[v]) prim(G, v);      // minimum spanning forest

        // check optimality conditions
        assert check(G);
    }

    // run Prim's algorithm in graph G, starting from vertex s
    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    // scan vertex v
    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;         // v-w is obsolete edge
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     *
     * @return the edges in a minimum spanning tree (or forest) as
     * an iterable of edges
     */
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     *
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }

    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph G) {
        // check weight
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(G.vertex());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {
            // all edges in MST except e
            uf = new UF(G.vertex());
            for (Edge f : edges()) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(9);
        graph.addEdge(new Edge(4, 7, 1));
        graph.addEdge(new Edge(4, 5, 18));
        graph.addEdge(new Edge(3, 6, 14));
        graph.addEdge(new Edge(2, 3, 12));
        graph.addEdge(new Edge(3, 8, 11));
        graph.addEdge(new Edge(3, 4, 10));
        graph.addEdge(new Edge(6, 7, 9));
        graph.addEdge(new Edge(1, 2, 8));
        graph.addEdge(new Edge(5, 6, 7));
        graph.addEdge(new Edge(1, 6, 6));
        graph.addEdge(new Edge(3, 7, 6));
        graph.addEdge(new Edge(1, 8, 5));
        graph.addEdge(new Edge(0, 5, 4));
        graph.addEdge(new Edge(0, 1, 3));
        graph.addEdge(new Edge(2, 8, 2));

        PrimMST mst = new PrimMST(graph);
        // 36.0
        System.out.println(mst.weight());
    }
}
