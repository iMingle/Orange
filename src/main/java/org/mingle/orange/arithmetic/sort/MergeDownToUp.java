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

package org.mingle.orange.arithmetic.sort;

import org.mingle.orange.util.SortUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mingle
 */
public class MergeDownToUp {
    public static <T extends Comparable<T>> void sort(List<T> a) {
        int N = a.size();
        List<Comparable> aux = new ArrayList<>(a);

        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                MergeUpToDown.merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 5, 2, 1, 11, 3, 9, 8, 7, 6, 10};
        List<Integer> array = Arrays.asList(arr);
        sort(array);

        assert SortUtils.isSorted(array);

        SortUtils.show(array);
    }
}
