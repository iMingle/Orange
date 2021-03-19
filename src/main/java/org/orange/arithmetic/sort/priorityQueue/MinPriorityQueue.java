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

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code MinPriorityQueue} class represents a priority queue of generic keys.
 * It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 * operations, along with methods for peeking at the minimum key,
 * testing if the priority queue is empty, and iterating through
 * the keys.
 * <p>
 * This implementation uses a binary heap.
 * The <em>insert</em> and <em>delete-the-minimum</em> operations take
 * logarithmic amortized time.
 * The <em>min</em>, <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes time proportional to the specified capacity or the number of
 * items used to initialize the data structure.
 * <p>
 *
 * @param <K> the generic type of key on this priority queue
 * @author mingle
 */
public class MinPriorityQueue<K> implements Iterable<K> {
    private K[] pq;                    // store items at indices 1 to n
    private int n;                     // number of items on priority queue
    private Comparator<K> comparator;  // optional comparator

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    @SuppressWarnings("unchecked") public MinPriorityQueue(int initCapacity) {
        pq = (K[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MinPriorityQueue() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param initCapacity the initial capacity of this priority queue
     * @param comparator   the order to use when comparing keys
     */
    @SuppressWarnings("unchecked") public MinPriorityQueue(int initCapacity, Comparator<K> comparator) {
        this.comparator = comparator;
        pq = (K[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator the order to use when comparing keys
     */
    public MinPriorityQueue(Comparator<K> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * <p>
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    @SuppressWarnings("unchecked") public MinPriorityQueue(K[] keys) {
        n = keys.length;
        pq = (K[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i + 1] = keys[i];
        for (int k = n / 2; k >= 1; k--)
            sink(k);
        assert isMinHeap();
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public K min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
        assert capacity > n;
        @SuppressWarnings("unchecked") K[] temp = (K[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param x the key to add to this priority queue
     */
    public void insert(K x) {
        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public K delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        exch(1, n);
        K min = pq[n--];
        sink(1);
        pq[n + 1] = null;         // avoid loitering and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    @SuppressWarnings("unchecked") private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<K>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        K swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..N] a min heap?
    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeap(int k) {
        if (k > n) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && greater(k, left)) return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }

    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in ascending order.
     * <p>
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    @Override
    public Iterator<K> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<K> {
        // create a new pq
        private final MinPriorityQueue<K> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) copy = new MinPriorityQueue<>(size());
            else copy = new MinPriorityQueue<>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public K next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }

    public static void main(String[] args) {
        Integer[] array = {1, 7, 4, 5, 8, 2, 3, 6, 9};

        MinPriorityQueue<Integer> heap = new MinPriorityQueue<>(array);

        System.out.println(Arrays.toString(heap.pq));
        System.out.println(heap.delMin());
        System.out.println(Arrays.toString(heap.pq));
        System.out.println(heap.min());
    }
}
