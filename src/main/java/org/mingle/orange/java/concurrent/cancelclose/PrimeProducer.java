/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.cancelclose;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 素数生成器的取消
 * 
 * @since 1.8
 * @author Mingle
 */
public class PrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	
	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted()) {
				queue.put(p = p.nextProbablePrime());
			}
		} catch (InterruptedException e) {
			/* 允许线程退出 */
		}
	}

	public void cancel() {
		interrupt();
	}
	
}
