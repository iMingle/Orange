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

package org.orange.arithmetic.sort.priorityQueue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 二项队列，合并，插入和删除(最坏O(logN))
 * <p>
 * The BinomialMinPriorityQueue class represents a priority queue of generic keys.
 * It supports the usual insert and delete-the-minimum operations,
 * along with the merging of two heaps together.
 * It also supports methods for peeking at the minimum key,
 * testing if the priority queue is empty, and iterating through
 * the keys.
 * It is possible to build the priority queue using a Comparator.
 * If not, the natural order relation between the keys will be used.
 * <p>
 * This implementation uses a binomial heap.
 * The insert, delete-the-minimum, union, min-key
 * and size operations take logarithmic time.
 * The is-empty and constructor operations take constant time.
 *
 * @author mingle
 */
public class BinomialMinPriorityQueue<K> implements Iterable<K> {
    private Node head;                    //head of the list of roots
    private final Comparator<K> comp;    //Comparator over the keys

    //Represents a Node of a Binomial Tree
    private class Node {
        K key;                        // Key contained by the Node
        int order;                      // The order of the Binomial Tree rooted by this Node
        Node child, sibling;            // child and sibling of this Node
    }

    /**
     * Initializes an empty priority queue
     * Worst case is O(1)
     */
    public BinomialMinPriorityQueue() {
        comp = new MyComparator();
    }

    /**
     * Initializes an empty priority queue using the given Comparator
     * Worst case is O(1)
     *
     * @param comparator a comparator over the keys
     */
    public BinomialMinPriorityQueue(Comparator<K> comparator) {
        comp = comparator;
    }

    /**
     * Initializes a priority queue with given keys
     * Worst case is O(n*log(n))
     *
     * @param a an array of keys
     */
    public BinomialMinPriorityQueue(K[] a) {
        comp = new MyComparator();
        for (K k : a) insert(k);
    }

    /**
     * Initializes a priority queue with given keys using the given Comparator
     * Worst case is O(n*log(n))
     *
     * @param comparator a comparator over the keys
     * @param a          an array of keys
     */
    public BinomialMinPriorityQueue(Comparator<K> comparator, K[] a) {
        comp = comparator;
        for (K k : a) insert(k);
    }

    /**
     * Whether the priority queue is empty
     * Worst case is O(1)
     *
     * @return true if the priority queue is empty, false if not
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Number of elements currently on the priority queue
     * Worst case is O(log(n))
     *
     * @return the number of elements on the priority queue
     * @throws java.lang.ArithmeticException if there are more than 2^63-1 elements in the queue
     */
    public int size() {
        int result = 0, tmp;
        for (Node node = head; node != null; node = node.sibling) {
            if (node.order > 30) {
                throw new ArithmeticException("The number of elements cannot be evaluated, but the priority queue is "
                        + "still valid.");
            }
            tmp = 1 << node.order;
            result |= tmp;
        }
        return result;
    }

    /**
     * Puts a Key in the heap
     * Worst case is O(log(n))
     *
     * @param key a Key
     */
    public void insert(K key) {
        Node x = new Node();
        x.key = key;
        x.order = 0;
        BinomialMinPriorityQueue<K> H = new BinomialMinPriorityQueue<>(); //The Comparator oh the H heap is not used
        H.head = x;
        this.head = this.union(H).head;
    }

    /**
     * Get the minimum key currently in the queue
     * Worst case is O(log(n))
     *
     * @return the minimum key currently in the priority queue
     * @throws java.util.NoSuchElementException if the priority queue is empty
     */
    public K minKey() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue is empty");
        Node min = head;
        Node current = head;
        while (current.sibling != null) {
            min = (greater(min.key, current.sibling.key)) ? current : min;
            current = current.sibling;
        }
        return min.key;
    }

    /**
     * Deletes the minimum key
     * Worst case is O(log(n))
     *
     * @return the minimum key
     * @throws java.util.NoSuchElementException if the priority queue is empty
     */
    public K delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue is empty");
        Node min = eraseMin();
        Node x = (min.child == null) ? min : min.child;
        if (min.child != null) {
            min.child = null;
            Node prevx = null, nextx = x.sibling;
            while (nextx != null) {
                x.sibling = prevx;
                prevx = x;
                x = nextx;
                nextx = nextx.sibling;
            }
            x.sibling = prevx;
            BinomialMinPriorityQueue<K> H = new BinomialMinPriorityQueue<>();
            H.head = x;
            head = union(H).head;
        }
        return min.key;
    }

    /**
     * Merges two Binomial heaps together
     * This operation is destructive
     * Worst case is O(log(n))
     *
     * @param heap a Binomial Heap to be merged with the current heap
     * @return the union of two heaps
     * @throws java.lang.IllegalArgumentException if the heap in parameter is null
     */
    public BinomialMinPriorityQueue<K> union(BinomialMinPriorityQueue<K> heap) {
        if (heap == null) throw new IllegalArgumentException("Cannot merge a Binomial Heap with null");
        this.head = merge(new Node(), this.head, heap.head).sibling;
        Node x = this.head;
        Node prevx = null, nextx = x.sibling;
        while (nextx != null) {
            if (x.order < nextx.order ||
                    (nextx.sibling != null && nextx.sibling.order == x.order)) {
                prevx = x;
                x = nextx;
            } else if (greater(nextx.key, x.key)) {
                x.sibling = nextx.sibling;
                link(nextx, x);
            } else {
                if (prevx == null) {
                    this.head = nextx;
                } else {
                    prevx.sibling = nextx;
                }
                link(x, nextx);
                x = nextx;
            }
            nextx = x.sibling;
        }
        return this;
    }

    //Compares two keys
    private boolean greater(K n, K m) {
        if (n == null) return false;
        if (m == null) return true;
        return comp.compare(n, m) > 0;
    }

    //Assuming root1 holds a greater key than root2, root2 becomes the new root
    private void link(Node root1, Node root2) {
        root1.sibling = root2.child;
        root2.child = root1;
        root2.order++;
    }

    //Deletes and return the node containing the minimum key
    private Node eraseMin() {
        Node min = head;
        Node previous = null;
        Node current = head;
        while (current.sibling != null) {
            if (greater(min.key, current.sibling.key)) {
                previous = current;
                min = current.sibling;
            }
            current = current.sibling;
        }
        previous.sibling = min.sibling;
        if (min == head) head = min.sibling;
        return min;
    }

    //Merges two root lists into one, there can be up to 2 Binomial Trees of same order
    private Node merge(Node h, Node x, Node y) {
        if (x == null && y == null) return h;
        else if (x == null) h.sibling = merge(y, null, y.sibling);
        else if (y == null) h.sibling = merge(x, x.sibling, null);
        else if (x.order < y.order) h.sibling = merge(x, x.sibling, y);
        else h.sibling = merge(y, x, y.sibling);
        return h;
    }

    /**
     * Gets an Iterator over the keys in the priority queue in ascending order
     * The Iterator does not implement the remove() method
     * iterator() : Worst case is O(n)
     * next() : 	Worst case is O(log(n))
     * hasNext() : 	Worst case is O(1)
     *
     * @return an Iterator over the keys in the priority queue in ascending order
     */
    public Iterator<K> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<K> {
        BinomialMinPriorityQueue<K> data;

        //Constructor clones recursively the elements in the queue
        //It takes linear time
        public MyIterator() {
            data = new BinomialMinPriorityQueue<>(comp);
            data.head = clone(head, false, false, null);
        }

        private Node clone(Node x, boolean isParent, boolean isChild, Node parent) {
            if (x == null) return null;
            Node node = new Node();
            node.key = x.key;
            node.sibling = clone(x.sibling, false, false, parent);
            node.child = clone(x.child, false, true, node);
            return node;
        }

        public boolean hasNext() {
            return !data.isEmpty();
        }

        public K next() {
            if (!hasNext()) throw new NoSuchElementException();
            return data.delMin();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class MyComparator implements Comparator<K> {
        @SuppressWarnings("unchecked") @Override
        public int compare(K key1, K key2) {
            return ((Comparable<K>) key1).compareTo(key2);
        }
    }
}
