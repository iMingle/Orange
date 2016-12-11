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

package org.mingle.orange.java.concurrent.state.construct;

/**
 * 同步方法
 * 
 * @author mingle
 */
public class PartWithGuard {
    protected boolean cond = false;

    synchronized void await() throws InterruptedException {
        while (!cond)
            wait();
        // any other code
    }

    synchronized void signal(boolean c) {
        cond = c;
        notifyAll();
    }
}

class Host {
    protected final PartWithGuard part = new PartWithGuard();

    synchronized void rely() throws InterruptedException {
        part.await();    // 阻塞时持有的host锁没有办法释放,导致不能调用set方法通知
    }

    synchronized void set(boolean c) {
        part.signal(c);
    }
}
