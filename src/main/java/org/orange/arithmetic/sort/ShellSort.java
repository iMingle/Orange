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
 * 希尔排序
 *
 * @author mingle
 */
public class ShellSort {
    public static void sort(int[] a) {
        int N = a.length;
        int h = 1;
        int temp;

        while (h < N / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && a[j - h] > a[j]; j -= h) {
                    temp = a[j - h];
                    a[j - h] = a[j];
                    a[j] = temp;
                }
            }

            h /= 3;
        }
    }

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        int N = a.length;
        int h = 1;

        while (h < N / 3) h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && SortUtils.less(a[j], a[j - h]); j -= h) {
                    SortUtils.exchange(a, j, j - h);
                }
            }

            h /= 3;
        }
    }

    public static void main(String[] args) {
        String[] s = new String[]{"g", "f", "e", "d", "c", "b", "a", "h"};
        sort(s);

        assert SortUtils.isSorted(s);

        SortUtils.show(s);

        int[] data = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(data);

        System.out.println(Arrays.toString(data));
    }
}
