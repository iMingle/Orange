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
 * 委托操作-分解类
 * 
 * @author Mingle
 */
public final class BoundedBufferWithDelegates {
    private Object[] array;
    private Exchanger putter;
    private Exchanger taker;

    public BoundedBufferWithDelegates(int capacity)
            throws IllegalArgumentException {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        array = new Object[capacity];
        putter = new Exchanger(capacity);
        taker = new Exchanger(0);
    }

    public void put(Object x) throws InterruptedException {
        putter.exchange(x);
    }

    public Object take() throws InterruptedException {
        return taker.exchange(null);
    }

    void removedSlotNotification(Exchanger h) { // relay
        if (h == putter)
            taker.addedSlotNotification();
        else
            putter.addedSlotNotification();
    }

    protected class Exchanger { // Inner class
        protected int ptr = 0; // circular index
        protected int slots; // number of usable slots
        protected int waiting = 0; // number of waiting threads

        Exchanger(int n) {
            slots = n;
        }

        synchronized void addedSlotNotification() {
            ++slots;
            if (waiting > 0) // unblock a single waiting thread
                notify();
        }

        Object exchange(Object x) throws InterruptedException {
            Object old = null; // return value

            synchronized (this) {
                while (slots <= 0) { // wait for slot
                    ++waiting;
                    try {
                        wait();
                    } catch (InterruptedException ie) {
                        notify();
                        throw ie;
                    } finally {
                        --waiting;
                    }
                }

                --slots; // use slot
                old = array[ptr];
                array[ptr] = x;
                ptr = (ptr + 1) % array.length; // advance position
            }

            removedSlotNotification(this); // notify of change
            return old;
        }
    }
}
