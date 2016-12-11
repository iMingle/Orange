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

package org.mingle.orange.java.concurrent.createthread.threadpermessage;

import org.mingle.orange.java.concurrent.util.Helper;

/**
 * 用执行器处理请求
 * 
 * @author mingle
 */
public class HostWithExecutor {
    protected long localState;
    protected final Helper helper = new Helper();
    protected final Executor executor;

    public HostWithExecutor(Executor e) {
        executor = e;
    }

    protected synchronized void updateState() {
        localState = 2; // ...;
    }

    public void req() {
        updateState();
        executor.execute(new Runnable() {
            public void run() {
                helper.handle();
            }
        });
    }
}
