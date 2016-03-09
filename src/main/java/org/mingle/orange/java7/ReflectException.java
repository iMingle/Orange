/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

/**
 * 为所有的reflect操作异常找了个新爸爸 - ReflectiveOperationException
 * 
 * @since 1.0
 * @author Mingle
 */
public class ReflectException extends ReflectiveOperationException {
    private static final long serialVersionUID = 1617286221119926091L;
    
    public ReflectException() {
        super();
    }

    public ReflectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectException(String message) {
        super(message);
    }

    public ReflectException(Throwable cause) {
        super(cause);
    }
}
