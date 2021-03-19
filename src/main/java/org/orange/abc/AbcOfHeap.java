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

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author mingle
 */
public class AbcOfHeap<V extends Comparable<? super V>> {
    private Object[] elementData;
    private int n;

    public AbcOfHeap() {
        this(1);
    }

    public AbcOfHeap(int capacity) {
        this.elementData = new Object[capacity];
        this.n = 0;
    }

    public AbcOfHeap(V[] values) {
        this.n = values.length;
        this.elementData = new Object[this.n];
        for (int i = 0; i < this.n; i++) {
            this.elementData[i] = values[i];
        }

        for (int i = ((this.n >> 1) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    public void insert(V value) {
        if (this.n >= this.elementData.length) resize(this.elementData.length << 1);
        this.elementData[++n] = value;
        swim(this.n - 1);
    }

    public V removeMax() {
        if (isEmpty()) throw new NoSuchElementException();
        V max = elementData(0);
        swap(0, --this.n);
        sink(0);

        this.elementData[n] = null;
        if (n > 0 && n == (this.elementData.length >> 2)) resize(this.elementData.length >> 1);

        return max;
    }

    private void sink(int start) {
        int N = this.n - 1;
        int li = 2 * start + 1;
        int ri = li + 1;
        int max = li;
        if (li > N) return;
        if (ri <= N && elementData(li).compareTo(elementData(ri)) < 0) {
            max = ri;
        }

        if (elementData(start).compareTo(elementData(max)) < 0) {
            swap(start, max);
            sink(max);
        }
    }

    private void swim(int k) {
        while (k > 0 && elementData(k / 2).compareTo(elementData(k)) < 0) {
            swap(k / 2, k);
            k = k / 2;
        }
    }

    private void swap(int i, int j) {
        V temp = elementData(i);
        this.elementData[i] = this.elementData[j];
        this.elementData[j] = temp;
    }

    public V max() {
        if (isEmpty()) throw new NoSuchElementException();
        return elementData(0);
    }

    @SuppressWarnings("unchecked")
    V elementData(int index) {
        return (V) elementData[index];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Object[] t = new Object[capacity];
        for (int i = 0; i < this.n; i++) {
            t[i] = elementData[i];
        }

        this.elementData = t;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 7, 4, 5, 8, 2, 3, 6, 9};

        AbcOfHeap<Integer> heap = new AbcOfHeap<>(array);

        System.out.println(Arrays.toString(heap.elementData));
        System.out.println(heap.removeMax());
        System.out.println(Arrays.toString(heap.elementData));
        System.out.println(heap.max());
    }
}
