/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.sync;

/**
 * volatile类似此类
 * 
 * @since 1.0
 * @author Mingle
 */
public final class VFloat {
    private float value;

    final synchronized void set(float f) {
        value = f;
    }

    final synchronized float get() {
        return value;
    }
}
