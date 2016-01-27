/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.createthread.unidirectionmessage;

/**
 * 双输出阶段
 * 
 * @since 1.8
 * @author Mingle
 */
public class Alternator extends DualOutputPushStage implements PushStage {
    protected boolean outTo2 = false; // control alternation

    protected synchronized boolean testAndInvert() {
        boolean b = outTo2;
        outTo2 = !outTo2;
        return b;
    }

    @Override
    public void putA(final Box p) {
        if (testAndInvert())
            next1().putA(p);
        else {
            new Thread(new Runnable() {
                public void run() {
                    next2().putA(p);
                }
            }).start();
        }
    }
}
