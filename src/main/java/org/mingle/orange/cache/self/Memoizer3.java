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
 * 
 * @author Mingle
 */
public class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;
    
    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * 可能出现2个线程同时计算相同的数据,但是发生概率小于Memoizer2
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (null == f) {
            Callable<V>    eval = new Callable<V>() {

                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();    // 调用c.compute(arg);
        }
        
        try {
            return f.get();
        } catch (ExecutionException e) {
            LaunderThrowable.launderThrowable(e.getCause());
            return null;
        }
    }

}
