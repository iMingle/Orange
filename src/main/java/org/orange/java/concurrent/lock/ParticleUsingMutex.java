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

package org.orange.java.concurrent.lock;

import java.awt.Graphics;
import java.util.Random;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

/**
 * 互斥锁
 * 
 * @author mingle
 */
public class ParticleUsingMutex {
    protected int x;
    protected int y;
    protected final Random rng = new Random();
    protected final Mutex mutex = new Mutex();

    public ParticleUsingMutex(int initialX, int initialY) {
        x = initialX;
        y = initialY;
    }

    public void move() {
        try {
            mutex.acquire();
            try {
                x += rng.nextInt(10) - 5;
                y += rng.nextInt(20) - 10;
            } finally {
                mutex.release();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void draw(Graphics g) {
        int lx, ly;

        try {
            mutex.acquire();
            try {
                lx = x;
                ly = y;
            } finally {
                mutex.release();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            return;
        }

        g.drawRect(lx, ly, 10, 10);
    }
}
