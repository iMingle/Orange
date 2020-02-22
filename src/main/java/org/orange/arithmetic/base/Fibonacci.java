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

import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {
    
    public static int[] array = new int[100];

    public static long F(int N) {
        if (N == 0)
            return 0;
        if (N == 1)
            return 1;
        return F(N - 1) + F(N - 2);
    }
    
    public static long newF(int N) {
        if (N == 0)
            return array[0] = 0;
        if (N == 1)
            return array[1] = 1;
        
        return array[N] = array[N - 1] + array[N - 2];
    }

    public static void main(String[] args) {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + newF(N));
    }

}
