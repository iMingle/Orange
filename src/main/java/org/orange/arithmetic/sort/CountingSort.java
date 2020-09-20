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

import java.util.Arrays;

/**
 * 计数排序(桶排序的一种特殊情况) - 稳定的排序算法
 * 非负整数数组 且 数据范围不大
 * <ul>
 * <li>时间复杂度 O(n)</li>
 * <li>空间复杂度 O(n)</li>
 * </ul>
 *
 * @author mingle
 */
public class CountingSort {
    public static void sort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }

        // 查找数组的范围
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        // 申请一个计数数组c，下标大小[0,max]
        int[] c = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < n; i++) {
            c[a[i]]++;
        }

        // 依次累加
        for (int i = 1; i <= max; i++) {
            c[i] = c[i - 1] + c[i];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[n];

        // 计算排序的关键步骤
        for (int i = n - 1; i >= 0; i--) {
            int index = --c[a[i]];
            r[index] = a[i];
        }

        // 将结果拷贝给a数组
        System.arraycopy(r, 0, a, 0, n);
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 0, 2, 9, 1, 0, 5, 2, 8, 1, 4};
        sort(data);
        System.out.println(Arrays.toString(data));
    }
}
