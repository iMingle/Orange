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

import org.orange.util.SortUtils;

import java.util.Arrays;

/**
 * 归并排序 - 稳定的排序算法
 * <ul>
 * <li>最好情况时间复杂度 O(nlogn)</li>
 * <li>最坏情况时间复杂度 O(nlogn)</li>
 * <li>平均情况时间复杂度 O(nlogn)</li>
 * <li>空间复杂度 O(n)</li>
 * </ul>
 *
 * @author mingle
 */
public class MergeSort {
    /**
     * 自顶向下的归并排序
     *
     * @param a   the list to be sorted
     * @param <T> type
     */
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        T[] aux = Arrays.copyOf(a, a.length);

        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return;

        int middle = lo + (hi - lo) / 2;

        sort(a, aux, lo, middle);
        sort(a, aux, middle + 1, hi);

        merge(a, aux, lo, middle, hi);
    }

    /**
     * 自底向上的归并排序
     *
     * @param a   the list to be sorted
     * @param <T> type
     */
    public static <T extends Comparable<? super T>> void sortBottomUp(T[] a) {
        int N = a.length;
        T[] aux = Arrays.copyOf(a, a.length);

        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int middle, int hi) {
        int i = lo;
        int j = middle + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > middle) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (SortUtils.less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        Integer[] array = {4, 5, 2, 1, 11, 12, 3, 9, 8, 7, 6, 10};
        sort(array);

        assert SortUtils.isSorted(array);

        SortUtils.show(array);

        Integer[] arrayBottomUp = {4, 5, 2, 1, 11, 3, 9, 8, 7, 6, 10};
        sortBottomUp(arrayBottomUp);

        assert SortUtils.isSorted(arrayBottomUp);

        SortUtils.show(arrayBottomUp);
    }
}
