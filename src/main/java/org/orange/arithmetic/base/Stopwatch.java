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

package org.orange.arithmetic.base;

import org.orange.arithmetic.util.RandomUtil;

public class Stopwatch {
    private final long start;
    
    public Stopwatch() {
        start = System.currentTimeMillis();
    }
    
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        
        return (now - start) / 1000.0;
    }
    
    public static void main(String[] args) {
        int[] a = new int[16000];
        for (int i = 0; i < a.length; i++) {
            a[i] = RandomUtil.uniform(-1000000, 1000000);
        }
        
        Stopwatch stopwatch = new Stopwatch();
        int count = ThreeSum.count(a);
        
        double time = stopwatch.elapsedTime();
        
        System.out.println(count + " triples " + time + " seconds");
    }
}
