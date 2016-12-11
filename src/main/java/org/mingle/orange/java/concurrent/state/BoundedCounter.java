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

package org.mingle.orange.java.concurrent.state;

/**
 * 界限计数器
 * 
 * @author mingle
 */
public interface BoundedCounter {
    static final long MIN = 0; // minimum allowed value

    static final long MAX = 10; // maximum allowed value

    long count(); // INV: MIN <= count() <= MAX
                    // INIT: count() == MIN

    void inc(); // only allowed when count() < MAX

    void dec(); // only allowed when count() > MIN
}
