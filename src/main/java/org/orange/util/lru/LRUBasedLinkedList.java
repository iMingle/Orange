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

package org.orange.util.lru;

import java.util.Objects;

/**
 * 基于链表实现的LRU缓存
 * 1. 空间复杂度为O(1)
 * 2. 添加元素时间复杂度为O(1)
 * 3. 缓存访问时间复杂度为O(n)
 *
 * @author mingle
 */
public class LRUBasedLinkedList<E> {
    private static final int DEFAULT_CAPACITY = (1 << 3);

    private Node<E> first;
    private final int capacity;
    private int size;

    public LRUBasedLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedLinkedList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must > 0");
        }
        this.capacity = capacity;
    }

    public void offer(E element) {
        if (Objects.isNull(first)) {
            first = new Node<>(element, null);
            size++;
            return;
        }

        Node<E> x = first;
        Node<E> prev = x;
        boolean exist = false;

        while (Objects.nonNull(x)) {
            if ((Objects.isNull(x.item) && Objects.isNull(element))
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

        // 第一个元素匹配
        if (prev == x && exist) {
            return;
        }

        if (exist) {
            Node<E> t = x.next;
            x.next = first;
            first = x;
            prev.next = t;
        } else {
            first = new Node<>(element, first);
            if (isFull()) {
                prev.next = null;
            } else {
                size++;
            }
        }
    }

    public boolean contains(E o) {
        if (Objects.isNull(first))
            return false;

        Node<E> x = first;
        while (Objects.nonNull(x)) {
            if (x.item.equals(o)) {
                return true;
            }

            x = x.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder("LRUBasedLinkedList{elementData=");
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

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBasedLinkedList<Integer> lru = new LRUBasedLinkedList<>(4);
        lru.offer(null);
        System.out.println(lru);
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
        lru.offer(null);
        System.out.println(lru);
        lru.offer(4);
        System.out.println(lru);
        lru.offer(null);
        System.out.println(lru);
        lru.offer(7);
        System.out.println(lru);
        lru.offer(1);
        System.out.println(lru);
        lru.offer(2);
        System.out.println(lru);
    }
}
