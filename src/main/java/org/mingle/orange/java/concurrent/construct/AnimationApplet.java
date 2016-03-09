/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.construct;

/**
 * 双重检查
 * 
 * @since 1.0
 * @author Mingle
 */
public class AnimationApplet {
    int framesPerSecond; // default zero is illegal value

    void animate() {
        try {
            if (framesPerSecond == 0) { // the unsynchronized check
                synchronized (this) {
                    if (framesPerSecond == 0) { // the double-check
                        String param = getParameter("fps");
                        framesPerSecond = Integer.parseInt(param);
                    }
                }
            }
        } catch (Exception e) {
        }

        // ... actions using framesPerSecond ...
    }

    private String getParameter(String string) {
        return "60";
    }
}
