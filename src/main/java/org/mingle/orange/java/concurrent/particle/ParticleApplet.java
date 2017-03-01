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

package org.mingle.orange.java.concurrent.particle;

import java.applet.Applet;

/**
 * @author mingle
 */
public class ParticleApplet extends Applet {
    private static final long serialVersionUID = -8285243665638624935L;

    protected Thread[] threads = null;    // null when not running
    protected final ParticleCanvas canvas = new ParticleCanvas(100);

    @Override
    public void init() {
        add(canvas);
    }

    protected Thread makeThread(final Particle p) {
        return new Thread(() -> {
            try {
                for (; ; ) {
                    p.move();
                    canvas.repaint();
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                return;
            }
        });
    }

    @Override
    public synchronized void start() {
        int n = 10;

        if (threads == null) {    // bypass if already started
            Particle[] particles = new Particle[n];
            for (int i = 0; i < particles.length; i++)
                particles[i] = new Particle(50, 50);
            canvas.setParticles(particles);

            threads = new Thread[n];
            for (int i = 0; i < particles.length; i++) {
                threads[i] = makeThread(particles[i]);
                threads[i].start();
            }
        }
    }

    @Override
    public synchronized void stop() {
        if (threads != null) {    // bypass if already stopped
            for (Thread thread : threads)
                thread.interrupt();
            threads = null;
        }
    }
}
