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
 * 顺序化资源防止死锁
 * 
 * @author mingle
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
