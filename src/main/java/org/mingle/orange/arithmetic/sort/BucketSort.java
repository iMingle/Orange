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

import java.util.Arrays;
import java.util.List;

/**
 * 桶排序
 * 限制: 输入数据A1, A2, ..., An, 必须只由小于M的正整数组成
 *
 * @author mingle
 */
public class BucketSort {
    public static void sort(int[] a, int maxVal) {
        int[] bucket = new int[maxVal + 1];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = 0;
        }

        for (int i = 0; i < a.length; i++) {
            bucket[a[i]]++;
        }

        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                a[outPos++] = i;
            }
        }
    }

    public static void sort(List<Integer> a, int maxVal) {
        int[] bucket = new int[maxVal + 1];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = 0;
        }

        for (int i = 0; i < a.size(); i++) {
            bucket[a.get(i)]++;
        }

        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                a.set(outPos++, i);
            }
        }
    }

    public static void main(String[] args) {
        int maxVal = 5;
        int[] data = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
        List<Integer> dataList = Arrays.asList(5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4);

        System.out.println("Before: " + Arrays.toString(data));
        sort(data, maxVal);
        System.out.println("After:  " + Arrays.toString(data));

        sort(dataList, 5);
        System.out.println("After:  " + dataList);
    }
}
