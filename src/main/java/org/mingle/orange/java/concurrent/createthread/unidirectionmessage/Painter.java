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

package org.mingle.orange.java.concurrent.createthread.unidirectionmessage;

import java.awt.Color;
import java.awt.Dimension;

/**
 * 线性阶段
 * 
 * @author mingle
 */
public class Painter extends SingleOutputPushStage implements PushStage {
    protected final Color color; // the color to paint things

    public Painter(Color c) {
        color = c;
    }

    public void putA(Box p) {
        p.setColor(color);
        next1().putA(p);
    }
}

class Wrapper extends SingleOutputPushStage implements PushStage {
    protected final int thickness;

    public Wrapper(int t) {
        thickness = t;
    }

    public void putA(Box p) {
        Dimension d = new Dimension(thickness, thickness);
        next1().putA(new WrappedBox(p, d));
    }
}

class Flipper extends SingleOutputPushStage implements PushStage {
    public void putA(Box p) {
        if (p instanceof JoinedPair)
            ((JoinedPair) p).flip();
        next1().putA(p);
    }
}
