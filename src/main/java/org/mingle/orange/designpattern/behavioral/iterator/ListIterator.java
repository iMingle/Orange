/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.designpattern.behavioral.iterator;

import java.util.List;

/**
 * 
 * 
 * @author Mingle
 */
public class ListIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int current;

    public ListIterator(List<T> list) {
        super();
        this.list = list;
    }

    @Override public void first() {
        current = 0;
    }

    @Override public void next() {
        current++;
    }

    @Override public boolean isDone() {
        return current >= list.size();
    }

    @Override public T currentItem() {
        if (isDone())
            throw new IndexOutOfBoundsException();
        return list.get(current);
    }

}
