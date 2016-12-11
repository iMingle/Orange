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
 * 信号量的实现
 * 
 * @author mingle
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
