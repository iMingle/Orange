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

package org.mingle.orange.util;

import java.util.List;

/**
 * 排序工具类
 *
 * @author mingle
 */
public abstract class SortUtils {
    /**
     * return median of left, center and right.
     * order these and hide the pivot.
     * <p>
     * 首选对A[left],A[right],A[center]进行从小到大排序，经过该操作后，
     * 三元素的最大者被放到了A[right],最小者被放到了A[left],A[center]中的元素大小居中，
     * 这样就可以作为枢纽元。由于A[right]大于枢纽元，我们可以把枢纽元放到A[right-1],
     * 并在分割阶段将左右的遍历索引i和j分别置为left+1,right-2.
     * <p>
     * 这样操作的好处是，因为此时A[left]比枢纽元小，所以将它作为j的警戒标记，
     * 因此我们不用担心j越界；由于i将停在那些等于枢纽元处，故将枢纽元存储在A[right-1],将提供一个警戒标记。
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static <T extends Comparable<T>> T median(List<T> a, int left, int right) {
        int center = left + (right - left) / 2;
        if (a.get(center).compareTo(a.get(left)) < 0)
            SortUtils.exchange(a, center, left);
        if (a.get(right).compareTo(a.get(left)) < 0)
            SortUtils.exchange(a, right, left);
        if (a.get(right).compareTo(a.get(center)) < 0)
            SortUtils.exchange(a, right, center);

        SortUtils.exchange(a, center, right - 1); // place pivot at position right - 1

        return a.get(right - 1);
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static <T extends Comparable<T>> void exchange(List<T> a, int i, int j) {
        T t = a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    }

    public static <T extends Comparable<T>> void show(List<T> a) {
        for (T element : a) {
            System.out.print(element + " ");
        }

        System.out.println();
    }

    public static <T extends Comparable<T>> boolean isSorted(List<T> a) {
        for (int i = 1; i < a.size(); i++) {
            if (less(a.get(i), a.get(i - 1))) return false;
        }

        return true;
    }
}
