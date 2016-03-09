/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.cache.self;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存
 * 
 * @since 1.0
 * @author Mingle
 */
public class Memoizer2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> c;
    
    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * 可能出现2个线程同时计算相同的数据
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (null == result) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        
        return result;
    }

}
