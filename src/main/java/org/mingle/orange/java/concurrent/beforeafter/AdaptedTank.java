/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 适配器
 * 
 * @since 1.8
 * @author Mingle
 */
public class AdaptedTank implements Tank {
	protected final Tank delegate;

	public AdaptedTank(Tank t) {
		this.delegate = t;
	}

	@Override
	public float getCapacity() {
		return delegate.getCapacity();
	}

	@Override
	public float getVolume() {
		return delegate.getVolume();
	}

	@Override
	public synchronized void transferWater(float amount) throws OverflowException,
			UnderflowException {
		checkVolumeInvariant();		// before-check
		
		try {
			delegate.transferWater(amount);
		} catch (OverflowException e) {
			throw e;
		} catch (UnderflowException e) {
			throw e;
		} finally {
			checkVolumeInvariant();	// after-check
		}
	}

	protected void checkVolumeInvariant() throws AssertionError {
		float v = getVolume();
		float c = getCapacity();
		if (!(v >= 0.0 && v <= c))
			throw new AssertionError();
	}
	
}
