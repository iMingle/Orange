/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.beforeafter;

/**
 * 水槽
 * 
 * @since 1.8
 * @author Mingle
 */
public interface Tank {
	float getCapacity();
	float getVolume();
	void transferWater(float amount) throws OverflowException, UnderflowException;
}
