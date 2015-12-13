/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.cancelclose;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 素数生成器的取消
 * 
 * @since 1.8
 * @author Mingle
 */
public class PrimeGenerator implements Runnable {
	private final List<BigInteger> primes = new ArrayList<>();
	private volatile boolean cancelled;

	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}
	
	public synchronized List<BigInteger> get() {
		return new ArrayList<>(primes);
	}
	
	public static List<BigInteger> oneSecondOfPrimes() throws InterruptedException {
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} finally {
			generator.cancel();
		}
		
		return generator.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
		PrimeGenerator.oneSecondOfPrimes();
	}
}
