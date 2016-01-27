/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.construct;

/**
 * 同步方法
 * 
 * @since 1.8
 * @author Mingle
 */
public class PartWithGuard {
    protected boolean cond = false;

    synchronized void await() throws InterruptedException {
        while (!cond)
            wait();
        // any other code
    }

    synchronized void signal(boolean c) {
        cond = c;
        notifyAll();
    }
}

class Host {
    protected final PartWithGuard part = new PartWithGuard();

    synchronized void rely() throws InterruptedException {
        part.await();    // 阻塞时持有的host锁没有办法释放,导致不能调用set方法通知
    }

    synchronized void set(boolean c) {
        part.signal(c);
    }
}