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
 * 基数排序 - 稳定的排序算法
 * 将整数按位数切割成不同的数字，然后按每个位数分别比较
 *
 * <li>最好情况时间复杂度 O(kn)</li>
 * <li>最坏情况时间复杂度取决于桶内排序算法 O(k * nlogn)</li>
 * <li>平均情况时间复杂度 k * O(n + c) c=N*(logN-logM)</li>
 * <li>空间复杂度 O(n + m) m为位的种类数</li>
 *
 * @author mingle
 */
public class RadixSort {
    private static int maxbit(int[] a) {
        int n = a.length;
        // 最大数
        int maxData = a[0];
        // 先求出最大数，再求其位数，这样有原先依次每个数判断其位数，稍微优化点。
        for (int i = 1; i < n; ++i) {
            if (maxData < a[i])
                maxData = a[i];
        }

        int d = 1;
        int p = 10;
        while (maxData >= p) {
            //p *= 10; // Maybe overflow
            maxData /= 10;
            ++d;
        }

        return d;
    }

    public static void sort(int[] a) {
        int n = a.length;
        int d = maxbit(a);
        int[] tmp = new int[n];
        //计数器
        int[] count = new int[10];
        int i, j, k;
        int radix = 1;

        // 进行d次排序
        for (i = 1; i <= d; i++) {
            // 每次分配前清空计数器
            for (j = 0; j < 10; j++)
                count[j] = 0;

            // 统计每个桶中的记录数
            for (j = 0; j < n; j++) {
                k = (a[j] / radix) % 10;
                count[k]++;
            }

            // 将tmp中的位置依次分配给每个桶
            for (j = 1; j < 10; j++)
                count[j] = count[j - 1] + count[j];

            // 将所有桶中记录依次收集到tmp中
            for (j = n - 1; j >= 0; j--) {
                k = (a[j] / radix) % 10;
                tmp[count[k] - 1] = a[j];
                count[k]--;
            }

            // 将临时数组的内容复制到data中
            for (j = 0; j < n; j++)
                a[j] = tmp[j];

            radix = radix * 10;
        }
    }

    public static void main(String[] args) {
        int[] array = {4, 55, 2211, 11, 12, 13, 111, 3036, 907, 82, 7, 6, 10};
        sort(array);

        System.out.println(Arrays.toString(array));
    }
}
