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

package org.mingle.orange.java.concurrent.state.condvar;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * 一把锁的多个条件变量
 * 
 * @since 1.0
 * @author Mingle
 */
public class PThreadsStyleBuffer {
    private final Mutex mutex = new Mutex();
    private final CondVar notFull = new CondVar(mutex);
    private final CondVar notEmpty = new CondVar(mutex);
    private int count = 0;
    private int takePtr = 0;
    private int putPtr = 0;
    private final Object[] array;

    public PThreadsStyleBuffer(int capacity) {
        array = new Object[capacity];
    }

    public void put(Object x) throws InterruptedException {
        mutex.acquire();
        try {
            while (count == array.length)
                notFull.await();

            array[putPtr] = x;
            putPtr = (putPtr + 1) % array.length;
            ++count;
            notEmpty.signal();
        } finally {
            mutex.release();
        }
    }

    public Object take() throws InterruptedException {
        Object x = null;
        mutex.acquire();
        try {
            while (count == 0)
                notEmpty.await();

            x = array[takePtr];
            array[takePtr] = null;
            takePtr = (takePtr + 1) % array.length;
            --count;
            notFull.signal();
        } finally {
            mutex.release();
        }
        return x;
    }
}

class CondVar {
    protected final Sync mutex;

    public CondVar(Sync lock) {
        mutex = lock;
    }
    
    public void await() throws InterruptedException {}
    
    public void timewait(long ms)  throws InterruptedException {}
    
    public void signal() {}        // analog of notify
    
    public void broadcast() {}    // analog of notifyAll
}