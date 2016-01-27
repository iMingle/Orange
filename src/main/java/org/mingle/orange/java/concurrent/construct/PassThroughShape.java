/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.construct;

/**
 * 类分解
 * 
 * @since 1.8
 * @author Mingle
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
        return 1; /* ... */
    }

    protected double longCalculation2() {
        return 2; /* ... */
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
        return 3; /* ... */
    }

    protected double longCalculation4() {
        return 4; /* ... */
    }

}