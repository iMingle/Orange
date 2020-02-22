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
import java.util.List;

/**
 * @author mingle
 */
public class Selection {

    public static <T extends Comparable<T>> void sort(List<T> a) {
        int N = a.size();
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (SortUtils.less(a.get(j), a.get(min))) {
                    min = j;
                }
            }

            if (i != min)
                SortUtils.exchange(a, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        List<Integer> array = Arrays.asList(arr);
        sort(array);

        assert SortUtils.isSorted(array);

        SortUtils.show(array);
    }
}
