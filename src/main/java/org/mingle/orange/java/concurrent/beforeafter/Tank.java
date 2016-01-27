/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.beforeafter;

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
