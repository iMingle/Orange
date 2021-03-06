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

package org.orange.distributed.lock.impl;

import org.orange.distributed.lock.DistributedLock;
import org.orange.distributed.lock.LockingException;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author mingle
 */
public class RedissonDistributedLock implements DistributedLock {
    private RLock lock;

    {
        Config config = new Config();
        config.useMasterSlaveServers().setMasterAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        lock = redisson.getLock("DistributedLock");
    }

    @Override public void lock() throws LockingException {
        lock.lock();
    }

    @Override public boolean tryLock(long timeout, TimeUnit unit) {
        try {
            return lock.tryLock(timeout, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override public void unlock() throws LockingException {
        lock.unlock();
    }

    public static void main(String[] args) {
        RedissonDistributedLock lock = new RedissonDistributedLock();
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }
}
