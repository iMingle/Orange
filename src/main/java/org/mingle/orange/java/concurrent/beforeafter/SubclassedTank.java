/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 子类化
 * 
 * @since 1.8
 * @author Mingle
 */
public class SubclassedTank extends TankImpl {
    protected void checkVolumeInvariant() throws AssertionError {
        float v = getVolume();
        float c = getCapacity();
        if (!(v >= 0.0 && v <= c))
            throw new AssertionError();
    }
    
    @Override
    public void transferWater(float amount) throws OverflowException,
            UnderflowException {
        checkVolumeInvariant();        // before-check
        
        try {
            super.transferWater(amount);
        } catch (OverflowException e) {
            throw e;
        } catch (UnderflowException e) {
            throw e;
        } finally {
            checkVolumeInvariant();    // after-check
        }
    }
}
