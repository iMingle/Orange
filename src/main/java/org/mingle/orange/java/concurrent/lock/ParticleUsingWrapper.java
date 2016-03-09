/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.lock;

import java.util.Random;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

/**
 * 方法适配器
 * 
 * @since 1.0
 * @author Mingle
 */
public class ParticleUsingWrapper {
    protected int x;
    protected int y;
    protected final Random rng = new Random();

    protected final WithMutex withMutex = new WithMutex(new Mutex());

    protected void doMove() {
        x += rng.nextInt(10) - 5;
        y += rng.nextInt(20) - 10;
    }

    public void move() {
        try {
            withMutex.perform(new Runnable() {
                public void run() {
                    doMove();
                }
            });
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}

class WithMutex {
    private final Mutex mutex;

    public WithMutex(Mutex m) {
        mutex = m;
    }

    public void perform(Runnable r) throws InterruptedException {
        mutex.acquire();
        try {
            r.run();
        } finally {
            mutex.release();
        }
    }
}
