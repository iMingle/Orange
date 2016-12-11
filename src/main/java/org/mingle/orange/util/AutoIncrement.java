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

package org.mingle.orange.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.Lists;

/**
 * 实现类似mysql的主键自增操作，线程安全
 * 
 * @since 1.0
 * @author Mingle
 */
public class AutoIncrement {
    /**
     * 自增字段
     */
    private int id;
    /**
     * volatile变量
     */
    private volatile int vid;
    /**
     * 锁对象
     */
    private Lock lock = new ReentrantLock();
    /**
     * 条件变量
     */
    private Condition condition = lock.newCondition();
    /**
     * 原子变量
     */
    private AtomicInteger atomic = new AtomicInteger(0);
    /**
     * 私有构造方法
     */
    private AutoIncrement() {}
    
    private static class AutoIncrementInstance {
        private static final AutoIncrement autoIncrement = new AutoIncrement();
    }
    
    public static AutoIncrement getInstance() {
        return AutoIncrementInstance.autoIncrement;
    }
    
    /**
     * volatile同步，无法保证非原子操作
     * @return
     */
    public int getVolatileId() {
        return vid++;
    }
    
    /**
     * 同步块
     * @return
     */
    public int getSyncBlockId() {
        synchronized(this) {
            return id++;
        }
    }
    
    /**
     * 同步方法
     * @return
     */
    public synchronized int getSyncMethodId() {
        return id++;
    }
    
    /**
     * 锁对象操作
     * @return
     */
    public int getLockId() {
        lock.lock();
        try {
            return id++;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * 锁对象操作加上条件对象，对此类没有意义
     * @return
     */
    @Deprecated
    public int getLockConditionId() {
        lock.lock();
        try {
            while (id < 0) condition.await();
            
            id++;
            
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return id;
    }
    
    /**
     * 尝试获得锁对象操作
     * @return
     */
    public int getTryLockId() {
        if (lock.tryLock()) {
            try {
                return id++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("cannot get lock");
            return 1;
        }
    }
    
    public int getAtomicId() {
        return atomic.addAndGet(1);
    }
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Thread> threads = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new ThreadId());
            threads.add(t);
            t.start();
        }
        
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("total time is " + (System.currentTimeMillis() - start));
    }
}

/**
 * 测试获取ID的线程
 */
class ThreadId implements Runnable {
    private AutoIncrement autoIncrement;
    private static Set<Integer> set;
    
    public ThreadId() {
        autoIncrement = AutoIncrement.getInstance();
        set = new HashSet<Integer>();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        if (autoIncrement == null) System.out.println("get AutoIncrement is null");
        int id = 0;
        
        while (id < 500000) {
            id = autoIncrement.getVolatileId();            // 无法正常工作
//            id = autoIncrement.getSyncMethodId();        // 6643ms
//            id = autoIncrement.getSyncBlockId();        // 6469ms
//            id = autoIncrement.getLockId();                // 6401ms
//            id = autoIncrement.getTryLockId();            // 可能获取不到锁
//            id = autoIncrement.getAtomicId();            // 6297ms
            System.out.println(" ID = " + id);
            if (!set.add(id)) {
                System.out.println("has repeat element");
                break;
            }
        }
    }
    
}