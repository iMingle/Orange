/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.createthread.unidirectionmessage;

import java.awt.Dimension;

/**
 * 发送源
 * 
 * @since 1.0
 * @author Mingle
 */
public class BasicBoxSource extends SingleOutputPushStage implements
        PushSource, Runnable {

    protected final Dimension size; // maximum sizes
    protected final int productionTime; // simulated delay

    public BasicBoxSource(Dimension s, int delay) {
        size = s;
        productionTime = delay;
    }

    @Override
    public Box produce() {
        return new BasicBox((int) (Math.random() * size.width) + 1,
                (int) (Math.random() * size.height) + 1);
    }

    public void start() {
        next1().putA(produce());
    }

    @Override
    public void run() {
        try {
            for (;;) {
                start();
                Thread.sleep((int) (Math.random() * 2 * productionTime));
            }
        } catch (InterruptedException ie) {} // die
    }

}
