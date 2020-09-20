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
 * 插入排序 - 稳定的排序算法
 * <ul>
 * <li>最好情况时间复杂度 O(n)</li>
 * <li>最坏情况时间复杂度 O(n**2)</li>
 * <li>平均情况时间复杂度 O(n**2)</li>
 * <li>空间复杂度 O(1)</li>
 * </ul>
 *
 * @author mingle
 */
public class InsertionSort {
    public static void sort(int[] a) {
        int N = a.length;
        int temp;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && a[j - 1] > a[j]; j--) {
                temp = a[j - 1];
                a[j - 1] = a[j];
                a[j] = temp;
            }
        }
    }

    public void insertionSort(int[] a) {
        int N = a.length;
        if (N <= 1) return;

        for (int i = 1; i < N; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j + 1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j + 1] = value; // 插入数据
        }
    }

    public static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > 0 && SortUtils.less(a[j], a[j - 1]); j--) {
                SortUtils.exchange(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] s = new String[]{"g", "f", "e", "d", "c", "b", "a"};

        sort(s, 0, s.length - 1);

        assert SortUtils.isSorted(s);

        SortUtils.show(s);

        int[] data = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
        sort(data);

        System.out.println(Arrays.toString(data));
    }
}
