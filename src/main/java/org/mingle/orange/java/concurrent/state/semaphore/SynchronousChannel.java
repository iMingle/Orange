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

package org.mingle.orange.java.concurrent.state.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 同步通道
 * 
 * @author mingle
 */
public class SynchronousChannel {
    protected Object item = null; // to hold while in transit
    protected final Semaphore putPermit;
    protected final Semaphore takePermit;
    protected final Semaphore taken;

    public SynchronousChannel() {
        putPermit = new Semaphore(1);
        takePermit = new Semaphore(0);
        taken = new Semaphore(0);
    }

    public void put(Object x) throws InterruptedException {
        putPermit.acquire();
        item = x;
        takePermit.release();

        // Must wait until signalled by taker
        InterruptedException caught = null;
        for (;;) {
            try {
                taken.acquire();
                break;
            } catch (InterruptedException ie) {
                caught = ie;
            }
        }

        if (caught != null)
            throw caught; // can now rethrow
    }

    public Object take() throws InterruptedException {
        takePermit.acquire();
        Object x = item;
        item = null;
        putPermit.release();
        taken.release();
        return x;
    }
}
