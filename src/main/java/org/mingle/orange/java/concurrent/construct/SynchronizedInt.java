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
 * 隔离成员变量保证原子性
 * 
 * @since 1.0
 * @author Mingle
 */
public class SynchronizedInt {
    private int value;

    public SynchronizedInt(int value) {
        this.value = value;
    }

    public synchronized int get() {
        return value;
    }

    public synchronized int set(int value) {
        int oldValue = value;
        this.value = value;
        return oldValue;
    }

    public synchronized int increment() {
        return value++;
    }
}

class Person {
    protected final SynchronizedInt age = new SynchronizedInt(0);

    public int getAge() {
        return age.get();
    }

    public void birthday() {
        age.increment();
    }
}