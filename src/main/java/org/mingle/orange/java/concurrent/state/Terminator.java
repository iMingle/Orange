/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state;

/**
 * 多阶段取消
 * 
 * @since 1.0
 * @author Mingle
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
