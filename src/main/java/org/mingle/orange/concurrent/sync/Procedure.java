/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.sync;

/**
 * 遍历-同步聚合操作
 * 
 * @since 1.8
 * @author Mingle
 */
public interface Procedure {
	void apply(Object obj);
}
