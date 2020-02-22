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

/**
 * 不变引用连接的数据结构,当synchronized方法调用非同步方法时,非同步方法依然处于同步状态
 *
 * @author mingle
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
