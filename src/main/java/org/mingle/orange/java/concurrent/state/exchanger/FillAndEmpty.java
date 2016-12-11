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

package org.mingle.orange.java.concurrent.state.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 * 
 * @author Mingle
 */
public class FillAndEmpty {
    static final int SIZE = 1024; // buffer size, for demo
    protected Exchanger<byte[]> exchanger = new Exchanger<>();

    protected byte readByte() {
        return 1; /* ... */
    }

    protected void useByte(byte b) { /* ... */
    }

    public void start() {
        new Thread(new FillingLoop()).start();
        new Thread(new EmptyingLoop()).start();
    }

    class FillingLoop implements Runnable { // inner class
        public void run() {
            byte[] buffer = new byte[SIZE];
            int position = 0;

            try {
                for (;;) {
                    if (position == SIZE) {
                        buffer = (byte[]) (exchanger.exchange(buffer));
                        position = 0;
                    }

                    buffer[position++] = readByte();
                }
            } catch (InterruptedException ie) {
            } // die
        }
    }

    class EmptyingLoop implements Runnable { // inner class
        public void run() {
            byte[] buffer = new byte[SIZE];
            int position = SIZE; // force exchange first time through

            try {
                for (;;) {
                    if (position == SIZE) {
                        buffer = (byte[]) (exchanger.exchange(buffer));
                        position = 0;
                    }

                    useByte(buffer[position++]);
                }
            } catch (InterruptedException ex) {
            } // die
        }
    }
}
