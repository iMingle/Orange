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

package org.mingle.orange.util;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

/**
 * Minimum object size is 16 bytes for modern 64-bit JDK since the object has 12-byte header,
 * padded to a multiple of 8 bytes. In 32-bit JDK, the overhead is 8 bytes, padded to a multiple of 4 bytes.
 * <p>
 * References have a typical size of 4 bytes on 32-bit platforms and on 64-bits platforms with heap boundary less
 * than 32Gb (-Xmx32G), and 8 bytes for this boundary above 32Gb.
 * <p>
 * This means that a 64-bit JVM usually requires 30-50% more heap space.
 * <p>
 * Especially relevant is to note that boxed types, arrays, Strings and other containers like multidimensional arrays
 * are memory costly since they add certain overhead. For example, when we compare int primitive (which consumes
 * only 4 bytes) to the Integer object which takes 16 bytes, we see that there is 300% memory overhead.
 *
 * @author mingle
 */
public class ObjectSizeFetcher {
    int a;
    int b;
    int d;
    char c;

    public static void main(String[] args) {
        System.out.println(ObjectSizeCalculator.getObjectSize(new ObjectSizeFetcher()));
    }
}
