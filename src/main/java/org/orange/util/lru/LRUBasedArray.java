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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 基于数组实现的LRU缓存
 * 1. 空间复杂度为O(n)
 * 2. 时间复杂度为O(n)
 *
 * @author mingle
 */
public class LRUBasedArray<E> {
    private static final int DEFAULT_CAPACITY = (1 << 3);

    private final Object[] elementData;
    private final int capacity;
    private int size;
    private final Map<Object, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity) {
        this.capacity = capacity;
        this.elementData = new Object[capacity];
        this.holder = new HashMap<>(capacity);
    }

    public void offer(E element) {
        Integer index = holder.get(element);
        if (Objects.isNull(index)) {
            if (isFull()) {
                Object last = elementData[elementData.length - 1];
                holder.remove(last);
                elementData[elementData.length - 1] = element;
                moveToFront(elementData.length - 1);
            } else {
                elementData[size] = element;
                moveToFront(size);
                size++;
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

        for (int i = index; i > 0; --i) {
            elementData[i] = elementData[i - 1];
            holder.put(elementData[i], i);
        }

        elementData[0] = e;
        holder.put(e, 0);
    }

    public boolean contains(E o) {
        return holder.containsKey(o);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    @Override public String toString() {
        return "LRUBasedArray{" + "elementData=" + Arrays.toString(elementData) + '}';
    }

    public static void main(String[] args) {
        LRUBasedArray<Integer> lru = new LRUBasedArray<>(4);
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
