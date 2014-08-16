package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class VisualAccumulator {
	private double total;
	private int N;
	
	public VisualAccumulator(int trials, double max) {
		StdDraw.setXscale(0, trials);
		StdDraw.setYscale(0, max);
		StdDraw.setPenRadius(.005);
	}
	
	public void addDataValue(double val) {
		N++;
		total += val;
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(N, val);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(N, total / N);
	}
	
	public double mean() {
		return total / N;
	}

	public String toString() {
		return "Mean (" + N + " values): " + String.format("%7.5f", mean());
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		VisualAccumulator v = new VisualAccumulator(2000, 1.0);
		
		for (int i = 0; i < 2000; i++) {
			v.addDataValue(StdRandom.random());
		}
		
		StdOut.println(v);
	}

}
