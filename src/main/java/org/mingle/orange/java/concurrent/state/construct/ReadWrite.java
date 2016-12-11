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

/**
 * 读入者写入者
 * 
 * @author Mingle
 */
public abstract class ReadWrite {
    protected int activeReaders = 0; // threads executing read
    protected int activeWriters = 0; // always zero or one

    protected int waitingReaders = 0; // threads not yet in read
    protected int waitingWriters = 0; // same for write

    protected abstract void doRead(); // implement in subclasses

    protected abstract void doWrite();

    public void read() throws InterruptedException {
        beforeRead();
        try {
            doRead();
        } finally {
            afterRead();
        }
    }

    public void write() throws InterruptedException {
        beforeWrite();
        try {
            doWrite();
        } finally {
            afterWrite();
        }
    }

    protected boolean allowReader() {
        return waitingWriters == 0 && activeWriters == 0;
    }

    protected boolean allowWriter() {
        return activeReaders == 0 && activeWriters == 0;
    }

    protected synchronized void beforeRead() throws InterruptedException {
        ++waitingReaders;
        while (!allowReader()) {
            try {
                wait();
            } catch (InterruptedException ie) {
                --waitingReaders; // roll back state
                throw ie;
            }
        }
        --waitingReaders;
        ++activeReaders;
    }

    protected synchronized void afterRead() {
        --activeReaders;
        notifyAll();
    }

    protected synchronized void beforeWrite() throws InterruptedException {
        ++waitingWriters;
        while (!allowWriter()) {
            try {
                wait();
            } catch (InterruptedException ie) {
                --waitingWriters;
                throw ie;
            }
        }
        --waitingWriters;
        ++activeWriters;
    }

    protected synchronized void afterWrite() {
        --activeWriters;
        notifyAll();
    }
}
