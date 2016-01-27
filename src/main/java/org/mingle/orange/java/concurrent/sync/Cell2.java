/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.sync;

/**
 * 顺序化资源防止死锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class Cell2 {
    private long value;

    public synchronized long getValue() {
        return value;
    }

    public synchronized void setValue(long v) {
        value = v;
    }

    /**
     * 用hashCode顺序化资源
     * 
     * @param other
     */
    public void swapValue(Cell2 other) {
        if (other == this) // 别名检查
            return;
        else if (System.identityHashCode(this) < System.identityHashCode(other))
            this.doSwapValue(other);
        else
            other.doSwapValue(this);
    }

    protected synchronized void doSwapValue(Cell2 other) {
        long t = getValue();
        long v = other.getValue();
        setValue(v);
        other.setValue(t);
    }

    protected synchronized void doSwapValueV2(Cell2 other) {
        synchronized (other) {
            long t = value;
            value = other.value;
            other.value = t;
        }
    }
}
