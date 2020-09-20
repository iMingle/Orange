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

package org.orange;

import org.orange.util.SortUtils;

import java.util.Arrays;

/**
 * @author mingle
 */
public class Test {
    public static void main(String[] args) {
        /**
         * 12345的浮点数标识
         * 11000000111001  1.1000000111001*(2**13)
         */
        System.out.println(Long.toBinaryString(-1L));
        System.out.println(1L >> 2);
        System.out.println(Float.floatToIntBits(1.2f));
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
        System.out.println(Long.toBinaryString(Long.MAX_VALUE));
        System.out.println(Long.toBinaryString(Long.MIN_VALUE));
        System.out.println(Long.toBinaryString((-1L) >> 2));
        System.out.println(Long.toBinaryString(12345)); // 1+8+16+32+2**12+2**13

        Integer[] a = new Integer[] {5, 3, 6, 7, 2, 1, 9, 8, 4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(Integer[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Integer[] a, int lo, int hi) {
        if (hi <= lo)
            return;

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Integer[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        int v = a[lo];
        for (;;) {
            while (a[++i] < v) if (i == hi) break;
            while (v < a[--j]) if (j == lo) break;
            if (i >= j) break;

            SortUtils.exchange(a, i, j);
        }

        SortUtils.exchange(a, lo, j);
        return j;
    }
}
