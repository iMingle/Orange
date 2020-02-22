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

package org.orange.arithmetic.base;

import edu.princeton.cs.algs4.StdRandom;

public class DoublingRatio {

    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }
    
    public static void main(String[] args) {
        double prev = timeTrial(125);
        
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            System.out.printf("%6d %7.1f ", N, time);
            System.out.printf("%5.1f\n", time/prev);
            prev = time;
        }
    }
}
