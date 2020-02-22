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

package org.orange.java.concurrent.sync;

import java.util.Random;

/**
 * 懒加载单例模式
 * 
 * @author mingle
 */
public class LazySingletonCounter {
    private final long initial;
    private long count;
    private static LazySingletonCounter s = null;
    private static final Object classLock = LazySingletonCounter.class;

    private LazySingletonCounter() {
        initial = Math.abs(new Random().nextLong() / 2);
        count = initial;
    }

    public static LazySingletonCounter instance() {
        synchronized (classLock) {
            if (s == null)
                s = new LazySingletonCounter();
            return s;
        }
    }

    public long next() {
        synchronized (classLock) {
            return count++;
        }
    }

    public void reset() {
        synchronized (classLock) {
            count = initial;
        }
    }
}
