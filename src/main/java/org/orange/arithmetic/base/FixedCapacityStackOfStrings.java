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

package org.orange.arithmetic.base;

public class FixedCapacityStackOfStrings {
    private String[] a;
    private int N;
    
    public String[] getA() {
        return a;
    }

    public void setA(String[] a) {
        this.a = a;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public FixedCapacityStackOfStrings(int capacity) {
        a = new String[capacity];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public boolean isFull() {
        return N == this.a.length;
    }
    
    public int size() {
        return N;
    }
    
    public void push(String item) {
        a[N++] = item;
    }
    
    public String pop() {
        return a[--N];
    }
}
