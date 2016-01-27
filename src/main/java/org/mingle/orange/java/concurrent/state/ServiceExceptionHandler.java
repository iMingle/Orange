/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state;

/**
 * 服务异常处理
 * 
 * @since 1.8
 * @author Mingle
 */
public interface ServiceExceptionHandler {
    void handle(ServiceException e);
}
