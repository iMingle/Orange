/**
 * Copyright (c) 2016, Mingle. All rights reserved.
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
