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

import org.mingle.orange.java.concurrent.createthread.threadpermessage.Channel;
import org.mingle.orange.java.concurrent.createthread.threadpermessage.Executor;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class PlainWorkerPool implements Executor {
    protected final Channel<Runnable> workQueue;

    @Override
    public void execute(Runnable r) {
        try {
            workQueue.put(r);
        } catch (InterruptedException ie) { // postpone response
            Thread.currentThread().interrupt();
        }
    }

    public PlainWorkerPool(Channel<Runnable> ch, int nworkers) {
        workQueue = ch;
        for (int i = 0; i < nworkers; ++i)
            activate();
    }

    protected void activate() {
        Runnable runLoop = new Runnable() {
            public void run() {
                try {
                    for (;;) {
                        Runnable r = workQueue.take();
                        r.run();
                    }
                } catch (InterruptedException ie) {} // die
            }
        };
        new Thread(runLoop).start();
    }
}
