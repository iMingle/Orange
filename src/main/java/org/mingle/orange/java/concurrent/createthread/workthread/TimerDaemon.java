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

package org.mingle.orange.java.concurrent.createthread.workthread;

import java.util.Date;

/**
 * 只有一个工作者线程
 * 
 * @since 1.0
 * @author Mingle
 */
public class TimerDaemon {
    static class TimerTask implements Comparable<TimerTask> {
        final Runnable command;
        final long execTime; // time to run at

        public int compareTo(TimerTask x) {
            long otherExecTime = x.execTime;
            return (execTime < otherExecTime) ? -1
                    : (execTime == otherExecTime) ? 0 : 1;
        }

        TimerTask(Runnable r, long t) {
            command = r;
            execTime = t;
        }
    }

    // a heap or list with methods that preserve
    // ordering with respect to TimerTask.compareTo
    static class PriorityQueue {
        void put(TimerTask t) {
        }

        TimerTask least() {
            return null;
        }

        void removeLeast() {
        }

        boolean isEmpty() {
            return true;
        }
    }

    protected final PriorityQueue pq = new PriorityQueue();

    public synchronized void executeAfterDelay(Runnable r, long t) {
        pq.put(new TimerTask(r, t + System.currentTimeMillis()));
        notifyAll();
    }

    public synchronized void executeAt(Runnable r, Date time) {
        pq.put(new TimerTask(r, time.getTime()));
        notifyAll();
    }

    // wait for and then return next task to run
    protected synchronized Runnable take() throws InterruptedException {
        for (;;) {
            while (pq.isEmpty())
                wait();
            TimerTask t = pq.least();
            long now = System.currentTimeMillis();
            long waitTime = now - t.execTime;
            if (waitTime <= 0) {
                pq.removeLeast();
                return t.command;
            } else
                wait(waitTime);
        }
    }

    public TimerDaemon() {
        activate();
    } // only one

    void activate() {
        // same as PlainWorkerThread except using above take method
        Runnable runLoop = new Runnable() {
            public void run() {
                try {
                    for (;;) {
                        Runnable r = take();
                        r.run();
                    }
                } catch (InterruptedException ie) {
                } // die
            }
        };
        new Thread(runLoop).start();
    }
}
