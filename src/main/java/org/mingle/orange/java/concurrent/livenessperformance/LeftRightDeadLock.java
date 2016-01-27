/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.livenessperformance;

/**
 * 简单的锁顺序死锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class LeftRightDeadLock {
    private final Object left = new Object();
    private final Object right = new Object();
    
    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }
    
    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }
    
    private void doSomething() {}
    
    private void doSomethingElse() {}
}
