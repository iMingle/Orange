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

/**
 * 静态计数器
 * 
 * @author mingle
 */
public class StaticCounter {
    private static final long initial = Math.abs(new java.util.Random().nextLong() / 2);
    private static long count = initial;

    private StaticCounter() {}

    public static synchronized long next() {
        return count++;
    }

    public static synchronized void reset() {
        count = initial;
    }
}
