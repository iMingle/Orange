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

package org.mingle.orange.arithmetic.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 动态数组实现
 * 
 * @author mingle
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int first = 0;
    private int last = 0;
    private int N = 0;
    
    @SuppressWarnings("unchecked")
    public ResizingArrayQueue() {
        queue = (Item[]) new Object[2];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        
        for (int i = 0; i < N; i++) {
            temp[i] = queue[(i + first) % queue.length];
        }
        
        queue = temp;
        first = 0;
        last = N;
    }
    
    public void enqueue(Item item) {
        if (N == queue.length) {
            resize(2 * queue.length);
        }
        
        queue[last++] = item;
        
        if (last == queue.length) {
            last = 0;
        }
        
        N++;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        
        Item item = queue[first];
        queue[first] = null;
        N--;
        first++;
    
        if (first == queue.length) {
            first = 0;
        }
        
        if (N > 0 && N == queue.length / 4) {
            resize(queue.length /2);
        }
        
        return item;
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> re = new ResizingArrayQueue<String>();
        re.enqueue("jin");
        re.enqueue("ming");
        re.enqueue("lei");
        re.enqueue("wang");
        re.enqueue("jian");
        re.enqueue("zong");
System.out.println(re.N);

        for (int i = 0; i < 6; i++) {
            System.out.println("kk");
            System.out.println(re.dequeue());
        }
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            Item item = queue[(i + first) % queue.length];
            i++;
            
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
