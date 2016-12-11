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

package org.mingle.orange.java.concurrent.createthread.unidirectionmessage;

/**
 * 双输出阶段
 * 
 * @author Mingle
 */
public class Alternator extends DualOutputPushStage implements PushStage {
    protected boolean outTo2 = false; // control alternation

    protected synchronized boolean testAndInvert() {
        boolean b = outTo2;
        outTo2 = !outTo2;
        return b;
    }

    @Override
    public void putA(final Box p) {
        if (testAndInvert())
            next1().putA(p);
        else {
            new Thread(new Runnable() {
                public void run() {
                    next2().putA(p);
                }
            }).start();
        }
    }
}
