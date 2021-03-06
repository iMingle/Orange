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

package org.orange.java.speciality.concurrency;

/**
 * 筷子,哲学家就餐问题
 *
 * @author mingle
 */
public class Chopstick {
    private boolean taken = false;
    
    public synchronized void take() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
    }
    
    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
