/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.limit;

/**
 * 代理非同步对象
 * 
 * @since 1.0
 * @author Mingle
 */
public class SynchedPoint {
    protected final BarePoint delegate = new BarePoint();

    public synchronized double getX() {
        return delegate.x;
    }

    public synchronized double getY() {
        return delegate.y;
    }

    public synchronized void setX(double v) {
        delegate.x = v;
    }

    public synchronized void setY(double v) {
        delegate.y = v;
    }
}

class BarePoint {
    public double x;
    public double y;
}