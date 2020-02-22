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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mingle
 */
public class MergeUpToDown {
    public static <T extends Comparable<T>> void sort(List<T> a) {
        List<Comparable> aux = new ArrayList<>(a);

        sort(a, aux, 0, a.size() - 1);
    }

    private static <T extends Comparable<T>> void sort(List<T> a, List<Comparable> aux, int lo, int hi) {
        if (hi <= lo) return;

        int middle = lo + (hi - lo) / 2;

        sort(a, aux, lo, middle);
        sort(a, aux, middle + 1, hi);

        merge(a, aux, lo, middle, hi);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void merge(List<T> a, List<Comparable> aux, int lo, int middle, int hi) {
        int i = lo;
        int j = middle + 1;

        for (int k = lo; k <= hi; k++)
            aux.set(k, a.get(k));

        for (int k = lo; k <= hi; k++) {
            if (i > middle) a.set(k, (T) aux.get(j++));
            else if (j > hi) a.set(k, (T) aux.get(i++));
            else if (SortUtils.less(aux.get(i), aux.get(j))) a.set(k, (T) aux.get(i++));
            else a.set(k, (T) aux.get(j++));
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 5, 2, 1, 11, 12, 3, 9, 8, 7, 6, 10};
        List<Integer> array = Arrays.asList(arr);
        sort(array);

        assert SortUtils.isSorted(array);

        SortUtils.show(array);
    }
}
