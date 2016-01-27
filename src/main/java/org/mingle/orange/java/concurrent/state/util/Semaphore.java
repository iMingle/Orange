/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.util;

/**
 * 信号量的实现
 * 
 * @since 1.8
 * @author Mingle
 */
public class Semaphore implements Sync {
    protected long permits; // current number of available permits

    public Semaphore(long initialPermits) {
        permits = initialPermits;
    }

    @Override
    public synchronized void release() {
        ++permits;
        notify();
    }

    @Override
    public void acquire() throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        synchronized (this) {
            try {
                while (permits <= 0)
                    wait();
                --permits;
            } catch (InterruptedException ie) {
                notify();
                throw ie;
            }
        }
    }

    @Override
    public boolean attempt(long msecs) throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        synchronized (this) {
            if (permits > 0) { // Same as acquire but messier
                --permits;
                return true;
            } else if (msecs <= 0) // avoid timed wait if not needed
                return false;
            else {
                try {
                    long startTime = System.currentTimeMillis();
                    long waitTime = msecs;

                    for (;;) {
                        wait(waitTime);
                        if (permits > 0) {
                            --permits;
                            return true;
                        } else { // Check for time-out
                            long now = System.currentTimeMillis();
                            waitTime = msecs - (now - startTime);
                            if (waitTime <= 0)
                                return false;
                        }
                    }
                } catch (InterruptedException ie) {
                    notify();
                    throw ie;
                }
            }
        }
    }
}
