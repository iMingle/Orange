/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 数字产生器
 *
 * @since 1.0
 * @author Mingle
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    
    public abstract int next();
    
    public void cancel() {
        canceled = true;
    }
    
    public boolean isCanceled() {
        return canceled;
    }
}
