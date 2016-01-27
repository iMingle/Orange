/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.construct;

/**
 * 不变引用连接的数据结构,当synchronized方法调用非同步方法时,非同步方法依然处于同步状态
 * 
 * @since 1.8
 * @author Mingle
 */
public class LinkedCell {
    protected int value;
    protected final LinkedCell next;

    public LinkedCell(int v, LinkedCell t) {
        value = v;
        next = t;
    }

    public synchronized int value() {
        return value;
    }

    public synchronized void setValue(int v) {
        value = v;
    }

    public int sum() { // add up all element values
        return (next == null) ? value() : value() + next.sum();
    }

    public boolean includes(int x) { // search for x
        return (value() == x) ? true : (next == null) ? false : next
                .includes(x);
    }
}
