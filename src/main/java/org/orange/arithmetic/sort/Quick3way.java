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

/**
 * 三向切分(将数组分为小于,等于,大于三部分)
 * 适合包含很多重复元素的数组
 *
 * @author mingle
 */
public class Quick3way {
    private static final int INSERTION_SORT_CUTOFF = 10;

    /**
     * 三向切分快速排序
     *
     * @param a   the list to be sorted
     * @param <T> type
     */
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo + INSERTION_SORT_CUTOFF) {
            InsertionSort.sort(a, lo, hi);
            return;
        }

        int lt = lo;
        int i = lo + 1;
        int gt = hi;

        T v = a[lo];

        while (i <= gt) {
            int cmp = a[i].compareTo(v);

            if (cmp < 0) SortUtils.exchange(a, lt++, i++);
            else if (cmp > 0) SortUtils.exchange(a, i, gt--);
            else i++;
        } // now a[lo..lt-1] < v = a[lt..gt] < a[gt+1.. hi]

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] array = {4, 5, 2, 1, 3, 11, 9, 8, 7, 6, 10, 4, 5, 3, 4, 5, 4, 5, 4};
        sort(array);

        assert SortUtils.isSorted(array);

        SortUtils.show(array);
    }
}
