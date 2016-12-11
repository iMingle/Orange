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

/**
 * 死锁
 * 
 * @author mingle
 */
public class Cell {
    private long value;
    
    public synchronized long getValue() {
        return value;
    }

    public synchronized void setValue(long v) {
        value = v;
    }

    /**
     * 会造成死锁,a.swapValue(b)和b.swapValue(a)
     * 
     * @param other
     */
    public synchronized void swapValue(Cell other) {
        long t = getValue();
        long v = other.getValue();
        setValue(v);
        other.setValue(t);
    }
}
