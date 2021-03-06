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

package org.orange.java.concurrent.particle;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 * 粒子绘制区域
 *
 * @author mingle
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
