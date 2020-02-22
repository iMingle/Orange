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

package org.orange.java.concurrent.state.protectedmethod;

/**
 * notify方法模拟notifyAll方法
 * 
 * @author mingle
 */
public class GuardedClassUsingNotify {
    protected boolean cond = false;
    protected int nWaiting = 0; // count waiting threads

    protected synchronized void awaitCond() throws InterruptedException {
        while (!cond) {
            ++nWaiting; // record fact that a thread is waiting
            try {
                wait();
            } catch (InterruptedException ie) {
                notify();
                throw ie;
            } finally {
                --nWaiting; // no longer waiting
            }
        }
    }

    protected synchronized void signalCond() {
        if (cond) { // simulate notifyAll
            for (int i = nWaiting; i > 0; --i) {
                notify();
            }
        }
    }
}
