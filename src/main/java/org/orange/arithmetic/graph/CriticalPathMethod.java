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

import java.util.Random;

/**
 * @author mingle
 */
public class CriticalPathMethod {

    private CriticalPathMethod() {
    }

    /**
     * Reads the precedence constraints from standard input
     * and prints a feasible schedule to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Random random = new Random();
        // number of jobs
        int n = 100;

        // source and sink
        int source = 2 * n;
        int sink = 2 * n + 1;

        // build network
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * n + 2);
        for (int i = 0; i < n; i++) {
            double duration = random.nextDouble();
            G.addEdge(new DirectedEdge(source, i, 0.0));
            G.addEdge(new DirectedEdge(i + n, sink, 0.0));
            G.addEdge(new DirectedEdge(i, i + n, duration));

            // precedence constraints
            int m = 10;
            for (int j = 0; j < m; j++) {
                int precedent = random.nextInt(100);
                G.addEdge(new DirectedEdge(n + i, precedent, 0.0));
            }
        }

        // compute longest path
        AcyclicLP lp = new AcyclicLP(G, source);

        // print results
        System.out.println(" job   start  finish");
        System.out.println("--------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i + n));
        }
        System.out.printf("Finish time: %7.1f\n", lp.distTo(sink));
    }
}
