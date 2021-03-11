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

package org.orange.abc;

/**
 * @author mingle
 */
public class AbcOfBinarySearch {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 4, 4, 4, 4, 4, 6, 6, 7, 7, 8, 9, 10};

        System.out.println(AbcOfBinarySearch.search(a, 4));
        System.out.println(AbcOfBinarySearch.first(a, 4));
        System.out.println(AbcOfBinarySearch.last(a, 4));
        System.out.println(AbcOfBinarySearch.firstGreaterEquals(a, 5));
        System.out.println(AbcOfBinarySearch.lastLesserEquals(a, 10));

        a = new int[]{8, 8, 11, 18, 1, 3, 4, 5, 6, 8};

        System.out.println(AbcOfBinarySearch.circularSearch(a, 6));
    }

    public static int search(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] == key) return mid;
            else if (a[mid] > key) hi = mid - 1;
            else lo = mid + 1;
        }

        return -1;
    }

    public static int first(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] >= key) hi = mid - 1;
            else lo = mid + 1;
        }

        if (lo < a.length && a[lo] == key) return lo;
        else return -1;
    }

    public static int last(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] <= key) lo = mid + 1;
            else hi = mid - 1;
        }

        if (hi >= 0 && a[hi] == key) return hi;
        else return -1;
    }

    public static int firstGreaterEquals(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] >= key) hi = mid - 1;
            else lo = mid + 1;
        }

        if (lo < a.length && a[lo] >= key) return lo;
        else return -1;
    }

    public static int lastLesserEquals(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] <= key) lo = mid + 1;
            else hi = mid - 1;
        }

        if (hi >= 0 && a[hi] <= key) return hi;
        else return -1;
    }

    public static int circularSearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] == key) return mid;

            if (a[lo] < a[mid]) {
                if (key >= a[lo] && key < a[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (key > a[mid] && key <= a[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }
}
