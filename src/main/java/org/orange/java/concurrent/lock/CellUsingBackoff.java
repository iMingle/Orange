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

package org.orange.java.concurrent.lock;

/**
 * 回退
 * 
 * @author mingle
 */
public class CellUsingBackoff {
    private long value;
    private final Mutex mutex = new Mutex();

    void swapValue(CellUsingBackoff other) {
        if (this == other)    // alias check required
            return;
        for (;;) {
            try {
                mutex.acquire();

                try {
                    if (other.mutex.attempt(0)) {
                        try {
                            long t = value;
                            value = other.value;
                            other.value = t;
                            return;
                        } finally {
                            other.mutex.release();
                        }
                    }
                } finally {
                    mutex.release();
                }

                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
