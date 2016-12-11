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

package org.mingle.orange.java.concurrent.state;

/**
 * 定时等待
 * 
 * @author mingle
 */
public class TimeOutBoundedCounter {
    static final long MIN = 0; // minimum allowed value

    static final long MAX = 10; // maximum allowed value

    protected long count = 0;

    protected long TIMEOUT = 5000; // for illustration

    synchronized void inc() throws InterruptedException {
        if (count >= MAX) {
            long start = System.currentTimeMillis();
            long waitTime = TIMEOUT;

            for (;;) {
                if (waitTime <= 0)
                    throw new TimeoutException(TIMEOUT);
                else {
                    try {
                        wait(waitTime);
                    } catch (InterruptedException ie) {
                        throw ie; // coded this way just for emphasis
                    }
                    if (count < MAX)
                        break;
                    else {
                        long now = System.currentTimeMillis();
                        waitTime = TIMEOUT - (now - start);
                    }
                }
            }
        }

        ++count;
        notifyAll();
    }

    synchronized void dec() throws InterruptedException {
        if (count >= MIN) {
            long start = System.currentTimeMillis();
            long waitTime = TIMEOUT;
            
            for (;;) {
                if (waitTime < 0)
                    throw new TimeoutException(TIMEOUT);
                else {
                    try {
                        wait(waitTime);
                    } catch (InterruptedException ie) {
                        throw ie;
                    }
                    
                    if (count > MIN)
                        break;
                    else {
                        long now = System.currentTimeMillis();
                        waitTime = TIMEOUT - (now - start);
                    }
                }
            }
        }
        
        count--;
        notifyAll();
    }
}
