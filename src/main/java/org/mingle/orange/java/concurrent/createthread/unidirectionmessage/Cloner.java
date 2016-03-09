/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.createthread.unidirectionmessage;

/**
 * 复制阶段
 * 
 * @since 1.0
 * @author Mingle
 */
public class Cloner extends DualOutputPushStage implements PushStage {

    @Override
    public void putA(Box p) {
        final Box p2 = p.duplicate();
        next1().putA(p);
        new Thread(new Runnable() {
            public void run() {
                next2().putA(p2);
            }
        }).start();
    }

}
