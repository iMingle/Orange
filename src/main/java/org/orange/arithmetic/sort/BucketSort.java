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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桶排序 - 稳定的排序算法
 * 数据容易划分 且 桶之间有序
 * <li>最好情况时间复杂度 O(n)</li>
 * <li>最坏情况时间复杂度取决于桶内排序算法 可能为O(nlogn)</li>
 * <li>平均情况时间复杂度 O(n + c) c=N*(logN-logM)</li>
 * <li>空间复杂度 O(n + m) m为桶的个数</li>
 *
 * @author mingle
 */
public class BucketSort {
    public static void sort(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int a : arr) {
            if (max < a)
                max = a;
            if (min > a)
                min = a;
        }

        // set bucket number
        int bucketNum = max / 10 - min / 10 + 1;
        List<List<Integer>> buckList = new ArrayList<>();
        // create bucket
        for (int i = 1; i <= bucketNum; i++) {
            buckList.add(new ArrayList<>());
        }

        // push into the bucket
        for (int i = 0; i < arr.length; i++) {
            int index = indexFor(arr[i], min, 10);
            buckList.get(index).add(arr[i]);
        }

        List<Integer> bucket;
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            bucket = buckList.get(i);
            insertSort(bucket);
            for (int k : bucket) {
                arr[index++] = k;
            }
        }
    }

    private static int indexFor(int a, int min, int step) {
        return (a - min) / step;
    }

    private static void insertSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, temp);
        }
    }

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

    public static void main(String[] args) {
        int maxVal = 5;
        int[] data = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};

        System.out.println("Before: " + Arrays.toString(data));
        sort(data, maxVal);
        System.out.println("After:  " + Arrays.toString(data));

        int[] dataBucket = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
        sort(dataBucket);
        System.out.println(Arrays.toString(dataBucket));
    }
}
