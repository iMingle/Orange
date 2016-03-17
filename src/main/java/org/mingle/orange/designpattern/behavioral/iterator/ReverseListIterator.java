/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.iterator;

import java.util.List;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class ReverseListIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int current;

    public ReverseListIterator(List<T> list) {
        super();
        this.list = list;
    }

    @Override public void first() {
        current = list.size() - 1;
    }

    @Override public void next() {
        current--;
    }

    @Override public boolean isDone() {
        return current < 0;
    }

    @Override public T currentItem() {
        if (isDone())
            throw new IndexOutOfBoundsException();
        return list.get(current);
    }

}
