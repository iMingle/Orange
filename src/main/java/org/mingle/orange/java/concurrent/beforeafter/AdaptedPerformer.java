/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 适配Performer
 * 
 * @since 1.8
 * @author Mingle
 */
public class AdaptedPerformer implements Runnable {
    private final Performer adaptee;

    public AdaptedPerformer(Performer adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void run() {
        adaptee.perform();
    }

}