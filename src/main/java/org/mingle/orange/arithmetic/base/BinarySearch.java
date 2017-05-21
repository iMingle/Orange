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

package org.mingle.orange.arithmetic.base;

/**
 * 二分查找
 *
 * @author mingle
 */
public class BinarySearch {

    public static int rank0(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }

        return -1;
    }

    public static int rank1(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int min = 0;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                min = mid;
                hi = mid;

                break;
            }
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) lo = mid + 1;
            else {
                min = mid;
                hi = mid;
            }
        }

        return min;
    }

    public static int rank2(int key, int[] a) {
        return rankRecursive(key, a, 0, a.length - 1);
    }

    private static int rankRecursive(int key, int[] a, int lo, int hi) {
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid])
            return rankRecursive(key, a, lo, mid - 1);
        else if (key > a[mid])
            return rankRecursive(key, a, mid + 1, hi);
        else
            return mid;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 4, 4, 5, 6, 6, 7, 7, 8, 9, 10};

        System.out.println(BinarySearch.rank0(4, a));
        System.out.println(BinarySearch.rank1(4, a));
        System.out.println(BinarySearch.rank2(4, a));
    }
}
