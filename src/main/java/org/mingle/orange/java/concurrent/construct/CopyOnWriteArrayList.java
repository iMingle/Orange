/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.construct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 写拷贝数组
 * 
 * @since 1.8
 * @author Mingle
 */
public class CopyOnWriteArrayList {
    protected Object[] array = new Object[0];
    
    protected synchronized Object[] getArray() {
        return array;
    }
    
    public synchronized void add(Object element) {
        int len = array.length;
        Object[] newArray = new Object[len + 1];
        System.arraycopy(array, 0, newArray, 0, len);
        newArray[len] = element;
        array = newArray;
    }
    
    public Iterator<?> iterator() {
        return new Iterator<Object>() {
            protected final Object[] snapshot = getArray();
            protected int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < snapshot.length;
            }

            @Override
            public Object next() {
                try {
                    return snapshot[cursor++];
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}