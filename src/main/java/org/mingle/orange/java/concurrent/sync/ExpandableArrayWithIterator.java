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

package org.mingle.orange.java.concurrent.sync;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 遍历-版本化迭代变量,适合集合类
 * 
 * @author Minele
 */
public class ExpandableArrayWithIterator extends ExpandableArray {
    protected int version = 0;

    public ExpandableArrayWithIterator(int cap) {
        super(cap);
    }

    @Override
    public synchronized void removeLast() throws NoSuchElementException {
        super.removeLast();
        version++;
    }

    @Override
    public synchronized void add(Object x) {
        super.add(x);
        version++;
    }

    public synchronized Iterator<?> iterator() {
        return new EAIterator<Object>();
    }

    protected class EAIterator<T> implements Iterator<T> {
        protected final int currentVersion;
        protected int currentIndex = 0;

        EAIterator() {
            currentVersion = version;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            synchronized (ExpandableArrayWithIterator.this) {
                if (currentVersion != version)
                    throw new ConcurrentModificationException();
                else if (currentIndex == size)
                    throw new NoSuchElementException();
                else
                    return (T) data[currentIndex++];
            }
        }

        @Override
        public boolean hasNext() {
            synchronized (ExpandableArrayWithIterator.this) {
                return (currentIndex < size);
            }
        }

        @Override
        public void remove() {
            // similar
        }
    }
    
    public static void main(String[] args) {
        ExpandableArrayWithIterator v = new ExpandableArrayWithIterator(5);
        v.add("One");
        v.add("Two");
        for (Iterator<?> it = v.iterator(); it.hasNext();) {
            try {
                System.out.println(it.next());
            } catch (ConcurrentModificationException e) {
                /* fail */
            } catch (NoSuchElementException e) {
                /* fail */
            }
        }
    }
}
