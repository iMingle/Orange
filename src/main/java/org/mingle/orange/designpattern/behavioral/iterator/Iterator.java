/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.iterator;

/**
 * 迭代器
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Iterator<T> {
    void first();
    void next();
    boolean isDone();
    T currentItem();
}
