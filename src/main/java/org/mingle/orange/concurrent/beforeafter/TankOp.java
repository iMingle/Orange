/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.beforeafter;

/**
 * 方法适配器接口
 * 
 * @since 1.8
 * @author Mingle
 */
public interface TankOp {
	void op() throws OverflowException, UnderflowException;
}
