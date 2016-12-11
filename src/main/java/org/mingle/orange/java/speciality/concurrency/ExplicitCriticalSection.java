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

package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显式的Lock对象创建临界区
 *
 * @author mingle
 */
class ExplicitPairManager1 extends PairManager {
    private Lock lock = new ReentrantLock();

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.concurrency.PairManager#increment()
     */
    @Override
    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }

}

class ExplicitPairManager2 extends PairManager {
    private Lock lock = new ReentrantLock();

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.concurrency.PairManager#increment()
     */
    @Override
    public void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }
    
}

public class ExplicitCriticalSection {
    public static void main(String[] args) {
        PairManager pman1 = new ExplicitPairManager1(),
                pman2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(pman1, pman2);
    }
}
