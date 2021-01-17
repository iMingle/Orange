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

import org.orange.arithmetic.util.StandardDraw;
import org.orange.util.SortUtils;

/**
 * 快速排序可视化
 *
 * @author mingle
 */
public class QuickBars {
    private static int ROWS;
    private static int row = 0;
    private static final int VERTICAL = 50;
    private static final int INSERTION_SORT_CUTOFF = 8;

    // partition the subarray a[lo .. hi] by returning an index j
    // so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    private static int partition(Double[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        double v = a[lo];
        while (true) {

            // find item on lo to swap
            while (SortUtils.less(a[++i], v))
                if (i == hi)
                    break;

            // find item on hi to swap
            while (SortUtils.less(v, a[--j]))
                if (j == lo)
                    break; // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j)
                break;

            SortUtils.exchange(a, i, j);
        }

        // put v = a[j] into position
        SortUtils.exchange(a, lo, j);

        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static void sort(Double[] a) {
        show(a, 0, 0, -1, a.length - 1);
        sort(a, 0, a.length - 1);
        show(a, 0, 0, -1, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Double[] a, int lo, int hi) {
        // cutoff to insertion sort
        int N = hi - lo + 1;
        if (N <= INSERTION_SORT_CUTOFF) {
            InsertionSort.sort(a, lo, hi);
            // show(a, lo, -1, -1, hi);
            return;
        }

        int m = SortUtils.median(a, lo, lo + N / 2, hi);
        SortUtils.exchange(a, m, lo);

        int j = partition(a, lo, hi);
        show(a, lo, j, j, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // draw one row of trace
    private static void show(Double[] a, int lo, int lt, int gt, int hi) {
        double y = ROWS - row - 1;
        for (int k = 0; k < a.length; k++) {
            if (k < lo)
                StandardDraw.setPenColor(StandardDraw.LIGHT_GRAY);
            else if (k > hi)
                StandardDraw.setPenColor(StandardDraw.LIGHT_GRAY);
            else if (k >= lt && k <= gt)
                StandardDraw.setPenColor(StandardDraw.BOOK_RED);
            else
                StandardDraw.setPenColor(StandardDraw.BLACK);

            StandardDraw.filledRectangle(k, y + a[k] * .25, .25, a[k] * .25);
        }
        row++;
    }

    public static void main(String[] args) {
        int M = 1000;
        int N = 75;
        Double[] a = new Double[N];
        Double[] b = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = ((int) (1 + Math.random() * M)) / (double) M;
            b[i] = a[i];
        }

        // precompute the number of rows
        StandardDraw.show();
        ROWS = 0;
        sort(b);
        ROWS = row;
        row = 0;
        StandardDraw.clear();

        StandardDraw.setCanvasSize(800, ROWS * VERTICAL);
        StandardDraw.show();
        StandardDraw.square(.5, .5, .5);
        StandardDraw.setXscale(-1, N);
        StandardDraw.setYscale(-0.5, ROWS);
        StandardDraw.show();
        sort(a);
        StandardDraw.show();
    }
}
