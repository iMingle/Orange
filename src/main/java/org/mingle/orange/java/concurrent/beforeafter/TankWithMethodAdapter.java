/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 水槽方法适配器
 * 
 * @since 1.8
 * @author Mingle
 */
public class TankWithMethodAdapter {
	protected final Tank delegate;

	public TankWithMethodAdapter(Tank t) {
		delegate = t;
	}

	public float getCapacity() {
		return delegate.getCapacity();
	}

	public float getVolume() {
		return delegate.getVolume();
	}

	protected void checkVolumeInvariant() throws AssertionError {
		float v = getVolume();
		float c = getCapacity();
		if (!(v >= 0.0 && v <= c))
			throw new AssertionError();
	}

	protected void runWithinBeforeAfterChecks(TankOp cmd)
			throws OverflowException, UnderflowException {
		checkVolumeInvariant(); // before-check

		try {
			cmd.op();
		} catch (OverflowException e) {
			throw e;
		} catch (UnderflowException e) {
			throw e;
		} finally {
			checkVolumeInvariant(); // after-check
		}
	}

	protected void doTransferWater(float amount) throws OverflowException,
			UnderflowException {
		// ... implementation code ...
	}

	public synchronized void transferWater(final float amount)
			throws OverflowException, UnderflowException {

		runWithinBeforeAfterChecks(new TankOp() {
			public void op() throws OverflowException, UnderflowException {
				doTransferWater(amount);
			}
		});
	}
}
