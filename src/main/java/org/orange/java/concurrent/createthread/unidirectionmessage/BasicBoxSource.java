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

package org.orange.java.concurrent.createthread.unidirectionmessage;

import java.awt.Dimension;

/**
 * 发送源
 *
 * @author mingle
 */
public class BasicBoxSource extends SingleOutputPushStage implements PushSource, Runnable {

    protected final Dimension size; // maximum sizes
    protected final int productionTime; // simulated delay

    public BasicBoxSource(Dimension s, int delay) {
        size = s;
        productionTime = delay;
    }

    @Override
    public Box produce() {
        return new BasicBox((int) (Math.random() * size.width) + 1,
                (int) (Math.random() * size.height) + 1);
    }

    public void start() {
        next1().putA(produce());
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                start();
                Thread.sleep((int) (Math.random() * 2 * productionTime));
            }
        } catch (InterruptedException ie) {
        } // die
    }

}
