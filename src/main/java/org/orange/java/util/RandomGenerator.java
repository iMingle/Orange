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

package org.orange.java.util;

import java.util.*;

/**
 * Generators that produce random values.
 * 
 * @author mingle
 */
public class RandomGenerator {
    private static Random r = new Random(47);

    public static class Boolean implements Generator<java.lang.Boolean> {
        public java.lang.Boolean next() {
            return r.nextBoolean();
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        public java.lang.Byte next() {
            return (byte) r.nextInt();
        }
    }

    public static class Character implements Generator<java.lang.Character> {
        public java.lang.Character next() {
            return CountingGenerator.chars[r
                    .nextInt(CountingGenerator.chars.length)];
        }
    }

    public static class String extends CountingGenerator.String {
        // Plug in the random Character generator:
        {
            cg = new Character();
        } // Instance initializer

        public String() {
        }

        public String(int length) {
            super(length);
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        public java.lang.Short next() {
            return (short) r.nextInt();
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int mod = 10000;

        public Integer() {
        }

        public Integer(int modulo) {
            mod = modulo;
        }

        public java.lang.Integer next() {
            return r.nextInt(mod);
        }
    }

    public static class Long implements Generator<java.lang.Long> {
        private int mod = 10000;

        public Long() {
        }

        public Long(int modulo) {
            mod = modulo;
        }

        public java.lang.Long next() {
            return new java.lang.Long(r.nextInt(mod));
        }
    }

    public static class Float implements Generator<java.lang.Float> {
        public java.lang.Float next() {
            // Trim all but the first two decimal places:
            int trimmed = Math.round(r.nextFloat() * 100);
            return ((float) trimmed) / 100;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        public java.lang.Double next() {
            long trimmed = Math.round(r.nextDouble() * 100);
            return ((double) trimmed) / 100;
        }
    }
}
