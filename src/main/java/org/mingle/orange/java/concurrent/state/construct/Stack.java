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
 * 保障分层
 * 
 * @author mingle
 */
public class Stack {
    public synchronized boolean isEmpty() {
        return false; /* ... */
    }

    public synchronized void push(Object x) { /* ... */
    }

    public synchronized Object pop() throws StackEmptyException {
        if (isEmpty())
            throw new StackEmptyException();
        else
            return null;
    }
}
