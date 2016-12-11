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

package org.mingle.orange.java.concurrent.construct;

/**
 * 双重检查
 * 
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
