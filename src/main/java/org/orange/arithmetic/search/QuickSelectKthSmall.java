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

package org.orange.arithmetic.search;

import edu.princeton.cs.algs4.StdRandom;
import org.orange.util.SortUtils;

/**
 * @author mingle
 */
public class QuickSelectKthSmall {
    private static final int M = 10;

    public static void main(String[] args) {
        Integer[] array = {4, 5, 2, 1, 3, 11, 9, 8, 7, 6, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        int k = 17;

        System.out.println(select(array, k));
    }

    /**
     * Rearranges the array so that {@code a[k]} contains the kth smallest key;
     * {@code a[0]} through {@code a[k-1]} are less than (or equal to) {@code a[k]}; and
     * {@code a[k+1]} through {@code a[n-1]} are greater than (or equal to) {@code a[k]}.
     *
     * @param a the array
     * @param k the rank of the key
     * @return the key of rank {@code k}
     * @throws IllegalArgumentException unless {@code 0 <= k < a.length}
     */
    public static <T extends Comparable<? super T>> T select(T[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }

        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }

    private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {
        int n = hi - lo + 1;
        int m = SortUtils.median(a, lo, lo + n / 2, hi);
        SortUtils.exchange(a, m, lo);

        int i = lo;
        int j = hi + 1;
        T v = a[lo];

        // a[lo] is unique largest element
        while (SortUtils.less(a[++i], v)) {
            if (i == hi) {
                SortUtils.exchange(a, lo, hi);
                return hi;
            }
        }

        // a[lo] is unique smallest element
        while (SortUtils.less(v, a[--j])) {
            if (j == lo + 1) return lo;
        }

        // the main loop
        while (i < j) {
            SortUtils.exchange(a, i, j);
            while (SortUtils.less(a[++i], v)) ;
            while (SortUtils.less(v, a[--j])) ;
        }

        // put partitioning item v at a[j]
        SortUtils.exchange(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
}
