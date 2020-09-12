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
 * 冒泡排序
 *
 * @author mingle
 */
public class BubbleSort {
    public static void sort(int[] a) {
        int N = a.length;
        int temp;
        for (int i = 0; i < N - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < N - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
    }

    public static void sortDesc(int[] a) {
        int N = a.length;
        int temp;
        for (int i = 0; i < N - 1; i++) {
            boolean flag = false;
            for (int j = N - 1; j > i; j--) {
                if (a[j - 1] < a[j]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;

                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
    }

    public static void sortDesc1(int[] a) {
        int N = a.length;
        int temp;
        for (int i = 0; i < N - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < N - i - 1; j++) {
                if (a[j] < a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
        sort(data);

        System.out.println(Arrays.toString(data));

        int[] dataDesc = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
        sortDesc(dataDesc);
        System.out.println(Arrays.toString(dataDesc));

        int[] dataDesc1 = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
        sortDesc1(dataDesc1);
        System.out.println(Arrays.toString(dataDesc1));
    }
}
