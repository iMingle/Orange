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

package org.mingle.orange.java.concurrent.construct;

/**
 * 原子性提交
 * 
 * @since 1.0
 * @author Mingle
 */
public class Optimistic {
    private State state; // reference to representation object

    @SuppressWarnings("unused")
    private synchronized State getState() {
        return state;
    }

    @SuppressWarnings("unused")
    private synchronized boolean commit(State assumed, State next) {
        if (state == assumed) {
            state = next;
            return true;
        } else
            return false;
    }
}

class State {
}

class OptimisticDot {
    protected ImmutablePoint loc;

    public OptimisticDot(int x, int y) {
        loc = new ImmutablePoint(x, y);
    }

    public synchronized ImmutablePoint location() {
        return loc;
    }

    protected synchronized boolean commit(ImmutablePoint assumed,
            ImmutablePoint next) {
        if (loc == assumed) {
            loc = next;
            return true;
        } else
            return false;
    }

    public synchronized void moveTo(int x, int y) {
        // bypass commit since unconditional
        loc = new ImmutablePoint(x, y);
    }

    public void shiftX(int delta) {
        boolean success = false;
        do {
            ImmutablePoint old = location();
            ImmutablePoint next = new ImmutablePoint(old.x() + delta, old.y());
            success = commit(old, next);
        } while (!success);
    }

}