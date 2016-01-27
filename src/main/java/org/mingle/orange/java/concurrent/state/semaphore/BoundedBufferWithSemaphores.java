/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 有界缓冲区
 * 
 * @since 1.8
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
