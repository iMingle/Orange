/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.orange.java.concurrent.construct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 写拷贝数组
 *
 * @author mingle
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
