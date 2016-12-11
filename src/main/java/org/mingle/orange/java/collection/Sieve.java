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

package org.mingle.orange.java.collection;

import java.util.BitSet;

/**
 * This program runs the Sieve of Erathostenes benchmark. It computes all primes
 * up to 2,000,000.
 * 
 * @author mingle
 */
public class Sieve {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 2000000;
        long start = System.currentTimeMillis();
        BitSet b = new BitSet(n + 1);
        int count = 0;
        int i;
        for (i = 2; i <= n; i++)
            b.set(i);
        i = 2;
        while (i * i <= n) {
            if (b.get(i)) {
                count++;
                int k = 2 * i;
                while (k <= n) {
                    b.clear(k);
                    k += i;
                }
            }
            i++;
        }
        while (i <= n) {
            if (b.get(i))
                count++;
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(count + " primes");
        System.out.println((end - start) + " milliseconds");
    }
}
