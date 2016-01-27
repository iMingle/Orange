/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.cache.self;

import java.math.BigInteger;

/**
 * 耗时操作
 * 
 * @since 1.8
 * @author Mingle
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // 经过长时间的计算
        return new BigInteger(arg);
    }

}
