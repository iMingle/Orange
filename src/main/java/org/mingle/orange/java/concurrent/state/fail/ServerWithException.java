/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.fail;

/**
 * 服务异常
 * 
 * @since 1.8
 * @author Mingle
 */
public interface ServerWithException {
    void service() throws ServiceException;
}
