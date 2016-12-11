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

import org.mingle.orange.java.concurrent.util.Helper;

/**
 * 开放调用(非同步发送消息)
 * 
 * @since 1.0
 * @author Mingle
 */
public class ServerWithOpenCall {
    private double state;
    private final Helper helper = new Helper();

    public void service() {
        updateState();
        helper.operation();
    }

    public synchronized void updateState() {
        state = 2.0f; // set to some new value
    }

    public synchronized double getState() {
        return state;
    }
}
