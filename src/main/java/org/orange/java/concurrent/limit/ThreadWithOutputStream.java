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

import java.io.OutputStream;

/**
 * 线程私有成员变量
 * 
 * @author mingle
 */
public class ThreadWithOutputStream extends Thread {
    private OutputStream output;
    
    public ThreadWithOutputStream(Runnable r, OutputStream s) {
        super(r);
        output = s;
    }
    
    public static ThreadWithOutputStream current() {
        return (ThreadWithOutputStream) currentThread();
    }

    public static OutputStream getOutput() {
        return current().output;
    }

    public static void setOutput(OutputStream output) {
        current().output = output;
    }
}
