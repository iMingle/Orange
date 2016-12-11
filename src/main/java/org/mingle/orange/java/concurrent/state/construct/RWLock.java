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

package org.mingle.orange.java.concurrent.state.construct;

import org.mingle.orange.java.concurrent.lock.ReadWriteLock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * 
 * 
 * @author Mingle
 */
public class RWLock extends ReadWrite implements ReadWriteLock {
    class RLock implements Sync {
        public void acquire() throws InterruptedException {
            beforeRead();
        }

        public void release() {
            afterRead();
        }

        public boolean attempt(long msecs) throws InterruptedException {
            return beforeRead(msecs);
        }
    }

    class WLock implements Sync {
        public void acquire() throws InterruptedException {
            beforeWrite();
        }

        public void release() {
            afterWrite();
        }

        public boolean attempt(long msecs) throws InterruptedException {
            return beforeWrite(msecs);
        }
    }

    protected final RLock rlock = new RLock();
    protected final WLock wlock = new WLock();

    @Override
    public Sync readLock() {
        return rlock;
    }

    @Override
    public Sync writeLock() {
        return wlock;
    }

    public boolean beforeRead(long msecs) throws InterruptedException {
        return true;
        // ... time-out version of beforeRead ...
    }

    public boolean beforeWrite(long msecs) throws InterruptedException {
        return true;
        // ... time-out version of beforeWrite ...
    }

    @Override
    protected void doRead() {
    }

    @Override
    protected void doWrite() {
    }
}
