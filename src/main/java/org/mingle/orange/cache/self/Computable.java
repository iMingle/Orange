/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.cache.self;

/**
 * 可计算
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
