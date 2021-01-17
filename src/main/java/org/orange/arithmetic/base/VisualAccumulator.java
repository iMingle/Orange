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

public class VisualAccumulator {
    private double total;
    private int N;

    public VisualAccumulator(int trials, double max) {
        StandardDraw.setXscale(0, trials);
        StandardDraw.setYscale(0, max);
        StandardDraw.setPenRadius(.005);
    }

    public void addDataValue(double val) {
        N++;
        total += val;
        StandardDraw.setPenColor(StandardDraw.DARK_GRAY);
        StandardDraw.point(N, val);
        StandardDraw.setPenColor(StandardDraw.RED);
        StandardDraw.point(N, total / N);
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "Mean (" + N + " values): " + String.format("%7.5f", mean());
    }

    public static void main(String[] args) {
        VisualAccumulator v = new VisualAccumulator(2000, 1.0);

        for (int i = 0; i < 2000; i++) {
            v.addDataValue(RandomUtil.uniform());
        }

        System.out.println(v);
    }

}
