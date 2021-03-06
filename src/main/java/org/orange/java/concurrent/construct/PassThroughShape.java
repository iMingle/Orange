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

package org.orange.java.concurrent.construct;

/**
 * 类分解
 *
 * @author mingle
 */
public class PassThroughShape {
    protected final AdjustableLoc loc = new AdjustableLoc(0, 0);
    protected final AdjustableDim dim = new AdjustableDim(0, 0);

    public double x() {
        return loc.x();
    }

    public double y() {
        return loc.y();
    }

    public double width() {
        return dim.width();
    }

    public double height() {
        return dim.height();
    }

    public void adjustLocation() {
        loc.adjust();
    }

    public void adjustDimensions() {
        dim.adjust();
    }
}

class AdjustableLoc {
    protected double x;
    protected double y;

    public AdjustableLoc(double initX, double initY) {
        x = initX;
        y = initY;
    }

    public synchronized double x() {
        return x;
    }

    public synchronized double y() {
        return y;
    }

    public synchronized void adjust() {
        x = longCalculation1();
        y = longCalculation2();
    }

    protected double longCalculation1() {
        return 1;
    }

    protected double longCalculation2() {
        return 2;
    }

}

class AdjustableDim {
    protected double width;
    protected double height;

    public AdjustableDim(double initW, double initH) {
        width = initW;
        height = initH;
    }

    public synchronized double width() {
        return width;
    }

    public synchronized double height() {
        return height;
    }

    public synchronized void adjust() {
        width = longCalculation3();
        height = longCalculation4();
    }

    protected double longCalculation3() {
        return 3;
    }

    protected double longCalculation4() {
        return 4;
    }

}
