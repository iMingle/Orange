/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state;

import java.util.Iterator;

import org.mingle.orange.java.concurrent.construct.CopyOnWriteArrayList;

/**
 * 转换消息类型
 * 
 * @since 1.8
 * @author Mingle
 */
public class ServiceIssuingExceptionEvent {
    private final CopyOnWriteArrayList handlers = new CopyOnWriteArrayList();

    public void addHandler(ExceptionEventListener h) {
        handlers.add(h);
    }

    public void service() {
        boolean failed = true;
        if (failed) {
            Throwable ex = new ServiceException();
            ExceptionEvent ee = new ExceptionEvent(this, ex);

            for (Iterator<?> it = handlers.iterator(); it.hasNext();) {
                ExceptionEventListener l = (ExceptionEventListener) (it.next());
                l.exceptionOccured(ee);
            }
        }
    }

    class ExceptionEvent extends java.util.EventObject {
        private static final long serialVersionUID = -5261862607728828548L;
        public final Throwable theException;

        public ExceptionEvent(Object src, Throwable ex) {
            super(src);
            theException = ex;
        }
    }

    class ExceptionEventListener {
        public void exceptionOccured(ExceptionEvent ee) {
            // ... respond to exception...
        }
    }
}
