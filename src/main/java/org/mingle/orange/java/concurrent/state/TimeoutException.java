/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class TimeoutException extends InterruptedException {
    private static final long serialVersionUID = -6354763818990858746L;
    
    protected long timeout;

    public TimeoutException(long timeout) {
        super();
        this.timeout = timeout;
    }
}
