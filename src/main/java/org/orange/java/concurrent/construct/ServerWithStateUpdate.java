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

package org.orange.java.concurrent.construct;

import org.orange.java.concurrent.util.Helper;

/**
 * @author mingle
 */
public class ServerWithStateUpdate {
    private double state;
    private final Helper helper = new Helper();

    public synchronized void service() {
        state = 2.0f;    // set to some new value
        helper.operation();    // 花费时间很长的话,那么此方法需要阻塞无法接受的时间
    }

    public synchronized double getState() {
        return state;
    }
}
