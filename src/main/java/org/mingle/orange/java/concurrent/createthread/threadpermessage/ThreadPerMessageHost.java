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

package org.mingle.orange.java.concurrent.createthread.threadpermessage;

import org.mingle.orange.java.concurrent.util.Helper;

/**
 * 每消息一线程
 * 
 * @author mingle
 */
public class ThreadPerMessageHost {
    protected long localState;
    protected final Helper helper = new Helper();

    protected synchronized void updateState() {
        localState = 2; // ...;
    }

    public void req() {
        updateState();
        new Thread(new Runnable() {
            public void run() {
                helper.handle();
            }
        }).start();
    }
}
