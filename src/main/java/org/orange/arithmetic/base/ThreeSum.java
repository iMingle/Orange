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

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class ThreeSum {
    public static int count(int[] a) { // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
        return cnt;
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        String[] s = In.readStrings("1.txt");
        int[] a = new int[s.length];

        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }

        System.out.println(a.length);
        int count = 0;

        Arrays.sort(a);

        for (int i = 0; i < a.length; i++) {
            if (BinarySearch.rank0(-a[i], a) > i) {
                count++;
            }
        }

        System.out.println(count);
    }
}
