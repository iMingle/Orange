/*
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
 * limitations under the License.
 */

package org.mingle.orange.java.concurrent.beforeafter;

/**
 * 模板方法
 *
 * @author mingle
 */
public abstract class AbstractTank implements Tank {
    protected void checkVolumeInvariant() throws AssertionError {
        float v = getVolume();
        float c = getCapacity();
        if (!(v >= 0.0 && v <= c))
            throw new AssertionError();
    }

    protected abstract void doTransferWater(float amount) throws OverflowException, UnderflowException;

    @Override
    public synchronized void transferWater(float amount) throws OverflowException, UnderflowException {
        checkVolumeInvariant();        // before-check

        try {
            doTransferWater(amount);
        } catch (OverflowException | UnderflowException e) {
            throw e;
        } finally {
            checkVolumeInvariant();    // after-check
        }
    }

}
