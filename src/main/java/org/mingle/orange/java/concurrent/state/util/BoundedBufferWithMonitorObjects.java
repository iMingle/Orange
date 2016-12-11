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

package org.mingle.orange.java.concurrent.state.util;

/**
 * 委托操作-合并类
 * 
 * @author Mingle
 */
public final class BoundedBufferWithMonitorObjects {
    private final Object[] array; // the elements

    private int putPtr = 0; // circular indices
    private int takePtr = 0;

    private int emptySlots; // slot counts
    private int usedSlots = 0;

    private int waitingPuts = 0; // counts of waiting threads
    private int waitingTakes = 0;

    private final Object putMonitor = new Object();
    private final Object takeMonitor = new Object();

    public BoundedBufferWithMonitorObjects(int capacity)
            throws IllegalArgumentException {
        if (capacity <= 0)
            throw new IllegalArgumentException();

        array = new Object[capacity];
        emptySlots = capacity;
    }

    public void put(Object x) throws InterruptedException {
        synchronized (putMonitor) {
            while (emptySlots <= 0) {
                ++waitingPuts;
                try {
                    putMonitor.wait();
                } catch (InterruptedException ie) {
                    putMonitor.notify();
                    throw ie;
                } finally {
                    --waitingPuts;
                }
            }
            --emptySlots;
            array[putPtr] = x;
            putPtr = (putPtr + 1) % array.length;
        }
        synchronized (takeMonitor) { // directly notify
            ++usedSlots;
            if (waitingTakes > 0)
                takeMonitor.notify();
        }
    }

    public Object take() throws InterruptedException {
        Object old = null;
        synchronized (takeMonitor) {
            while (usedSlots <= 0) {
                ++waitingTakes;
                try {
                    takeMonitor.wait();
                } catch (InterruptedException ie) {
                    takeMonitor.notify();
                    throw ie;
                } finally {
                    --waitingTakes;
                }
            }
            --usedSlots;
            old = array[takePtr];
            array[takePtr] = null;
            takePtr = (takePtr + 1) % array.length;
        }
        synchronized (putMonitor) {
            ++emptySlots;
            if (waitingPuts > 0)
                putMonitor.notify();
        }
        return old;
    }
}
