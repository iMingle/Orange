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

/**
 * 链表实现Queue
 *
 * @author mingle
 */
public class Queue<E> implements Iterable<E> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        E item;
        Node next;
    }

    public boolean isEmpty() {
        return null == first;
    }

    public int size() {
        return N;
    }

    public void enqueue(E item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        N++;
    }

    public E dequeue() {
        E item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;

        return item;
    }

    @Override public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            E item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }
}
