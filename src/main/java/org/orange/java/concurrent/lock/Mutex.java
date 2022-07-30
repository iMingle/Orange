package org.orange.java.concurrent.lock;

import org.orange.java.concurrent.state.util.Sync;

/**
 * @author jinminglei
 */
public class Mutex implements Sync {
    public void acquire() throws InterruptedException {
    }

    public boolean attempt(int i) {
        return false;
    }

    public void release() {
    }

    @Override public boolean attempt(long msec) throws InterruptedException {
        return false;
    }
}
