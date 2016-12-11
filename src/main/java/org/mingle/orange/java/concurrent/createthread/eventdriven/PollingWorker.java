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

package org.mingle.orange.java.concurrent.createthread.eventdriven;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 触发任务
 * 
 * @since 1.0
 * @author Mingle
 */
public class PollingWorker implements Runnable {
    private List<IOEventTask> tasks = new LinkedList<>();
    private long sleepTime = 100;

    void register(IOEventTask t) {
        tasks.add(t);
    }

    void deregister(IOEventTask t) {
        tasks.remove(t);
    }

    public void run() {
        try {
            for (;;) {
                for (Iterator<IOEventTask> it = tasks.iterator(); it.hasNext();) {
                    IOEventTask t = it.next();
                    if (t.done())
                        deregister(t);
                    else {
                        boolean trigger;
                        try {
                            trigger = t.input().available() > 0;
                        } catch (IOException ex) {
                            trigger = true; // trigger if exception on check
                        }
                        if (trigger)
                            t.run();
                    }
                }
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException ie) {}
    }
}
