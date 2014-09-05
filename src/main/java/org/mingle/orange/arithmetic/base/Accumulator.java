package org.mingle.orange.arithmetic.base;

public class Accumulator {
	private double total;
	private int N;
	
	public void addDataValue(double val) {
		N++;
		total += val;
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
	}

}
