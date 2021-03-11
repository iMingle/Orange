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

import java.util.Objects;

/**
 * @author mingle
 */
public class AbcOfLinkedList {
    public static void main(String[] args) {
        LList<Integer> l1 = new LList<>();
        l1.addLast(1);
        l1.addLast(2);
        l1.addLast(3);

//        System.out.println(l1.palindrome());

        LList<Integer> l2 = new LList<>();
        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(4);
        l2.addLast(6);
        l2.addLast(8);
        l2.addLast(9);

        System.out.println(l1.merge(l2.getFirst()));

        System.out.println(l2.reverse());

        LList<Integer> circle = new LList<>();
        LList.Node<Integer> head = new LList.Node<>(1, null);
        LList.Node<Integer> tail = new LList.Node<>(4, null);
        LList.Node<Integer> c = new LList.Node<>(2, new LList.Node<>(3, tail));
        head.next = c;
        tail.next = head;
        circle.of(head);
        System.out.println("是否有环: " + circle.circle());

        l2 = new LList<>();
        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(4);
        l2.addLast(6);
        l2.addLast(8);
        l2.addLast(9);

        System.out.println(l2.middle());
        l2.removeLastNth(3);
        System.out.println(l2.getFirst());
        System.out.println(l2.middle());
    }

    static class LList<E extends Comparable<? super E>> {
        private Node<E> first;
        private int size;

        public void of(Node<E> first) {
            this.first = first;
        }

        public Node<E> getFirst() {
            return first;
        }

        public Node<E> middle() {
            if (Objects.isNull(first))
                return null;

            Node<E> p = first;
            Node<E> q = first;
            while (Objects.nonNull(q) && Objects.nonNull(q.next)) {
                p = p.next;
                q = q.next.next;
            }

            return p;
        }

        public void removeLastNth(int n) {
            if (n < 0 || n > size)
                throw new IllegalArgumentException();

            Node<E> f = first;
            for (int i = 0; i <= n; i++) {
                f = f.next;
            }

            Node<E> p = first;
            Node<E> q = f;
            while (Objects.nonNull(q)) {
                p = p.next;
                q = q.next;
            }

            Node<E> next = p.next;
            p.next = p.next.next;
            next.next = next;
        }

        public Node<E> merge(Node<E> second) {
            if (Objects.isNull(second))
                return first;

            Node<E> dummy = new Node<>(null, null);
            Node<E> next = dummy;
            Node<E> p = first;
            Node<E> q = second;
            while (Objects.nonNull(p) && Objects.nonNull(q)) {
                if (p.item.compareTo(q.item) <= 0) {
                    next.next = p;
                    p = p.next;
                } else {
                    next.next = q;
                    q = q.next;
                }

                next = next.next;
            }

            if (Objects.nonNull(p)) {
                next.next = p;
            }

            if (Objects.nonNull(q)) {
                next.next = q;
            }

            return dummy.next;
        }

        public boolean circle() {
            final Node<E> f = first;
            if (Objects.isNull(f) || Objects.isNull(f.next))
                return false;

            Node<E> p = first;
            Node<E> q = first;
            while (Objects.nonNull(q) && Objects.nonNull(q.next)) {
                p = p.next;
                q = q.next.next;
                if (p == q)
                    return true;
            }

            return false;
        }

        public Node<E> reverse() {
            final Node<E> f = first;
            if (Objects.isNull(f) || Objects.isNull(f.next))
                return f;

            Node<E> next;
            Node<E> prev = null;
            Node<E> p = first;
            while (Objects.nonNull(p)) {
                next = p.next;
                p.next = prev;
                prev = p;
                first = prev;
                p = next;
            }

            return first;
        }

        public void addFirst(E element) {
            first = new Node<>(element, first);
            size++;
        }

        public void addLast(E element) {
            Node<E> f = first;
            if (Objects.isNull(f)) {
                first = new Node<>(element, first);
                size++;
                return;
            }

            while (Objects.nonNull(f)) {
                if (Objects.nonNull(f.next)) {
                    f = f.next;
                } else {
                    f.next = new Node<>(element, null);
                    size++;
                    break;
                }
            }
        }

        public boolean palindrome() {
            if (Objects.isNull(first))
                return false;
            if (Objects.isNull(first.next))
                return true;

            Node<E> p = first;
            Node<E> q = first;
            Node<E> prev = null;
            Node<E> next;

            while (Objects.nonNull(q) && Objects.nonNull(q.next)) {
                q = q.next.next;
                next = p.next;
                p.next = prev;
                prev = p;
                p = next;
            }

            if (Objects.isNull(q)) {
                return same(prev, p);
            } else {
                return same(prev, p.next);
            }
        }

        public boolean same(Node<E> first, Node<E> second) {
            if (Objects.isNull(first) && Objects.isNull(second))
                return true;
            if (Objects.isNull(first) || Objects.isNull(second))
                return false;

            Node<E> x = first;
            Node<E> y = second;

            while (Objects.nonNull(x) && Objects.nonNull(y)) {
                if ((Objects.isNull(x.item) && Objects.isNull(y.item))
                        || (Objects.nonNull(x.item) && x.item.equals(y.item))) {
                    x = x.next;
                    y = y.next;
                } else {
                    return false;
                }
            }

            return Objects.isNull(x) && Objects.isNull(y);
        }

        static class Node<E> {
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
    }
}
