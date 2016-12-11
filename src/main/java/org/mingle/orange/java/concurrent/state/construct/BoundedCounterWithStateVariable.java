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

package org.mingle.orange.java.concurrent.state.construct;

/**
 * 状态变量
 * 
 * @author Mingle
 */
public class BoundedCounterWithStateVariable {
    static final long MIN = 0; // minimum allowed value
    static final long MAX = 10; // maximum allowed value

    static final int BOTTOM = 0, MIDDLE = 1, TOP = 2;
    protected int state = BOTTOM; // the state variable
    protected long count = MIN;

    protected void updateState() { // PRE: synch lock held
        int oldState = state;
        if (count == MIN)
            state = BOTTOM;
        else if (count == MAX)
            state = TOP;
        else
            state = MIDDLE;
        if (state != oldState && oldState != MIDDLE)
            notifyAll(); // notify on transition
    }

    public synchronized long count() {
        return count;
    }

    public synchronized void inc() throws InterruptedException {
        while (state == TOP)
            wait();
        ++count;
        updateState();
    }

    public synchronized void dec() throws InterruptedException {
        while (state == BOTTOM)
            wait();
        --count;
        updateState();
    }
}
