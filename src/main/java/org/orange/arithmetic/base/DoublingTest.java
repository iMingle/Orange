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
import org.orange.arithmetic.util.StandardDraw;

public class DoublingTest {
    
    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        
        for (int i = 0; i < N; i++) {
            a[i] = RandomUtil.uniform(-MAX, MAX);
        }
        
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        StandardDraw.setCanvasSize(800, 600);
        StandardDraw.setXscale(0, 100);
        StandardDraw.setYscale(0, 100);
        StandardDraw.setPenRadius(.005);
        
        for (int N = 2; true; N += 2) {
//            System.out.printf("%7d %5.1f\n", N, timeTrial(N));
//            StandardDraw.point(N / 10, timeTrial(N) * 1000);
            StandardDraw.point(10 * Math.log(N / 10), 10 * Math.log(timeTrial(N) * 1000));
        }
    }

}
