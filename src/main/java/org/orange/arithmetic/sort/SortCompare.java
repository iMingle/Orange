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

package org.orange.arithmetic.sort;

import edu.princeton.cs.algs4.StdRandom;
import org.orange.arithmetic.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class SortCompare {
    
    public static double time(String alg, List<Double> a) {
        Stopwatch timer = new Stopwatch();
        
        if (alg.equals("Insertion")) Insertion.sort(a, 0, a.size() - 1);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        
        return timer.elapsedTime();
    }
    
    private static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        List<Double> a = new ArrayList<>(N);
        
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a.add(StdRandom.uniform());
            }
            
            total += time(alg, a);
        }
        
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        
        System.out.printf("For %d random Doubles\n    %s is", N, alg1);
        System.out.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}
