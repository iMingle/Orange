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

import edu.princeton.cs.algs4.StdDraw;

public class Interval1D {
    
    private double startLine;
    private double length;

    public double getStartLine() {
        return startLine;
    }

    public void setStartLine(double startLine) {
        this.startLine = startLine;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Interval1D(double lo, double hi) {
        this.startLine = lo;
        this.length = hi;
    }
    
    public boolean intersects(Interval1D that) {
        if (this.startLine + this.length < that.startLine) return false;
        if (that.startLine + that.length < this.startLine) return false;
        return true;
    }
    
    public boolean contains(double x) {
        return (startLine <= x) && (x <= startLine + length);
    }

    public double length() {
        return this.length;
    }
    
    public void draw() {
        StdDraw.line(startLine, 0, startLine, 100);
        StdDraw.line(startLine + length, 0, startLine + length, 100);
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
        
        Interval1D inter = new Interval1D(10, 20);
        
        inter.draw();
    }
}
