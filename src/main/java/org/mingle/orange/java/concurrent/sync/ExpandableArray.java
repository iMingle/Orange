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

package org.mingle.orange.java.concurrent.sync;

import java.util.NoSuchElementException;

/**
 * 完全同步对象,简化版本的java.util.Vector
 * 
 * @author mingle
 */
public class ExpandableArray {
    protected Object[] data; // the elements
    protected int size = 0; // the number of array slots used

    // INV: 0 <= size <= data.length

    public ExpandableArray(int cap) {
        data = new Object[cap];
    }

    public synchronized int size() {
        return size;
    }

    public synchronized Object get(int i) // subscripted access
            throws NoSuchElementException {
        if (i < 0 || i >= size)
            throw new NoSuchElementException();

        return data[i];
    }

    public synchronized void add(Object x) { // add at end
        if (size == data.length) { // need a bigger array
            Object[] olddata = data;
            data = new Object[3 * (size + 1) / 2];
            System.arraycopy(olddata, 0, data, 0, olddata.length);
        }
        data[size++] = x;
    }

    public synchronized void removeLast() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException();

        data[--size] = null;
    }
}
