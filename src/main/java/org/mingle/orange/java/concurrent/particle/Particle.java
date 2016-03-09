/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.particle;

import java.awt.Graphics;
import java.util.Random;

/**
 * 粒子
 * 
 * @since 1.0
 * @author Mingle
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
