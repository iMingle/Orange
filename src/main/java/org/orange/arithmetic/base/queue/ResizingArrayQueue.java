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

package org.orange.arithmetic.base.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 动态数组实现
 *
 * @author mingle
 */
public class ResizingArrayQueue<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] queue;
    private int first = 0;
    private int last = 0;
    private int size = 0;

    public ResizingArrayQueue() {
        queue = new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int max) {
        assert max >= size;
        Object[] temp = new Object[max];

        for (int i = 0; i < size; i++) {
            temp[i] = queue[(i + first) % queue.length];
        }

        queue = temp;
        first = 0;
        last = size;
    }

    public void enqueue(E item) {
        if (size == queue.length) {
            resize(2 * queue.length);
        }

        queue[last++] = item;

        if (last == queue.length) {
            last = 0;
        }

        size++;
    }

    @SuppressWarnings("unchecked")
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        E item = (E) queue[first];
        queue[first] = null;
        size--;
        first++;

        if (first == queue.length) {
            first = 0;
        }

        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }

        return item;
    }

    @Override public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {
        private int i = 0;

        public boolean hasNext() {
            return i < size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E item = (E) queue[(i + first) % queue.length];
            i++;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> re = new ResizingArrayQueue<>();
        re.enqueue("jin");
        re.enqueue("ming");
        re.enqueue("lei");
        re.enqueue("wang");
        re.enqueue("jian");
        re.enqueue("zong");
        System.out.println(re.size);

        for (int i = 0; i < 6; i++) {
            System.out.println("kk");
            System.out.println(re.dequeue());
        }
    }
}
