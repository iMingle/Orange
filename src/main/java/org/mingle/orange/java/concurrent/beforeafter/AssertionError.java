/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 不变约束如果检查失败
 * 
 * @since 1.0
 * @author Mingle
 */
public class AssertionError extends Error {
    private static final long serialVersionUID = 9187371163456133683L;

    public AssertionError() {
        super();
    }

    public AssertionError(String message) {
        super(message);
    }
    
}
