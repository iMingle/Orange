/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.createthread.threadpermessage;

import org.mingle.orange.java.concurrent.util.Helper;

/**
 * 用执行器处理请求
 * 
 * @since 1.0
 * @author Mingle
 */
public class HostWithExecutor {
    protected long localState;
    protected final Helper helper = new Helper();
    protected final Executor executor;

    public HostWithExecutor(Executor e) {
        executor = e;
    }

    protected synchronized void updateState() {
        localState = 2; // ...;
    }

    public void req() {
        updateState();
        executor.execute(new Runnable() {
            public void run() {
                helper.handle();
            }
        });
    }
}
