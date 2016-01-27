/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.cache.self;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存
 * 
 * @since 1.8
 * @author Mingle
 */
public class Memoizer1<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;
    
    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * synchronized一次只能有一个线程执行,有伸缩性问题
     */
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (null == result) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        
        return result;
    }

}
