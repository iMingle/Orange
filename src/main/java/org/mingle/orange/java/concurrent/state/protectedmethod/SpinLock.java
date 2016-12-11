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

package org.mingle.orange.java.concurrent.state.protectedmethod;

/**
 * 忙等待的替换类
 * 
 * @author Mingle
 */
public class SpinLock { // Avoid needing to use this
    private volatile boolean busy = false;

    synchronized void release() {
        busy = false;
    }

    void acquire() throws InterruptedException {
        int itersBeforeYield = 100; // 100 is arbitrary
        int itersBeforeSleep = 200; // 200 is arbitrary
        long sleepTime = 1; // 1msec is arbitrary
        int iters = 0;
        for (;;) {
            if (!busy) { // test-and-test-and-set
                synchronized (this) {
                    if (!busy) {
                        busy = true;
                        return;
                    }
                }
            }

            if (iters < itersBeforeYield) { // spin phase
                ++iters;
            } else if (iters < itersBeforeSleep) { // yield phase
                ++iters;
                Thread.yield();
            } else { // back-off phase
                Thread.sleep(sleepTime);
                sleepTime = 3 * sleepTime / 2 + 1; // 50% is arbitrary
            }
        }
    }
}
