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
 * 循环队列
 * <p>
 * 如果不用size判断，当队列满时，last指向的位置实际上是没有存储数据的。
 * 所以，循环队列会浪费一个数组的存储空间
 *
 * @author mingle
 */
public class CircularQueue<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final Object[] queue;
    private int first = 0;
    private int last = 0;
    private int size;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int capacity) {
        queue = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public int size() {
        return size;
    }

    public boolean enqueue(E item) {
        if ((last + 1) % queue.length == first) {
            return false;
        }
        queue[last] = item;

        size++;
        last = (last + 1) % queue.length;

        return true;
    }

    @SuppressWarnings("unchecked")
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue underflow");
        }

        E item = (E) queue[first];
        queue[first] = null;
        size--;
        first = (first + 1) % queue.length;

        return item;
    }

    @Override public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    @Override public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {
        private int i = 0;

        public boolean hasNext() {
            return i < queue.length;
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
        CircularQueue<Integer> queue = new CircularQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue);
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        System.out.println(queue);
    }
}
