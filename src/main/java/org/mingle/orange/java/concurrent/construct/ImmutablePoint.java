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
 * 不变类
 * 
 * @author mingle
 */
public class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int initX, int initY) {
        x = initX;
        y = initY;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}

class Dot {
    protected ImmutablePoint loc;
    
    public Dot(int x, int y) {
        loc = new ImmutablePoint(x, y);
    }
    
    public synchronized ImmutablePoint location() {
        return loc;
    }
    
    protected synchronized void updateLoc(ImmutablePoint newLoc) {
        loc = newLoc;
    }
    
    public void moveTo(int x, int y) {
        updateLoc(new ImmutablePoint(x, y));
    }
    
    /**
     * loc可变,此方法必须同步
     * 
     * @param delta
     */
    public synchronized void shiftX(int delta) {
        updateLoc(new ImmutablePoint(loc.x() + delta, loc.y()));
    }
}
