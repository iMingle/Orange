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

public class SortingAlgorithm {

    public static void exchange(Comparable<Integer>[] array, int i, int j) {
        Integer temp = (Integer) array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 冒泡排序(大气泡沉底)
     *
     * @param array
     */
    public static void bubbleSortBig(Comparable<Integer>[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (less(array[j + 1], array[j])) {
                    exchange(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序(轻气泡上浮)
     *
     * @param array
     */
    public static void bubbleSortSmall(Comparable<Integer>[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (less(array[j], array[j - 1])) {
                    exchange(array, j, j - 1);
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param array
     */
    public static void selectSort(Comparable<Integer>[] array) {
        int min = 0;

        for (int i = 0; i < array.length; i++) {
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }

            exchange(array, i, min);
        }
    }

    /**
     * 简单插入排序
     *
     * @param array
     */
    public static void insertSort(Comparable<Integer>[] array) {
        for (int index = 1; index < array.length; index++) {
            for (int i = index; i > 0 && less(array[i], array[i - 1]); i--) {
                exchange(array, i, i - 1);
            }
        }
    }

    /**
     * shell排序
     *
     * @param array
     */
    public static void shellSort(Comparable<Integer>[] array) {
        int N = array.length;
        int h = 1;

        while (h < N / 3)
            h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h) {
                    exchange(array, j, j - h);
                }
            }

            h /= 3;
        }
    }

    /**
     * 数组归并
     *
     * @param array
     * @param start
     * @param middle
     * @param end
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void merge(Comparable<Integer>[] array, int start,
                             int middle, int end) {
        Comparable[] temp = new Comparable[array.length];
        int i = start;
        int j = middle + 1;

        for (int k = start; k <= end; k++) {
            temp[k] = array[k];
        }

        for (int k = start; k <= end; k++) {
            if (i > middle)
                array[k] = temp[j++];
            else if (j > end)
                array[k] = temp[i++];
            else if (less(temp[i], temp[j]))
                array[k] = temp[i++];
            else
                array[k] = temp[j++];
        }
    }

    /**
     * 归并排序
     *
     * @param array
     * @param low
     * @param high
     */
    public static void mergeSort(Comparable<Integer>[] array, int low, int high) {
        if (high <= low)
            return;
        int middle = low + (high - low) / 2;

        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);

        merge(array, low, middle, high);
    }

    @SuppressWarnings("rawtypes")
    public static int partition(Comparable<Integer>[] array, int low, int high) {
        int i = low;
        int j = high + 1;
        Comparable v = array[low];

        while (true) {
            // 从左向右找到比v大的元素
            while (less(array[++i], v)) {
                if (i == high)
                    break;
            }

            // 从右向左找到比v小的元素
            while (less(v, array[--j])) {
                if (j == low)
                    break;
            }

            if (i >= j)
                break;
            exchange(array, i, j);
        }

        exchange(array, low, j);

        return j;
    }

    /**
     * 快速排序
     *
     * @param array
     * @param low
     * @param high
     */
    public static void quickSort(Comparable<Integer>[] array, int low, int high) {
        if (high <= low)
            return;
        int j = partition(array, low, high);

        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);
    }

    /**
     * @param array
     * @param start
     * @param N
     */
    public static void sink(Comparable<Integer>[] array, int start, int N) {
        while (2 * start < N) {
            int j = 2 * start;

            if (j < N && less(array[j], array[j + 1])) {
                j++;
            }

            if (!less(array[start], array[j]))
                break;
            exchange(array, start, j);
            start = j;
        }
    }

    /**
     * 堆排序
     *
     * @param array
     */
    public static void heapSort(Comparable<Integer>[] array) {
        int N = array.length - 1;

        for (int i = N / 2; i >= 1; i--) {
            sink(array, i, N);
        }

        while (N > 1) {
            exchange(array, 1, N--);
            sink(array, 1, N);
        }
    }

    /**
     * 基数排序
     *
     * @param array
     * @param digit 表示最大的数有多少位
     */
    public static void radixSort(int[] array, int digit) {
        int k = 0;                                    // 计数用
        int n = 1;                                    // 跳位用，个位到十位
        int m = 1;                                    // 控制键值排序依据在哪一位
        int[][] bucket = new int[10][array.length];    // 数组的第一维表示可能的余数0-9
        int[] number = new int[10];                    // 数组order[i]用来表示该位是i的数的个数

        while (m <= digit) {
            for (int i = 0; i < array.length; i++) {
                int remainder = ((array[i] / n) % 10);
                bucket[remainder][number[remainder]] = array[i];
                number[remainder]++;
            }

            for (int i = 0; i < 10; i++) {
                if (number[i] != 0) {
                    for (int j = 0; j < number[i]; j++) {
                        array[k] = bucket[i][j];
                        k++;
                    }
                }

                number[i] = 0;
            }

            n *= 10;
            k = 0;
            m++;
        }
    }

    public static void main(String[] args) {
//        Integer[] array = new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        int[] array = new int[]{88, 76, 65, 91, 23, 34, 44, 100, 103, 888};

//        SortingAlgorithm.bubbleSortBig(array);
//        SortingAlgorithm.bubbleSortSmall(array);
//        SortingAlgorithm.selectSort(array);
//        SortingAlgorithm.insertSort(array);
//        SortingAlgorithm.shellSort(array);
//        SortingAlgorithm.mergeSort(array, 0, array.length - 1);
//        SortingAlgorithm.quickSort(array, 0, array.length - 1);
//        SortingAlgorithm.heapSort(array);
        SortingAlgorithm.radixSort(array, 3);

        System.out.println(Arrays.toString(array));
    }
}
