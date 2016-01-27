/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 水槽实现类
 * 
 * @since 1.8
 * @author Mingle
 */
public class TankImpl implements Tank {

    @Override
    public float getCapacity() {
        return 1.0f;
    }

    @Override
    public float getVolume() {
        return 1.0f;
    }

    @Override
    public void transferWater(float amount) throws OverflowException,
            UnderflowException {}

}
