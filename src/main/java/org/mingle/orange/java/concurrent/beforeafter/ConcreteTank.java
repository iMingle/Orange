/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 具体的水槽
 * 
 * @since 1.8
 * @author Mingle
 */
public class ConcreteTank extends AbstractTank {
	protected final float capacity = 10.f;
	protected float volume;

	@Override
	public float getCapacity() {
		return capacity;
	}

	@Override
	public float getVolume() {
		return volume;
	}

	@Override
	protected void doTransferWater(float amount) throws OverflowException,
			UnderflowException {
		// ... implementation code ...
	}

}
