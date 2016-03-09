/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.protectedmethod;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class Wait {
    synchronized void w() throws InterruptedException {
        before();
        wait();
        after();
    }

    synchronized void n() {
        notifyAll();
    }

    void before() {}

    void after() {}
}
