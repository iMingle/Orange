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
 * 大型数据集合一般使用读写锁
 * 
 * @author mingle
 */
public class DataRepository {
    protected final ReadWriteLock rw = new RWLock();
    
    public void access() throws InterruptedException {
        rw.readLock().acquire();
        try {
            /* read data */
        } finally {
            rw.readLock().release();
        }
    }
    
    public void modify() throws InterruptedException {
        rw.writeLock().acquire();
        try {
            /* write data */
        } finally {
            rw.writeLock().release();
        }
    }
}
