/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.sync;

/**
 * 不用同步机制的类
 * 
 * @since 1.0
 * @author Mingle
 */
public final class SetCheck {
    private int a = 0;
    private long b = 0;

    void set() {
        a = 1;
        b = -1;
    }

    boolean check() {
        return ((b == 0) || (b == -1 && a == 1));
    }
}
