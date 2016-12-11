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
import java.io.InputStream;

/**
 * 定时让步重试
 * 
 * @author Mingle
 */
public class ReaderWithTimeout {
    void process(int b) {
    }

    void attemptRead(InputStream stream, long timeout) throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            for (;;) {
                if (stream.available() > 0) {
                    int c = stream.read();
                    if (c != -1)
                        process(c);
                    else
                        break; // eof
                } else {
                    try {
                        Thread.sleep(100); // arbitrary back-off time
                    } catch (InterruptedException ie) {
                        /* ... quietly wrap up and return ... */
                    }
                    long now = System.currentTimeMillis();
                    if (now - startTime >= timeout) {
                        /* ... fail ... */
                    }
                }
            }
        } catch (IOException ex) { /* ... fail ... */
        }
    }
}
