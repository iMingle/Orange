/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 方法适配器接口
 * 
 * @since 1.8
 * @author Mingle
 */
public interface TankOp {
    void op() throws OverflowException, UnderflowException;
}
