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

package org.orange.java.concurrent.state;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * IO和资源失效
 * 
 * @author mingle
 */
public class CancellableReader {
    private Thread readerThread; // only one at a time supported
    private FileInputStream dataFile;

    public synchronized void startReaderThread() throws IllegalStateException,
            FileNotFoundException {
        if (readerThread != null)
            throw new IllegalStateException();
        dataFile = new FileInputStream("data");
        readerThread = new Thread(new Runnable() {
            public void run() {
                doRead();
            }
        });
        readerThread.start();
    }

    protected synchronized void closeFile() { // utility method
        if (dataFile != null) {
            try {
                dataFile.close();
            } catch (IOException ignore) {
            }
            dataFile = null;
        }
    }

    void process(int b) {
    }

    private void doRead() {
        try {
            while (!Thread.interrupted()) {
                try {
                    int c = dataFile.read();
                    if (c == -1)
                        break;
                    else
                        process(c);
                } catch (IOException ex) {
                    break; // perhaps first do other cleanup
                }
            }
        } finally {
            closeFile();
            synchronized (this) {
                readerThread = null;
            }
        }
    }

    public synchronized void cancelReaderThread() {
        if (readerThread != null)
            readerThread.interrupt();
        closeFile();
    }
}
