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

package org.mingle.orange.java.concurrent.createthread.eventdriven;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 事件驱动的任务
 * 
 * @since 1.0
 * @author Mingle
 */
public class IOEventTask implements Runnable {
    static final int BUFFSIZE = 1024;

    protected final Socket socket;
    protected final InputStream input;
    protected volatile boolean done = false; // latches true

    IOEventTask(Socket s) throws IOException {
        socket = s;
        input = socket.getInputStream();
    }

    void processCommand(byte[] b, int n) {
    }

    void cleanup() {
    }

    public void run() { // trigger only when input available
        if (done)
            return;

        byte[] commandBuffer = new byte[BUFFSIZE];
        try {
            int bytes = input.read(commandBuffer, 0, BUFFSIZE);
            if (bytes != BUFFSIZE)
                done = true;
            else
                processCommand(commandBuffer, bytes);
        } catch (IOException ex) {
            cleanup();
            done = true;
        } finally {
            if (!done)
                return;
            try {
                input.close();
                socket.close();
            } catch (IOException ignore) {}
        }
    }

    // Accessor methods needed by triggering agent:
    boolean done() {
        return done;
    }

    InputStream input() {
        return input;
    }
}

/**
 * 会话风格
 */
class SessionTask implements Runnable {
    static final int BUFFSIZE = 1024;

    protected final Socket socket;
    protected final InputStream input;

    SessionTask(Socket s) throws IOException {
        socket = s;
        input = socket.getInputStream();
    }

    void processCommand(byte[] b, int n) {
    }

    void cleanup() {
    }

    @Override
    public void run() { // Normally run in a new thread
        byte[] commandBuffer = new byte[BUFFSIZE];
        try {
            for (;;) {
                int bytes = input.read(commandBuffer, 0, BUFFSIZE);
                if (bytes != BUFFSIZE)
                    break;
                processCommand(commandBuffer, bytes);
            }
        } catch (IOException ex) {
            cleanup();
        } finally {
            try {
                input.close();
                socket.close();
            } catch (IOException ignore) {}
        }
    }
}
