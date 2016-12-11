/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 水槽方法适配器
 * 
 * @since 1.0
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
