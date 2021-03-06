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

package org.orange;

import java.util.Arrays;

/**
 * @author mingle
 */
public class Abc {
    public static void main(String[] args) {
        /**
         * 12345的浮点数标识
         * 11000000111001  1.1000000111001*(2**13)
         */
        System.out.println(Long.toBinaryString(-1L));
        System.out.println(Float.floatToIntBits(1.2f));
        System.out.println(Long.toBinaryString((-1L) >> 2));
        System.out.println(Long.toBinaryString(12345)); // 1+8+16+32+2**12+2**13

//        Integer[] a = new Integer[]{5, 3, 6, 7, 2, 1, 9, 8, 4};
        Integer[] a = new Integer[]{0, 4, 6, 8, 5, 9};
//        sort(a);
//        shell(a);
        heap(a);
//        quick(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    private static void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void heap(Integer[] a) {
        int N = a.length - 1;

        for (int i = (a.length >> 1) - 1; i >= 0; i--) {
            sink(a, i, N);
        }

        for (int i = N; i > 0; i--) {
            swap(a, 0, i);
            sink(a, 0, i - 1);
        }
    }

    private static void sink(Integer[] a, int start, int N) {
        int li = 2 * start + 1;
        int ri = li + 1;
        int max = li;
        if (li > N) return;
        if (ri <= N && a[ri] > a[li])
            max = ri;
        if (a[max] > a[start]) {
            swap(a, max, start);
            sink(a, max, N);
        }
    }

    public static void quick(Integer[] a, int low, int high) {
        if (high <= low)
            return;

        int j = partition(a, low, high);

        quick(a, low, j - 1);
        quick(a, j + 1, high);
    }

    private static int partition(Integer[] a, int low, int high) {
        int i = low;
        int j = high + 1;
        Integer v = a[low];

        while (true) {
            while (a[++i] < v) if (i == high) break;
            while (a[--j] > v) if (j == low) break;

            if (i >= j) break;

            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        a[low] = a[j];
        a[j] = v;

        return j;
    }

    public static void merge(Integer[] a, int low, int high) {
        if (high <= low)
            return;

        int middle = low + (high - low) / 2;

        merge(a, low, middle);
        merge(a, middle + 1, high);

        merge(a, low, middle, high);
    }

    public static void merge(Integer[] a, int low, int middle, int high) {
        Integer[] temp = new Integer[a.length];
        int i = low;
        int j = middle + 1;

        for (int k = low; k <= high; k++) {
            temp[k] = a[k];
        }

        for (int k = low; k <= high; k++) {
            if (i > middle)
                a[k] = temp[j++];
            else if (j > high)
                a[k] = temp[i++];
            else if (temp[i] < temp[j])
                a[k] = temp[i++];
            else
                a[k] = temp[j++];
        }
    }

    public static void shell(Integer[] a) {
        int N = a.length;
        int h = 1;

        while (h < N / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
                    int temp = a[j];
                    a[j] = a[j - h];
                    a[j - h] = temp;
                }
            }

            h /= 3;
        }
    }

    public static void select(Integer[] a) {
        int min;
        for (int i = 0; i < a.length; i++) {
            min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }

            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }

    public static void insert(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }

    public static void bubble(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void bubble1(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
    }
}
