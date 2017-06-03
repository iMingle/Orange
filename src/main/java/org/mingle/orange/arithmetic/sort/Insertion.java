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

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mingle
 */
public class Insertion {

    public static <T extends Comparable<T>> void sort(List<T> a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > 0 && SortUtils.less(a.get(j), a.get(j - 1)); j--) {
                SortUtils.exchange(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] str = new String[] {"g", "f", "e", "d", "c", "b", "a"};
        List<String> s = Arrays.asList(str);

        sort(s, 0, s.size() - 1);

        assert SortUtils.isSorted(s);

        SortUtils.show(s);
    }
}
