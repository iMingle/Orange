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

package org.mingle.orange.cache.self;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存
 * 
 * @author mingle
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
