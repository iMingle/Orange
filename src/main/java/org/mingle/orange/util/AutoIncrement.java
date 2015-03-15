/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.Lists;

/**
 * 实现类似mysql的主键自增操作，线程安全
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class AutoIncrement {
	/**
	 * 自增字段
	 */
	private int id;
	/**
	 * 单例对象
	 */
	private static final AutoIncrement autoIncrement = new AutoIncrement();
	/**
	 * 锁对象
	 */
	private Lock lock = new ReentrantLock();
	/**
	 * 条件变量
	 */
	private Condition condition = lock.newCondition();
	
	private AutoIncrement() {}
	
	public static AutoIncrement getInstance() {
		return autoIncrement;
	}
	
	/**
	 * 非同步
	 * @return
	 */
	public int getId() {
		return id++;
	}
	
	/**
	 * 同步块
	 * @return
	 */
	public int getSyncBlockId() {
		return id++;
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
			id++;
		} finally {
			lock.unlock();
		}
		return id;
	}
	
	/**
	 * 锁对象操作加上条件对象
	 * @return
	 */
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
				id++;
			} finally {
				lock.unlock();
			}
		} else {
			System.out.println("cannot get lock");
			return 1;
		}
		return id;
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
		
		while (id < 5000000) {
//			id = autoIncrement.getSyncMethodId();
//			id = autoIncrement.getLockId();
//			id = autoIncrement.getTryLockId();
			id = autoIncrement.getLockConditionId();
			System.out.println(" ID = " + id);
			if (!set.add(id)) {
				System.out.println("has repeat element");
				break;
			}
		}
	}
	
}