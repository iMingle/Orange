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

package org.orange.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author mingle
 */
public class CasNumberRange {
    private static class IntPair {
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            super();
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0, 0));

    public int getLower() {
        return values.get().lower;
    }

    public int getUpper() {
        return values.get().upper;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i > oldv.upper)
                throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
            IntPair newv = new IntPair(i, oldv.upper);
            if (values.compareAndSet(oldv, newv))
                return;
        }
    }

    public void setUpper(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i < oldv.lower)
                throw new IllegalArgumentException("Can't set upper to " + i + " < lower");
            IntPair newv = new IntPair(oldv.lower, i);
            if (values.compareAndSet(oldv, newv))
                return;
        }
    }
}
