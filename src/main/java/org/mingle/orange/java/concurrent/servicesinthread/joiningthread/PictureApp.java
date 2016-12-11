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

package org.mingle.orange.java.concurrent.servicesinthread.joiningthread;

import java.net.URL;

/**
 * 协作线程
 * 
 * @author Mingle
 */
public class PictureApp {
    private final Renderer renderer = new StandardRenderer();

    void displayBorders() {
    }

    void displayCaption() {
    }

    void displayImage(byte[] b) {
    }

    void cleanup() {
    }

    public void show(final URL imageSource) {
        class Waiter implements Runnable {
            private Pic result = null;

            Pic getResult() {
                return result;
            }

            @Override
            public void run() {
                result = renderer.render(imageSource);
            }
        }

        Waiter waiter = new Waiter();
        Thread t = new Thread(waiter);
        t.start();

        displayBorders(); // do other things
        displayCaption(); // while rendering

        try {
            t.join();
        } catch (InterruptedException e) {
            cleanup();
            return;
        }

        Pic pic = waiter.getResult();
        if (pic != null)
            displayImage(pic.getImage());
        else {
        }
        // ... deal with assumed rendering failure
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
