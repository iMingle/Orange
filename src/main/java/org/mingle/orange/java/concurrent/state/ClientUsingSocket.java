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

import java.io.IOException;
import java.net.Socket;

/**
 * 失败重试
 * 
 * @author mingle
 */
public class ClientUsingSocket {
    int portnumber = 1234;
    String server = "gee";

    Socket retryUntilConnected() throws InterruptedException {
        // first delay is randomly chosen between 5 and 10secs
        long delayTime = 5000 + (long) (Math.random() * 5000);
        for (;;) {
            try {
                return new Socket(server, portnumber);
            } catch (IOException ex) {
                Thread.sleep(delayTime);
                delayTime = delayTime * 3 / 2 + 1; // increase 50%
            }
        }
    }
}
