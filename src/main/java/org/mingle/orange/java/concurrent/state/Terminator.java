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
 * 多阶段取消
 * 
 * @author mingle
 */
public class Terminator {
    // Try to kill; return true if known to be dead
    static boolean terminate(Thread t, long maxWaitToDie) {

        if (!t.isAlive())    // 不同JVM行为不同,不建议用
            return true; // already dead

        // phase 1 -- graceful cancellation
        t.interrupt();
        try {
            t.join(maxWaitToDie);
        } catch (InterruptedException e) {
        } // ignore

        if (!t.isAlive())
            return true; // success

        // phase 2 -- trap all security checks
        // theSecurityMgr.denyAllChecksFor(t); // a made-up method
        try {
            t.join(maxWaitToDie);
        } catch (InterruptedException ex) {
        }

        if (!t.isAlive())
            return true;

        // phase 3 -- minimize damage
        t.setPriority(Thread.MIN_PRIORITY);
        return false;
    }
}
