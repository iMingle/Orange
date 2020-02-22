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
import redis.clients.jedis.Jedis;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author mingle
 */
public class RedisDistributedLock implements DistributedLock {
    private Jedis redis;

    /**
     * Lock key path.
     */
    private String key;

    /**
     * Lock expiration in milliseconds.
     */
    private int expire = 60 * 1000;

    /**
     * Acquire timeout in milliseconds.
     */
    private int timeout = 10 * 1000;

    boolean locked = false;

    public RedisDistributedLock(Jedis redis, String lockKey) {
        this.redis = redis;
        this.key = lockKey;
    }

    public RedisDistributedLock(Jedis jedis, String lockKey, int timeout) {
        this(jedis, lockKey);
        this.timeout = timeout;
    }

    public RedisDistributedLock(Jedis jedis, String lockKey, int timeout, int expire) {
        this(jedis, lockKey, timeout);
        this.expire = expire;
    }

    @Override public void lock() throws LockingException {
        while (true) {
            long expires = System.currentTimeMillis() + expire + 1;
            String expiresStr = String.valueOf(expires);

            if (redis.setnx(key, expiresStr) == 1) {
                locked = true;
                break;
            }

            String currentValueStr = redis.get(key);
            /** lock is expired */
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                String oldValueStr = redis.getSet(key, expiresStr);
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    locked = true;
                    break;
                }
            }

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(50, 100));
            } catch (InterruptedException e) {
                throw new LockingException("InterruptedException while trying to acquire lock!", e);
            }
        }
    }

    @Override public boolean tryLock(long timeout, TimeUnit unit) {
        long timeoutMilli = unit.toMillis(timeout);

        while (timeoutMilli >= 0) {
            long expires = System.currentTimeMillis() + expire + 1;
            String expiresStr = String.valueOf(expires);

            if (redis.setnx(key, expiresStr) == 1) {
                locked = true;
                return true;
            }

            String currentValueStr = redis.get(key);
            /** lock is expired */
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                String oldValueStr = redis.getSet(key, expiresStr);
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    locked = true;
                    return true;
                }
            }

            int sleepTime = ThreadLocalRandom.current().nextInt(50, 100);
            timeoutMilli -= sleepTime;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                return false;
            }
        }

        return false;
    }

    @Override public void unlock() throws LockingException {
        if (locked) {
            redis.del(key);
            locked = false;
            redis.close();
        }
    }
}
