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
 * 分解锁
 * 
 * @author Mingle
 */
public class LockSplitShape {
    protected double x = 0.0;
    protected double y = 0.0;
    protected double width = 0.0;
    protected double height = 0.0;

    protected final Object locationLock = new Object();
    protected final Object dimensionLock = new Object();

    public double x() {
        synchronized (locationLock) {
            return x;
        }
    }

    public double y() {
        synchronized (locationLock) {
            return y;
        }
    }

    public void adjustLocation() {
        synchronized (locationLock) {
            x = 1; // longCalculation1();
            y = 2; // longCalculation2();
        }
    }
}
