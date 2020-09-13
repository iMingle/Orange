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

/**
 * 下压栈-可以动态调整数组大小的实现
 *
 * @author mingle
 */
public class ResizingArrayStack<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] stack;
    private int size = 0;

    public ResizingArrayStack() {
        stack = new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int max) {
        Object[] temp = new Object[max];

        for (int i = 0; i < size; i++) {
            temp[i] = stack[i];
        }

        stack = temp;
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
        private int item = size;

        public boolean hasNext() {
            return item > 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            return (E) stack[--item];
        }

        public void remove() {
        }
    }
}
