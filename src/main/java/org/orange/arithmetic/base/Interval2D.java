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

import org.orange.arithmetic.util.StandardDraw;

public class Interval2D {
    private final Interval1D x;
    private final Interval1D y;

    public Interval1D getX() {
        return x;
    }

    public Interval1D getY() {
        return y;
    }

    public Interval2D(Interval1D x, Interval1D y) {
        this.x = x;
        this.y = y;
    }

    public double area() {
        return this.x.getLength() * this.y.getLength();
    }

    public boolean contains(Point2D point) {
        if ((point.getX() >= this.x.getStartLine() && point.getX() <= this.x.getStartLine() + this.x.getLength()) &&
                (point.getY() >= this.y.getStartLine() && point.getY() <= this.y.getStartLine() + this.y.getLength())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean intersect(Interval2D that) {
        return true;
    }

    public void draw() {
        StandardDraw.rectangle(this.x.getStartLine(), this.y.getStartLine(), this.x.getLength(), this.y.getLength());
    }

    public static void main(String[] args) {
        StandardDraw.setCanvasSize(800, 600);
        StandardDraw.setXscale(0, 100);
        StandardDraw.setYscale(0, 100);
        StandardDraw.setPenRadius(.005);

        Interval1D in1 = new Interval1D(20, 4);
        Interval1D in2 = new Interval1D(20, 5);
        Interval2D inter = new Interval2D(in1, in2);

        inter.draw();
    }
}
