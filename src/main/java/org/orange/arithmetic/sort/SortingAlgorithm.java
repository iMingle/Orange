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

import java.lang.reflect.Array;
import java.util.Arrays;

public class SortingAlgorithm {

    public static <E extends Comparable<E>> void exchange(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <E extends Comparable<E>> boolean less(E v, E w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 冒泡排序(大气泡沉底)
     *
     * @param array 待排序数组
     */
    public static <E extends Comparable<E>> void bubbleSortBig(E[] array) {
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
     * @param array 待排序数组
     */
    public static <E extends Comparable<E>> void bubbleSortSmall(E[] array) {
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
     * @param array 待排序数组
     */
    public static <E extends Comparable<E>> void selectSort(E[] array) {
        int min;

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
     * @param array 待排序数组
     */
    public static <E extends Comparable<E>> void insertSort(E[] array) {
        for (int index = 1; index < array.length; index++) {
            for (int i = index; i > 0 && less(array[i], array[i - 1]); i--) {
                exchange(array, i, i - 1);
            }
        }
    }

    /**
     * shell排序
     *
     * @param array 待排序数组
     */
    public static <E extends Comparable<E>> void shellSort(E[] array) {
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

    @SuppressWarnings({"unchecked"})
    private static <E extends Comparable<E>> void merge(E[] array, int start, int middle, int end) {
        E[] temp = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length);
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
     * @param array 待排序数组
     * @param low   low index
     * @param high  high index
     */
    public static <E extends Comparable<E>> void mergeSort(E[] array, int low, int high) {
        if (high <= low)
            return;

        int middle = low + (high - low) / 2;

        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);

        merge(array, low, middle, high);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int low, int high) {
        int i = low;
        int j = high + 1;
        E v = array[low];

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
     * @param array 待排序数组
     * @param low   low index
     * @param high  high index
     */
    public static <E extends Comparable<E>> void quickSort(E[] array, int low, int high) {
        if (high <= low)
            return;

        int j = partition(array, low, high);

        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);
    }

    private static <E extends Comparable<E>> void sink(E[] array, int start, int N) {
        while (2 * start <= N) {
            int j = 2 * start;

            if (j + 1 <= N && less(array[j], array[j + 1])) {
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
     * @param array 待排序数组
     */
    public static <E extends Comparable<E>> void heapSort(E[] array) {
        int N = array.length - 1;

        for (int i = N / 2; i >= 1; i--) {
            sink(array, i, N);
        }

        while (N > 1) {
            exchange(array, 1, N--);
            sink(array, 1, N);
        }
    }

    public static void main(String[] args) {
//        Integer[] array = new Integer[]{0, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] array = new Integer[]{0, 4, 6, 8, 5, 9};

//        int[] array = new int[]{88, 76, 65, 91, 23, 34, 44, 100, 103, 888};

//        SortingAlgorithm.bubbleSortBig(array);
//        SortingAlgorithm.bubbleSortSmall(array);
//        SortingAlgorithm.selectSort(array);
//        SortingAlgorithm.insertSort(array);
//        SortingAlgorithm.shellSort(array);
//        SortingAlgorithm.mergeSort(array, 0, array.length - 1);
//        SortingAlgorithm.quickSort(array, 0, array.length - 1);
        SortingAlgorithm.heapSort(array);

        System.out.println(Arrays.toString(array));
    }
}
