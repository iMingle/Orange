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

package org.mingle.orange.java.concurrent.lock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * 锁的顺序化管理器
 * 
 * @author mingle
 */
public class LockManager {
    protected void sortLocks(Sync[] locks) {}
    
    public void runWithinLocks(Runnable op, Sync[] locks) throws InterruptedException {
        sortLocks(locks);
        
        // for help in recovering from exceptions
        int lastLocked = -1;
        InterruptedException caught = null;
        
        try {
            for (int i = 0; i < locks.length; i++) {
                locks[i].acquire();
                lastLocked = i;
            }
            
            op.run();
        } catch (InterruptedException e) {
            caught = e;
        } finally {
            for (int i = lastLocked; i >= 0; i--)
                locks[i].release();
            if (caught != null)
                throw caught;
        }
    }
}
