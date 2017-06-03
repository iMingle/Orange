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

package org.mingle.orange.arithmetic.search;

import org.mingle.orange.arithmetic.sort.Insertion;
import org.mingle.orange.util.SortUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author mingle
 */
public class QuickSelect {
    private static final int M = 10;

    public static void main(String[] args) {
        Integer[] arr = {4, 5, 2, 1, 3, 11, 9, 8, 7, 6, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        List<Integer> array = Arrays.asList(arr);
        int k = 17;

        quickSelect(array, 0, array.size() - 1, k);

        System.out.println(array.get(k - 1));
    }

    /**
     * internal selection method that make recursive calls.
     * use median-of-three partitioning and a cutoff of 10.
     * places the kth smallest item in a[k - 1]
     *
     * @param a
     * @param left
     * @param right
     * @param k     the desired rank (1 is minimum) in the entire array.
     */
    public static <T extends Comparable<T>> void quickSelect(List<T> a, int left, int right, int k) {
        if (left + M <= right) {
            T pivot = SortUtils.median(a, left, right);
            int i = left, j = right - 1; // begin partitioning

            for (; ; ) {
                while (a.get(++i).compareTo(pivot) < 0) ;
                while (pivot.compareTo(a.get(--j)) < 0) ;
                if (i < j)
                    SortUtils.exchange(a, i, j);
                else break;
            }

            SortUtils.exchange(a, i, right - 1); // restore pivot

            if (k <= i)
                quickSelect(a, left, i - 1, k);
            else if (k > i + 1)
                quickSelect(a, i + 1, right, k);
        } else {
            Insertion.sort(a, left, right);
        }
    }

}
