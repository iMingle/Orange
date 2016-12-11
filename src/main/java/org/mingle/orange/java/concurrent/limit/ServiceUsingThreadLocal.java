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

package org.mingle.orange.java.concurrent.limit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 使用ThreadLocal
 * 
 * @since 1.0
 * @author Mingle
 */
public class ServiceUsingThreadLocal {
    static ThreadLocal<OutputStream> output = new ThreadLocal<>();

    public void service() {
        try {
            final OutputStream s = new FileOutputStream("...");
            Runnable r = new Runnable() {
                public void run() {
                    output.set(s);
                    try {
                        doService();
                    } catch (IOException e) {
                    }

                    finally {
                        try {
                            s.close();
                        } catch (IOException ignore) {
                        }
                    }
                }
            };
            new Thread(r).start();
        } catch (IOException e) {
        }
    }

    void doService() throws IOException {
        ((OutputStream) (output.get())).write(0);
    }
}
