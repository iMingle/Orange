package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdDraw;

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
        StdDraw.rectangle(this.x.getStartLine(), this.y.getStartLine(), this.x.getLength(), this.y.getLength());
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
        
        Interval1D in1 = new Interval1D(20, 4);
        Interval1D in2 = new Interval1D(20, 5);
        Interval2D inter = new Interval2D(in1, in2);
        
        inter.draw();
    }
}
