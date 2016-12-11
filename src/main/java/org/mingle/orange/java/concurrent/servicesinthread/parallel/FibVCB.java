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

package org.mingle.orange.java.concurrent.servicesinthread.parallel;

/**
 * 回调计数器方式
 * 
 * @since 1.0
 * @author Mingle
 */
public class FibVCB extends NullFJTask {
    static final int sequentialThreshold = 13; // for tuning

    volatile int number = 0; // as before
    final FibVCB parent; // Is null for outermost call
    int callbacksExpected = 0;
    volatile int callbacksReceived = 0;

    FibVCB(int n, FibVCB p) {
        number = n;
        parent = p;
    }

    int seqFib(int n) {
        if (n <= 1)
            return n;
        else
            return seqFib(n - 1) + seqFib(n - 2);
    }

    // Callback method invoked by subtasks upon completion
    synchronized void addToResult(int n) {
        number += n;
        ++callbacksReceived;
    }

    public void run() { // same structure as join-based version
        int n = number;
        if (n <= sequentialThreshold)
            number = seqFib(n);
        else {
            // clear number so subtasks can fill in
            number = 0;
            // establish number of callbacks expected
            callbacksExpected = 2;

            new FibVCB(n - 1, this).fork();
            new FibVCB(n - 2, this).fork();

            // Wait for callbacks from children
            while (callbacksReceived < callbacksExpected)
                yield();
        }

        // Call back parent
        if (parent != null)
            parent.addToResult(number);
    }
}
