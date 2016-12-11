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

package org.mingle.orange.java.concurrent.state.protectedmethod;

/**
 * 通知
 * 
 * @author Mingle
 */
public class SimpleBoundedCounter {
    static final long MIN = 0; // minimum allowed value

    static final long MAX = 10; // maximum allowed value

    protected long count = MIN;

    public synchronized long count() {
        return count;
    }

    public synchronized void inc() throws InterruptedException {
        awaitUnderMax();
        setCount(count + 1);
    }

    public synchronized void dec() throws InterruptedException {
        awaitOverMin();
        setCount(count - 1);
    }

    protected void setCount(long newValue) { // PRE: lock held
        count = newValue;
        notifyAll(); // wake up any thread depending on new value
    }

    protected void awaitUnderMax() throws InterruptedException {
        while (count == MAX)
            wait();
    }

    protected void awaitOverMin() throws InterruptedException {
        while (count == MIN)
            wait();
    }
}
