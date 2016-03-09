/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.createthread.opencall;

import org.mingle.orange.java.concurrent.util.Helper;

/**
 * 开放调用
 * 
 * @since 1.0
 * @author Mingle
 */
public class OpenCallHost {
    protected long localState;
    protected final Helper helper = new Helper();

    protected synchronized void updateState() {
        localState = 2; // ...;
    }

    public void req() {
        updateState();
        helper.handle();
    }
}
