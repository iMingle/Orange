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

package org.orange.arithmetic.base.stack;

import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    private void resize(int max) {
        @SuppressWarnings("unchecked")
        Item[] temp = (Item[]) new Object[max];

        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }

        a = temp;
    }

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int capacity) {
        a = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }

        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;

        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    @Override public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    @SuppressWarnings("unused")
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }

        public void remove() {
        }
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> s = new FixedCapacityStack<>(100);
        FixedCapacityStack<Integer> i = new FixedCapacityStack<>(100);

        s.push("jin");
//        System.out.println(s.pop());

        for (int j = 0; j < 10; j++) {
            i.push(j);
        }

        FixedCapacityStack<String> str = new FixedCapacityStack<>(100);

        for (int j = 0; j < 10; j++) {
            str.push(j + "");
        }

        Iterator<String> it = str.iterator();

        while (it.hasNext()) {
            String str1 = it.next();

            System.out.println(str1);
            it.remove();
        }

        System.out.println(it.hasNext());

        while (it.hasNext()) {
            String str2 = it.next();
            System.out.println(str2);
        }
    }
}
