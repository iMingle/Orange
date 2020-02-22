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

package org.orange.java.concurrent.limit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 使用线程私有成员变量类
 * 
 * @author mingle
 */
public class ServiceUsingThreadWithOutputStream {
    public void service() throws IOException {
        OutputStream output = new FileOutputStream("...");
        Runnable r = new Runnable() {
            public void run() {
                try {
                    doService();
                } catch (IOException e) {
                }
            }
        };
        new ThreadWithOutputStream(r, output).start();
    }

    @SuppressWarnings("static-access")
    void doService() throws IOException {
        ThreadWithOutputStream.current().getOutput().write(0);
    }
}
