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
 * The {@code Edge} class represents a weighted edge in an
 * {@link EdgeWeightedGraph}. Each edge consists of two integers
 * (naming the two vertices) and a real-value weight. The data type
 * provides methods for accessing the two endpoints of the edge and
 * the weight. The natural order for this data type is by
 * ascending order of weight.
 * <p>
 *
 * @author mingle
 */
public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    /**
     * Initializes an edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param v      one vertex
     * @param w      the other vertex
     * @param weight the weight of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *                                  is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *                                  endpoints of this edge
     */
    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    /**
     * Compares two edges by weight.
     * Note that {@code compareTo()} is not consistent with {@code equals()},
     * which uses the reference equality implementation inherited from {@code Object}.
     *
     * @param that the other edge
     * @return a negative integer, zero, or positive integer depending on whether
     * the weight of this is less than, equal to, or greater than the
     * argument edge
     */
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
