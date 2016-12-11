/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.java.concurrent.state;

/**
 * 缓冲区
 * 
 * @author mingle
 */
public class BoundedBufferWithStateTracking {
    protected final Object[] array; // the elements
    protected int putPtr = 0; // circular indices
    protected int takePtr = 0;
    protected int usedSlots = 0; // the count

    public BoundedBufferWithStateTracking(int capacity)
            throws IllegalArgumentException {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        array = new Object[capacity];
    }

    public synchronized int size() {
        return usedSlots;
    }

    public int capacity() {
        return array.length;
    }

    public synchronized void put(Object x) throws InterruptedException {
        while (usedSlots == array.length)
            // wait until not full
            wait();

        array[putPtr] = x;
        putPtr = (putPtr + 1) % array.length; // cyclically inc

        if (usedSlots++ == 0) // signal if was empty
            notifyAll();
    }

    public synchronized Object take() throws InterruptedException {
        while (usedSlots == 0)
            // wait until not empty
            wait();

        Object x = array[takePtr];
        array[takePtr] = null;
        takePtr = (takePtr + 1) % array.length;

        if (usedSlots-- == array.length) // signal if was full
            notifyAll();
        return x;
    }
}
