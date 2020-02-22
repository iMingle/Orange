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

package org.orange.java.concurrent.beforeafter;

/**
 * 具体的水槽
 *
 * @author mingle
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
    protected void doTransferWater(float amount) throws OverflowException, UnderflowException {
        // ... implementation code ...
    }

}
