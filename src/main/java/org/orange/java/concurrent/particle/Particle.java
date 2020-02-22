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

import java.awt.Graphics;
import java.util.Random;

/**
 * 粒子
 *
 * @author mingle
 */
public class Particle {
    protected int x;
    protected int y;
    protected final Random random = new Random();

    public Particle(int initialX, int initialY) {
        x = initialX;
        y = initialY;
    }

    public synchronized void move() {
        x += random.nextInt(10) - 5;
        y += random.nextInt(20) - 10;
    }

    public void draw(Graphics g) {
        int lx, ly;
        synchronized (this) {
            lx = x;
            ly = y;
        }
        g.drawRect(lx, ly, 10, 10);    // 调用其它对象的方法释放锁
    }
}
