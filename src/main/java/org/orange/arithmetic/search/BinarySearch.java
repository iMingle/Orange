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

package org.orange.arithmetic.search;

/**
 * 二分查找
 *
 * @author mingle
 */
public class BinarySearch {

    /**
     * 查找等于给定值的元素
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int search(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }

        return -1;
    }

    /**
     * 查找第一个值等于给定值的元素
     * 简洁的实现
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int firstBrief(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key <= a[mid]) hi = mid - 1;
            else lo = mid + 1;
        }

        if (lo < a.length && a[lo] == key) return lo;
        else return -1;
    }

    /**
     * 查找第一个值等于给定值的元素
     * 易于理解的实现
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int first(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                if (mid == 0 || a[mid - 1] != key) return mid;
                else hi = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int firstGreaterEquals(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key <= a[mid]) {
                if (mid == 0 || a[mid - 1] < key) return mid;
                else hi = mid - 1;
            } else lo = mid + 1;
        }

        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * 简洁的实现
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int lastBrief(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key < a[mid]) hi = mid - 1;
            else lo = mid + 1;
        }

        if (hi >= 0 && a[hi] == key) return hi;
        else return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * 易于理解的实现
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int last(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                if (mid == a.length - 1 || a[mid + 1] != key) return mid;
                else lo = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int lastLesserEquals(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key >= a[mid]) {
                if (mid == a.length - 1 || a[mid + 1] > key) return mid;
                else lo = mid + 1;
            } else hi = mid - 1;
        }

        return -1;
    }

    /**
     * 递归查找等于给定值的元素
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int searchRecursive(int[] a, int key) {
        return recursive(a, 0, a.length - 1, key);
    }

    private static int recursive(int[] a, int lo, int hi, int key) {
        if (lo > hi)
            return -1;
        int mid = lo + ((hi - lo) >> 1);
        if (key < a[mid])
            return recursive(a, lo, mid - 1, key);
        else if (key > a[mid])
            return recursive(a, mid + 1, hi, key);
        else
            return mid;
    }

    /**
     * 循环数组查找等于给定值的元素
     *
     * <p>
     * 我们发现循环数组存在一个性质：以数组中间点为分区，会将数组分成一个有序数组和一个循环有序数组。
     * <p>
     * 如果首元素小于 mid，说明前半部分是有序的，后半部分是循环有序数组；
     * 如果首元素大于 mid，说明后半部分是有序的，前半部分是循环有序的数组；
     * 如果目标元素在有序数组范围中，使用二分查找；
     * 如果目标元素在循环有序数组中，设定数组边界后，使用以上方法继续查找。
     * <p>
     * 时间复杂度为 O(logN)。
     *
     * @param a   查询数组
     * @param key 查询的值
     * @return 等于查询值的下标
     */
    public static int circularSearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (key == a[mid]) return mid;

            if (a[lo] <= a[mid]) {    // 前半部是有序的，后半部是循环数组
                if (key < a[mid] && key >= a[lo]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {    // 后半部是有序的，前半部是循环数组
                if (key > a[mid] && key <= a[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 4, 4, 5, 6, 6, 7, 7, 8, 9, 10};

        System.out.println(BinarySearch.search(a, 4));
        System.out.println(BinarySearch.searchRecursive(a, 4));

        a = new int[]{1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        System.out.println(BinarySearch.firstBrief(a, 8));
        System.out.println(BinarySearch.first(a, 8));

        System.out.println(BinarySearch.lastBrief(a, 8));
        System.out.println(BinarySearch.last(a, 8));

        System.out.println(BinarySearch.firstGreaterEquals(a, 8));
        System.out.println(BinarySearch.lastLesserEquals(a, 8));


        a = new int[]{8, 8, 11, 18, 1, 3, 4, 5, 6, 8};

        System.out.println(BinarySearch.circularSearch(a, 6));
    }
}
