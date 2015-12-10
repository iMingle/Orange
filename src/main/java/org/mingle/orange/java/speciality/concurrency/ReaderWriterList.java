/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock允许同时有多个读取者,只要他们都不试图写入.
 * 如果写锁已经被其他任务持有,那么任何读取者都不能访问,直至这个写锁被释放为止.
 * 
 * @since 1.8
 * @author Mingle
 */
public class ReaderWriterList<T> {
	private ArrayList<T> lockedList;
	// Make the ordering fair
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
	
	public ReaderWriterList(int size, T initialValue) {
		lockedList = new ArrayList<>(Collections.nCopies(size, initialValue));
	}
	
	public T set(int index, T element) {
		Lock wlock = lock.writeLock();
		wlock.lock();
		try {
			return lockedList.set(index, element);
		} finally {
			wlock.unlock();
		}
	}
	
	public T get(int index) {
		Lock rlock = lock.readLock();
		rlock.lock();
		try {
			if (lock.getReadLockCount() > 1)
				System.out.println(lock.getReadLockCount());
			return lockedList.get(index);
		} finally {
			rlock.unlock();
		}
	}

	public static void main(String[] args) {
		new ReaderWriterListTest(30, 1);
	}

}

class ReaderWriterListTest {
	ExecutorService exec = Executors.newCachedThreadPool();
	private final static int SIZE = 100;
	private static Random rand = new Random(47);
	private ReaderWriterList<Integer> list = new ReaderWriterList<Integer>(SIZE, 0);
	
	private class Writer implements Runnable {

		@Override
		public void run() {
			try {
				for (int i = 0; i < 20; i++) {
					list.set(i, rand.nextInt());
					TimeUnit.MILLISECONDS.sleep(100);
				}
			} catch (InterruptedException e) {
				// 可接受的方式退出
			}
			System.out.println("Writer finished, shutting down");
			exec.shutdownNow();
		}
		
	}
	
	private class Reader implements Runnable {

		@Override
		public void run() {
			try {
				while (!Thread.interrupted()) {
					for (int i = 0; i < 20; i++) {
						list.get(i);
						TimeUnit.MILLISECONDS.sleep(1);
					}
				}
			} catch (InterruptedException e) {
				// 可接受的方式退出
			}
		}
		
	}
	
	public ReaderWriterListTest(int readers, int writers) {
		for (int i = 0; i < readers; i++)
			exec.execute(new Reader());
		for (int i = 0; i < writers; i++)
			exec.execute(new Writer());
	}
}