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

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;
import org.orange.util.SortUtils;

/**
 * 快速排序 - 不稳定的排序算法
 * <ul>
 * <li>最好情况时间复杂度 O(nlogn)</li>
 * <li>最坏情况时间复杂度 O(n**2)</li>
 * <li>平均情况时间复杂度 O(nlogn)</li>
 * <li>空间复杂度 O(1)</li>
 * </ul>
 *
 * @author mingle
 */
public class QuickSort {
    private static final int INSERTION_SORT_CUTOFF = 10;

    /**
     * 快速排序,lo作为pivot
     *
     * @param a   the list to be sorted
     * @param <T> type
     */
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        // cutoff to insertion sort
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_CUTOFF) {
            Insertion.sort(a, lo, hi);
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        // 第一个元素作为pivot
        T v = a[lo];

        for (; ; ) {
            while (SortUtils.less(a[++i], v)) if (i == hi) break;
            while (SortUtils.less(v, a[--j])) if (j == lo) break;

            if (i >= j) break;
            SortUtils.exchange(a, i, j);
        }

        SortUtils.exchange(a, lo, j);

        return j;
    }

    /**
     * 快速排序,中位数作为枢纽元
     *
     * @param a   the list to be sorted
     * @param <T> type
     */
    public static <T extends Comparable<? super T>> void mediansort(T[] a) {
        mediansort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void mediansort(T[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        // cutoff to insertion sort
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_CUTOFF) {
            Insertion.sort(a, lo, hi);
            return;
        }

        int j = partitionMedian(a, lo, hi);
        mediansort(a, lo, j - 1);
        mediansort(a, j + 1, hi);
    }

    private static <T extends Comparable<? super T>> int partitionMedian(T[] a, int lo, int hi) {
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

    public static void main(String[] args) {
        Integer[] array = {4, 5, 2, 11, 12, 13, 1, 3, 9, 8, 7, 6, 10};
        sort(array);

        assert SortUtils.isSorted(array);

        SortUtils.show(array);

        Integer[] arrayMedian = {4, 5, 2, 11, 12, 13, 1, 3, 9, 8, 7, 6, 10};
        mediansort(arrayMedian);

        assert SortUtils.isSorted(arrayMedian);

        SortUtils.show(arrayMedian);
    }
}
