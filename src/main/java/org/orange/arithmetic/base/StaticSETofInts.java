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

package org.orange.arithmetic.base;

import java.util.Arrays;

/**
 * The {@code StaticSETofInts} class represents a set of integers.
 * It supports searching for a given integer is in the set. It accomplishes
 * this by keeping the set of integers in a sorted array and using
 * binary search to find the given integer.
 * <p>
 * The <em>rank</em> and <em>contains</em> operations take
 * logarithmic time in the worst case.
 *
 * @author mingle
 */
public class StaticSETofInts {
    private int[] a;

    public StaticSETofInts(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i]; // defensive copy
        Arrays.sort(a);
    }

    public boolean contains(int key) {
        return rank(key) != -1;
    }

    public int howMany(int key) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = 0;
        int min = 0;
        int max = 0;
        while (lo <= hi) { // Key is in a[lo..hi] or not present.
            mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else {
                min = mid;
                max = mid;
                break;
            }
        }

        lo = 0;
        hi = mid;
        while (lo < hi) {
            int midd = lo + (hi - lo) / 2;
            if (key > a[midd]) lo = mid + 1;
            else {
                min = midd;
                hi = midd;
            }
        }

        lo = mid;
        hi = a.length - 1;
        while (lo < hi) {
            int midd = lo + (hi - lo) / 2;
            if (key < a[midd]) hi = mid - 1;
            else {
                max = midd;
                lo = midd;
            }
        }
        System.out.println(max);
        System.out.println(min);
        return max - min;
    }

    private int rank(int key) { // Binary search.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) { // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6};
        StaticSETofInts se = new StaticSETofInts(a);

        System.out.println(se.howMany(3));
    }
}
