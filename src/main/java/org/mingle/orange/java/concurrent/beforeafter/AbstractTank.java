/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 模板方法
 * 
 * @since 1.8
 * @author Mingle
 */
public abstract class AbstractTank implements Tank {
    protected void checkVolumeInvariant() throws AssertionError {
        float v = getVolume();
        float c = getCapacity();
        if (!(v >= 0.0 && v <= c))
            throw new AssertionError();
    }

    protected abstract void doTransferWater(float amount) 
               throws OverflowException, UnderflowException;

    @Override
    public synchronized void transferWater(float amount) throws OverflowException,
            UnderflowException {
        checkVolumeInvariant();        // before-check
        
        try {
            doTransferWater(amount);
        } catch (OverflowException e) {
            throw e;
        } catch (UnderflowException e) {
            throw e;
        } finally {
            checkVolumeInvariant();    // after-check
        }
    }

}
