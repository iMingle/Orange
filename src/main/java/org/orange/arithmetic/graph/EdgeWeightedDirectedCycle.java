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
 * The {@code EdgeWeightedDirectedCycle} class represents a data type for
 * determining whether an edge-weighted digraph has a directed cycle.
 * The <em>hasCycle</em> operation determines whether the edge-weighted
 * digraph has a directed cycle and, if so, the <em>cycle</em> operation
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
 * See {@link Topological} to compute a topological order if the edge-weighted
 * digraph is acyclic.
 * <p>
 *
 * @author mingle
 */
public class EdgeWeightedDirectedCycle {
    private final boolean[] marked;             // marked[v] = has vertex v been marked?
    private final DirectedEdge[] edgeTo;        // edgeTo[v] = previous edge on path to v
    private final boolean[] onStack;            // onStack[v] = is vertex on the stack?
    private Stack<DirectedEdge> cycle;         // directed cycle (or null if no such cycle)

    /**
     * Determines whether the edge-weighted digraph {@code graph} has a directed cycle and,
     * if so, finds such a cycle.
     *
     * @param graph the edge-weighted digraph
     */
    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph graph) {
        marked = new boolean[graph.vertex()];
        onStack = new boolean[graph.vertex()];
        edgeTo = new DirectedEdge[graph.vertex()];
        for (int v = 0; v < graph.vertex(); v++)
            if (!marked[v]) dfs(graph, v);

        // check that digraph has a cycle
        assert check();
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(EdgeWeightedDigraph graph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();

            // short circuit if directed cycle found
            if (cycle != null) return;

                // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(graph, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);

                return;
            }
        }

        onStack[v] = false;
    }

    /**
     * Does the edge-weighted digraph have a directed cycle?
     *
     * @return {@code true} if the edge-weighted digraph has a directed cycle,
     * {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the edge-weighted digraph has a directed cycle,
     * and {@code null} otherwise.
     *
     * @return a directed cycle (as an iterable) if the edge-weighted digraph
     * has a directed cycle, and {@code null} otherwise
     */
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    // certify that digraph is either acyclic or has a directed cycle
    private boolean check() {

        // edge-weighted digraph is cyclic
        if (hasCycle()) {
            // verify cycle
            DirectedEdge first = null, last = null;
            for (DirectedEdge e : cycle()) {
                if (first == null) first = e;
                if (last != null) {
                    if (last.to() != e.from()) {
                        System.err.printf("cycle edges %s and %s not incident\n", last, e);
                        return false;
                    }
                }
                last = e;
            }

            if (last.to() != first.from()) {
                System.err.printf("cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }

        return true;
    }
}
