/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.java.concurrent.state;

import java.util.Iterator;

import org.mingle.orange.java.concurrent.construct.CopyOnWriteArrayList;

/**
 * 转换消息类型
 * 
 * @since 1.0
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
