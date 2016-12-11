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

/**
 * 下压栈-可以动态调整数组大小的实现
 * 
 * @author mingle
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    @SuppressWarnings("unchecked")
    private Item[] a = (Item[]) new Object();
    private int N = 0;
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private void resize(int max) {
        @SuppressWarnings("unchecked")
        Item[] temp = (Item[])new Object[max];
        
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        
        a = temp;
    }
    
    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        
        a[N++] = item;
    }
    
    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Iterator<Item> iterator() {
        return (Iterator<Item>) new ResizingArrayStack();
    }
    
    @SuppressWarnings("unused")
    private class ReverseArrayIterator implements Iterator<Item> {
        private int item = N;

        public boolean hasNext() {
            return item > 0;
        }

        public Item next() {
            return a[--item];
        }

        public void remove() {
        }
    }

    public static void main(String[] args) {

    }

}
