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

package org.mingle.orange.java.concurrent.lock;

import java.util.Random;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

/**
 * 方法适配器
 * 
 * @author mingle
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
