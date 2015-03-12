package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdDraw;

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
