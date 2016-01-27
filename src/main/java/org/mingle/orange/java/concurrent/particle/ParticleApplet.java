/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.particle;

import java.applet.Applet;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
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
        Runnable runloop = new Runnable() {
            
            @Override
            public void run() {
                try {
                    for (;;) {
                        p.move();
                        canvas.repaint();
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        return new Thread(runloop);
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
