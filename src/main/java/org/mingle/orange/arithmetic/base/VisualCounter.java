package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

public class VisualCounter {
	
	private int count;
	private int N;
	private int max;
	
	public VisualCounter(int n, int max) {
		N = n;
		this.max = max;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void increment() {
		if (N > 0) {
			count++;
			if (count > max) {
				count = max;
			}
			N--;
		}
	}
	
	public void decrement() {
		if (N > 0) {
			count--;
			if (Math.abs(count) > max) {
				count = -max;
			}
			N--;
		}
	}
	
	public static void main(String[] args) {
		VisualCounter v = new VisualCounter(10, 5);
		int temp = 0;
		StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
		for (int i = 0; i < 20; i++) {
			temp = StdRandom.uniform(2);
			
			if (0 == temp) {
				v.increment();
			} else if (1 == temp) {
				v.decrement();
			}
			
			StdDraw.rectangle(30, 30, 10, v.getCount() + 20);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			StdDraw.clear();
		}
		
	}

}
