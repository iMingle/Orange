/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.particle;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 * 粒子绘制区域
 * 
 * @since 1.0
 * @author Mingle
 */
public class ParticleCanvas extends Canvas {
    private static final long serialVersionUID = -4834861050300977216L;
    
    private Particle[] particles = new Particle[0];
    
    public ParticleCanvas(int size) {
        setSize(size, size);
    }

    protected synchronized Particle[] getParticles() {
        return particles;
    }

    protected synchronized void setParticles(Particle[] ps) {
        if (ps == null)
            throw new IllegalArgumentException("Cannot set null");
        this.particles = ps;
    }
    
    @Override
    public void paint(Graphics g) {
        Particle[] ps = getParticles();
        for (Particle particle : ps)
            particle.draw(g);
    }
}
