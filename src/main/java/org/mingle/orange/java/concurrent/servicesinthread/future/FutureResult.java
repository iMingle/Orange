/*
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
 * limitations under the License.
 */

package org.mingle.orange.java.concurrent.servicesinthread.future;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Callable接口的实现原理
 * 
 * @author mingle
 */
public class FutureResult {
    protected Object value = null;
    protected boolean ready = false;
    protected InvocationTargetException exception = null;

    public synchronized Object get() throws InterruptedException,
            InvocationTargetException {
        while (!ready)
            wait();
        if (exception != null)
            throw exception;
        else
            return value;
    }

    public Runnable setter(final Callable<Object> function) {
        return new Runnable() {
            public void run() {
                try {
                    set(function.call());
                } catch (Throwable e) {
                    setException(e);
                }
            }
        };
    }

    synchronized void set(Object result) {
        value = result;
        ready = true;
        notifyAll();
    }

    synchronized void setException(Throwable e) {
        exception = new InvocationTargetException(e);
        ready = true;
        notifyAll();
    }

    // ... other auxiliary and convenience methods ...
}

class PictureDisplayWithFutureResult {
    void displayBorders() {
    }

    void displayCaption() {
    }

    void displayImage(byte[] b) {
    }

    void cleanup() {
    }

    private final Renderer renderer = new StandardRenderer();

    public void show(final URL imageSource) {
        try {
            FutureResult futurePic = new FutureResult();
            Runnable command = futurePic.setter(new Callable<Object>() {
                
                @Override
                public Object call() {
                    return renderer.render(imageSource);
                }
            });
            new Thread(command).start();

            displayBorders();
            displayCaption();

            displayImage(((Pic) (futurePic.get())).getImage());
        } catch (InterruptedException ex) {
            cleanup();
            return;
        } catch (InvocationTargetException ex) {
            cleanup();
            return;
        }
    }
}
