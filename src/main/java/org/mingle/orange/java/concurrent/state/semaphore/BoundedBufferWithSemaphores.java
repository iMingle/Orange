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

package org.mingle.orange.java.concurrent.state.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 有界缓冲区
 * 
 * @author Mingle
 */
public class BoundedBufferWithSemaphores {
    protected final BufferArray buff;
    protected final Semaphore putPermits;
    protected final Semaphore takePermits;

    public BoundedBufferWithSemaphores(int capacity)
            throws IllegalArgumentException {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        buff = new BufferArray(capacity);
        putPermits = new Semaphore(capacity);
        takePermits = new Semaphore(0);
    }

    public void put(Object x) throws InterruptedException {
        putPermits.acquire();
        buff.insert(x);
        takePermits.release();
    }

    public Object take() throws InterruptedException {
        takePermits.acquire();
        Object x = buff.extract();
        putPermits.release();
        return x;
    }

    public Object poll(long msecs) throws InterruptedException {
        if (!takePermits.tryAcquire(msecs, TimeUnit.MICROSECONDS))
            return null;
        Object x = buff.extract();
        putPermits.release();
        return x;
    }

    public boolean offer(Object x, long msecs) throws InterruptedException {
        if (!putPermits.tryAcquire(msecs, TimeUnit.MICROSECONDS))
            return false;
        buff.insert(x);
        takePermits.release();
        return true;
    }
}
