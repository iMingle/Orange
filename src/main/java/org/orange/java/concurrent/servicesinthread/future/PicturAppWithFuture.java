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

package org.orange.java.concurrent.servicesinthread.future;

import java.net.URL;

/**
 * Future接口实现原理
 * 
 * @author mingle
 */
public class PicturAppWithFuture {
    private final Renderer renderer = new AsynchRenderer();

    void displayBorders() {
    }

    void displayCaption() {
    }

    void displayImage(byte[] b) {
    }

    void cleanup() {
    }

    public void show(final URL imageSource) {
        Pic pic = renderer.render(imageSource);

        displayBorders(); // do other things ...
        displayCaption();

        byte[] im = pic.getImage();
        if (im != null)
            displayImage(im);
        else {} // deal with assumed rendering failure
    }
}

class AsynchRenderer implements Renderer {
    private final Renderer renderer = new StandardRenderer();

    static class FuturePic implements Pic { // inner class
        private Pic pic = null;
        private boolean ready = false;

        synchronized void setPic(Pic p) {
            pic = p;
            ready = true;
            notifyAll();
        }

        public synchronized byte[] getImage() {
            while (!ready)
                try {
                    wait();
                } catch (InterruptedException e) {
                    return null;
                }
            return pic.getImage();
        }
    }

    public Pic render(final URL src) {
        final FuturePic p = new FuturePic();
        new Thread(new Runnable() {
            public void run() {
                p.setPic(renderer.render(src));
            }
        }).start();
        return p;
    }
}

interface Pic {
    byte[] getImage();
}

interface Renderer {
    Pic render(URL src);
}

class StandardRenderer implements Renderer {
    public Pic render(URL src) {
        return null;
    }
}
