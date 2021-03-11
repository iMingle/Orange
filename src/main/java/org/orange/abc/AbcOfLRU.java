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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author mingle
 */
public class AbcOfLRU {
    public static void main(String[] args) {
        LRUList<Integer> lru = new LRUList<>(4);
        lru.offer(1);
        System.out.println(lru);
        lru.offer(1);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
        lru.offer(3);
        System.out.println(lru);
        lru.offer(4);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
        lru.offer(4);
        System.out.println(lru);
        lru.offer(7);
        System.out.println(lru);
        lru.offer(1);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
    }

    static class LRUList<E> {
        private Node<E> first;
        private int size;
        private final int capacity;

        LRUList(int capacity) {
            this.capacity = capacity;
        }

        public void offer(E element) {
            if (Objects.isNull(first)) {
                first = new Node<>(element, null);
                size++;
                return;
            }

            Node<E> x = first;
            Node<E> prev = first;
            boolean exist = false;

            while (Objects.nonNull(x)) {
                if ((Objects.isNull(element) && Objects.isNull(x.item))
                        || (Objects.nonNull(x.item) && x.item.equals(element))) {
                    exist = true;
                    break;
                }

                if (Objects.isNull(x.next)) {
                    break;
                }

                prev = x;
                x = x.next;
            }

            if (prev == x && exist) {
                return;
            }

            if (exist) {
                prev.next = x.next;
                x.next = first;
                first = x;
            } else {
                first = new Node<>(element, first);
                if (isFull()) {
                    prev.next = null;
                } else {
                    size++;
                }
            }
        }

        public boolean isFull() {
            return this.size == this.capacity;
        }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder("LRULinkedList{elementData=");
            sb.append('[');
            Node<E> x = first;
            while (Objects.nonNull(x)) {
                sb.append(x.item);
                if (Objects.isNull(x.next)) {
                    sb.append("]}");
                    break;
                }
                sb.append(", ");
                x = x.next;
            }

            return sb.toString();
        }

        static class Node<E> {
            E item;
            Node<E> next;

            Node(E element, Node<E> next) {
                this.item = element;
                this.next = next;
            }
        }
    }

    static class LRUArray<E> {
        private final Object[] elementData;
        private final Map<Object, Integer> holder;
        private int size;
        private final int capacity;

        LRUArray(int capacity) {
            this.elementData = new Object[capacity];
            this.capacity = capacity;
            this.holder = new HashMap<>(capacity);
        }

        public void offer(E element) {
            Integer index = holder.get(element);
            if (Objects.isNull(index)) {
                if (isFull()) {
                    elementData[elementData.length - 1] = element;
                    moveToFront(elementData.length - 1);
                } else {
                    elementData[size++] = element;
                    moveToFront(size - 1);
                }
            } else {
                moveToFront(index);
            }
        }

        private void moveToFront(int index) {
            Object e = elementData[index];
            if (index == 0) {
                holder.put(e, 0);
                return;
            }

            for (int i = index; i > 0; i--) {
                elementData[i] = elementData[i - 1];
                holder.put(elementData[i], i);
            }

            elementData[0] = e;
            holder.put(e, 0);
        }

        public boolean isFull() {
            return this.size == this.capacity;
        }

        @Override public String toString() {
            return "LRUArray{" + "elementData=" + Arrays.toString(elementData) + '}';
        }
    }
}
