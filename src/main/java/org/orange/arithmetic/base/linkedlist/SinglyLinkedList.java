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

package org.orange.arithmetic.base.linkedlist;

import java.util.Objects;

/**
 * 单链表
 *
 * @author mingle
 */
public class SinglyLinkedList<E extends Comparable<? super E>> {
    private Node<E> first;
    private int size;

    /**
     * 用链表初始化
     *
     * @param first 链表
     */
    public void of(Node<E> first) {
        this.first = first;
    }

    /**
     * 从头部添加节点
     *
     * @param e 节点
     */
    public void addFirst(E e) {
        final Node<E> f = first;
        first = new Node<>(e, f);
        size++;
    }

    /**
     * 从尾部添加节点
     *
     * @param e 节点
     */
    public void addLast(E e) {
        Node<E> f = first;
        if (Objects.isNull(f)) {
            first = new Node<>(e, null);
            size++;
            return;
        }

        while (Objects.nonNull(f)) {
            if (Objects.nonNull(f.next)) {
                f = f.next;
                continue;
            }
            f.next = new Node<>(e, null);
            size++;
            break;
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * linked list inverse
     */
    public void inverse() {
        Node<E> f = first;
        if (Objects.isNull(f) || Objects.isNull(f.next)) {
            return;
        }

        Node<E> prev = null;
        Node<E> next;

        while (Objects.nonNull(f.next)) {
            next = f.next;
            f.next = prev;
            prev = f;
            f = next;
        }
        f.next = prev;

        first = f;
    }

    /**
     * linked list inverse
     */
    public void inverse1() {
        Node<E> f = first;
        if (Objects.isNull(f) || Objects.isNull(f.next)) {
            return;
        }

        Node<E> prev = null;
        Node<E> next;

        while (Objects.nonNull(f)) {
            next = f.next;
            f.next = prev;
            prev = f;
            f = next;
        }

        first = prev;
    }

    /**
     * if has circle
     *
     * @return true has circle
     */
    public boolean circle() {
        Node<E> p = first;
        Node<E> q = first;

        while (Objects.nonNull(q) && Objects.nonNull(q.next)) {
            p = p.next;
            q = q.next.next;

            if (p == q) {
                return true;
            }
        }

        return false;
    }

    /**
     * 回文结构判断
     * 1. 空间复杂度为O(1)
     * 2. 时间复杂度为O(n)
     *
     * @return true 回文结构
     */
    public boolean palindrome() {
        if (Objects.isNull(first)) {
            return false;
        }

        // 只有一个元素
        if (Objects.isNull(first.next)) {
            return true;
        }

        Node<E> prev = null;
        Node<E> next;
        Node<E> p = first;
        Node<E> q = first;
        while (Objects.nonNull(q) && Objects.nonNull(q.next)) {
            q = q.next.next;
            next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }

        // 偶数个节点
        if (Objects.isNull(q)) {
            return isSame(prev, p);
        } else {
            return isSame(prev, p.next);
        }
    }

    /**
     * if one linked list is same as another
     *
     * @param first  first linked list
     * @param second second linked list
     * @return true first is same as second
     */
    public boolean isSame(Node<E> first, Node<E> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);

        Node<E> f = first;
        Node<E> s = second;
        boolean same = true;

        while (Objects.nonNull(f) && Objects.nonNull(s)) {
            if (f.item.equals(s.item)) {
                f = f.next;
                s = s.next;
            } else {
                same = false;
                break;
            }
        }

        return same && (Objects.isNull(f) && Objects.isNull(s));
    }

    /**
     * merge two sorted list
     *
     * @param second another sorted list
     * @return sorted list
     */
    public Node<E> merge(Node<E> second) {
        // 哨兵节点
        Node<E> dummy = new Node<>(null, null);
        Node<E> next = dummy;
        Node<E> fh = first;
        Node<E> sh = second;
        while (Objects.nonNull(fh) && Objects.nonNull(sh)) {
            if (fh.item.compareTo(sh.item) <= 0) {
                next.next = fh;
                fh = fh.next;
            } else {
                next.next = sh;
                sh = sh.next;
            }

            next = next.next;
        }

        if (Objects.nonNull(fh)) {
            next.next = fh;
        }
        if (Objects.nonNull(sh)) {
            next.next = sh;
        }

        return dummy.next;
    }

    /**
     * delete last nth node
     * 1. 空间复杂度为O(1)
     * 2. 时间复杂度为O(n)
     *
     * @param n nth
     */
    public void removeLastNth(int n) {
        if (n < 0 || n > size) {
            throw new IllegalArgumentException();
        }

        Node<E> dummy = new Node<>(null, first);
        Node<E> first = dummy;
        Node<E> second = dummy;

        // the gap between first and second is n nodes
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }

        while (Objects.nonNull(first)) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        this.first = dummy.next;
    }

    public Node<E> middle() {
        if (Objects.isNull(first)) {
            return null;
        }

        Node<E> p = first;
        Node<E> q = first;
        while (Objects.nonNull(q) && Objects.nonNull(q.next)) {
            p = p.next;
            q = q.next.next;
        }

        return p;
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node<E> x = first;
        if (Objects.isNull(x)) {
            return sb.append("]").toString();
        }
        while (Objects.nonNull(x)) {
            sb.append(x.item);
            if (Objects.isNull(x.next)) {
                sb.append("]");
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

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Node<E> x = this;

            while (Objects.nonNull(x)) {
                sb.append(x.item);
                if (Objects.isNull(x.next)) {
                    sb.append("]");
                    break;
                }
                sb.append(", ");
                x = x.next;
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(2);
        list.addLast(2);
        list.addLast(1);
        System.out.println("list: " + list);
        System.out.println("回文: " + list.palindrome());

        Node<Integer> one = new Node<>(1, new Node<>(2, new Node<>(3, null)));
        Node<Integer> two = new Node<>(1, new Node<>(2, null));

        System.out.println("one: " + one);
        System.out.println("two: " + two);
        System.out.println("one is same two: " + list.isSame(one, two));

        SinglyLinkedList<Integer> inverse = new SinglyLinkedList<>();
        inverse.addLast(1);
        inverse.addLast(2);
        inverse.addLast(3);
        inverse.addLast(4);
        inverse.addLast(5);
        System.out.println("inverse original: " + inverse);
        inverse.inverse();
        System.out.println("inverse: " + inverse);
        inverse.inverse1();
        System.out.println("inverse: " + inverse);

        SinglyLinkedList<Integer> circle = new SinglyLinkedList<>();
        Node<Integer> head = new Node<>(1, null);
        Node<Integer> tail = new Node<>(4, null);
        Node<Integer> c = new Node<>(2, new Node<>(3, tail));
        head.next = c;
        tail.next = head;
        circle.of(head);
        System.out.println("是否有环: " + circle.circle());

        SinglyLinkedList<Integer> merge = new SinglyLinkedList<>();
        Node<Integer> first = new Node<>(1, new Node<>(3, new Node<>(5, null)));
        merge.of(first);
        Node<Integer> second = new Node<>(1, new Node<>(2, new Node<>(6, new Node<>(7, null))));

        System.out.println("first: " + first);
        System.out.println("second: " + second);
        System.out.println("merge: " + merge.merge(second));

        SinglyLinkedList<Integer> removeNth = new SinglyLinkedList<>();
        removeNth.addLast(1);
        removeNth.addLast(2);
        removeNth.addLast(3);
        removeNth.addLast(4);
        System.out.println("removeLastNth before: " + removeNth);
        removeNth.removeLastNth(4);
        System.out.println("removeLastNth after: " + removeNth);

        SinglyLinkedList<Integer> middle = new SinglyLinkedList<>();
        middle.addLast(1);
        System.out.println("middle: " + middle.middle().item);
        middle.addLast(2);
        System.out.println("middle: " + middle.middle().item);
        middle.addLast(3);
        System.out.println("middle: " + middle.middle().item);
        middle.addLast(4);
        System.out.println("middle: " + middle.middle().item);
    }
}
