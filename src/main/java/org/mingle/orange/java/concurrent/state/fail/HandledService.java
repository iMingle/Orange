/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.fail;


/**
 * 异常处理器
 * 
 * @since 1.0
 * @author Mingle
 */
public class HandledService implements ServerWithException {
    final ServerWithException server = new ServerImpl();
    final ServiceExceptionHandler handler = new HandlerImpl();

    @Override
    public void service() {
        try {
            server.service();
        } catch (ServiceException e) {
            handler.handle(e);
        }
    }

}
