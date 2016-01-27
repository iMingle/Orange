/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.limit;

import java.awt.Point;

/**
 * 跨方法的限制
 * 
 * @since 1.8
 * @author Mingle
 */
public class Plotter {
    public void showNextPoint() {
        Point p = new Point();
        p.x = computeX();
        p.y = computeY();
        display(p);
    }

    int computeX() {
        return 1;
    }

    int computeY() {
        return 1;
    }

    protected void display(Point p) {
        // somehow arrange to show p.
    }
}
