/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.fail;

/**
 * 服务异常处理
 * 
 * @since 1.0
 * @author Mingle
 */
public interface ServiceExceptionHandler {
    void handle(ServiceException e);
}
