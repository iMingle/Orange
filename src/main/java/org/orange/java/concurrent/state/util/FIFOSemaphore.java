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

package org.orange.java.concurrent.state.util;

/**
 * 基于FIFO的信号量
 * 
 * @author mingle
 */
public class FIFOSemaphore extends Semaphore {
    protected final WaitQueue queue = new WaitQueue();

    public FIFOSemaphore(long initialPermits) {
        super(initialPermits);
    }

    public void acquire() throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();

        WaitNode node = null;

        synchronized (this) {
            if (permits > 0) { // no need to queue
                --permits;
                return;
            } else {
                node = new WaitNode();
                queue.enq(node);
            }
        }

        // must release lock before node wait
        node.doWait();
    }

    public synchronized void release() {
        for (;;) { // retry until success
            WaitNode node = queue.deq();

            if (node == null) { // queue is empty
                ++permits;
                return;
            } else if (node.doNotify())
                return;

            // else node was already released due to
            // interruption or time-out, so must retry
        }
    }

    // Queue node class. Each node serves as a monitor.
    protected static class WaitNode {
        boolean released = false;
        WaitNode next = null;

        synchronized void doWait() throws InterruptedException {
            try {
                while (!released)
                    wait();
            } catch (InterruptedException ie) {

                if (!released) { // Interrupted before notified
                    // Suppress future notifications:
                    released = true;
                    throw ie;
                } else { // Interrupted after notified
                    // Ignore exception but propagate status:
                    Thread.currentThread().interrupt();
                }

            }
        }

        synchronized boolean doNotify() { // return true if notified
            if (released) // was interrupted or timed out
                return false;
            else {
                released = true;
                notify();
                return true;
            }
        }

        synchronized boolean doTimedWait(long msecs)
                throws InterruptedException {
            return true;
            // similar
        }
    }

    // Standard linked queue class.
    // Used only when holding Semaphore lock.
    protected static class WaitQueue {
        protected WaitNode head = null;
        protected WaitNode last = null;

        protected void enq(WaitNode node) {
            if (last == null)
                head = last = node;
            else {
                last.next = node;
                last = node;
            }
        }

        protected WaitNode deq() {
            WaitNode node = head;
            if (node != null) {
                head = node.next;
                if (head == null)
                    last = null;
                node.next = null;
            }
            return node;
        }
    }
}
