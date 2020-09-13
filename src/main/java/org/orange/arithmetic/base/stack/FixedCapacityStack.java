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

public class FixedCapacityStack<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] stack;
    private int size;

    private void resize(int max) {
        Object[] temp = new Object[max];

        for (int i = 0; i < size; i++) {
            temp[i] = stack[i];
        }

        stack = temp;
    }

    public FixedCapacityStack() {
        this(DEFAULT_CAPACITY);
    }

    public FixedCapacityStack(int capacity) {
        stack = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(E item) {
        if (size == stack.length) {
            resize(2 * stack.length);
        }

        stack[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        E item = (E) stack[--size];
        stack[size] = null;

        if (size > 0 && size == stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    @Override public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<E> {
        private int i = size;

        public boolean hasNext() {
            return i > 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            return (E) stack[--i];
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
