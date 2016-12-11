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

package org.mingle.orange.java.concurrent.servicesinthread.scheduling;

import java.util.concurrent.Semaphore;

import org.mingle.orange.java.concurrent.state.transaction.Failure;

/**
 * 磁盘读写任务
 * 
 * @since 1.0
 * @author Mingle
 */
public abstract class DiskTask implements Runnable {
    protected final int cylinder; // read/write parameters
    protected final byte[] buffer;
    protected Failure exception = null; // to relay out
    protected DiskTask next = null; // for use in queue
    protected final Semaphore done = new Semaphore(0); // status indicator

    DiskTask(int c, byte[] b) {
        cylinder = c;
        buffer = b;
    }

    abstract void access() throws Failure; // read or write

    @Override
    public void run() {
        try {
            access();
        } catch (Failure ex) {
            setException(ex);
        } finally {
            done.release();
        }
    }

    void awaitCompletion() throws InterruptedException {
        done.acquire();
    }

    synchronized Failure getException() {
        return exception;
    }

    synchronized void setException(Failure f) {
        exception = f;
    }
}

/**
 * 读任务
 */
class DiskReadTask extends DiskTask {
    DiskReadTask(int c, byte[] b) {
        super(c, b);
    }

    void access() throws Failure { /* ... raw read ... */
    }
}

/**
 * 写任务
 */
class DiskWriteTask extends DiskTask {
    DiskWriteTask(int c, byte[] b) {
        super(c, b);
    }

    void access() throws Failure { /* ... raw write ... */
    }
}

/**
 * 调度读写任务
 */
class ScheduledDisk implements Disk {
    protected final DiskTaskQueue tasks = new DiskTaskQueue();

    public void read(int c, byte[] b) throws Failure {
        readOrWrite(new DiskReadTask(c, b));
    }

    public void write(int c, byte[] b) throws Failure {
        readOrWrite(new DiskWriteTask(c, b));
    }

    protected void readOrWrite(DiskTask t) throws Failure {
        tasks.put(t);
        try {
            t.awaitCompletion();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // propagate
            throw new Failure(); // convert to failure exception
        }
        Failure f = t.getException();
        if (f != null)
            throw f;
    }

    public ScheduledDisk() { // construct worker thread
        new Thread(new Runnable() {
            public void run() {
                try {
                    for (;;) {
                        tasks.take().run();
                    }
                } catch (InterruptedException ex) {} // die
            }
        }).start();
    }
}

class DiskTaskQueue {
    protected DiskTask thisSweep = null;    // 存储大于柱面号的任务
    protected DiskTask nextSweep = null;    // 存储小于柱面号的任务
    protected int currentCylinder = 0;

    protected final Semaphore available = new Semaphore(0);

    void put(DiskTask t) {
        insert(t);
        available.release();
    }

    DiskTask take() throws InterruptedException {
        available.acquire();
        return extract();
    }

    synchronized void insert(DiskTask t) {
        DiskTask q;
        if (t.cylinder >= currentCylinder) { // determine queue
            q = thisSweep;
            if (q == null) {
                thisSweep = t;
                return;
            }
        } else {
            q = nextSweep;
            if (q == null) {
                nextSweep = t;
                return;
            }
        }
        DiskTask trail = q; // ordered linked list insert
        q = trail.next;
        for (;;) {
            if (q == null || t.cylinder < q.cylinder) {
                trail.next = t;
                t.next = q;
                return;
            } else {
                trail = q;
                q = q.next;
            }
        }
    }

    synchronized DiskTask extract() { // PRE: not empty
        if (thisSweep == null) { // possibly swap queues
            thisSweep = nextSweep;
            nextSweep = null;
        }
        DiskTask t = thisSweep;
        thisSweep = t.next;
        currentCylinder = t.cylinder;
        return t;
    }
}