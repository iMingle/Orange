/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.cache.self;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.mingle.orange.util.LaunderThrowable;

/**
 * 缓存
 * 问题1:如果计算取消或检测到RuntimeExeption,需要移除Future
 * 问题2:缓存逾期问题,可以通过FutureTask的子类来解决
 * 问题3:缓存清理问题,即移除旧的计算结果以便为新的计算结果腾出空间,从而使缓存不会消耗过多的内存
 * 
 * @since 1.8
 * @author Mingle
 */
public class Memoizer<A, V> implements Computable<A, V> {
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private final Computable<A, V> c;
	
	public Memoizer(Computable<A, V> c) {
		this.c = c;
	}

	/**
	 * 通过putIfAbsent方法确保原子性,不可能出现2个线程同时计算相同的数据的情况
	 */
	@Override
	public V compute(A arg) throws InterruptedException {
		Future<V> f = cache.get(arg);
		if (null == f) {
			Callable<V>	eval = new Callable<V>() {

				@Override
				public V call() throws Exception {
					return c.compute(arg);
				}
			};
			
			FutureTask<V> ft = new FutureTask<>(eval);
			f = ft;
			cache.putIfAbsent(arg, ft);
			ft.run();	// 调用c.compute(arg);
		}
		
		try {
			return f.get();
		} catch (ExecutionException e) {
			LaunderThrowable.launderThrowable(e.getCause());
			return null;
		}
	}

}
