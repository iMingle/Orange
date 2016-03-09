/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.servicesinthread.joiningthread;

import java.net.URL;

/**
 * 协作线程
 * 
 * @since 1.0
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
