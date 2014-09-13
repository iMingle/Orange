package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

public class DoublingTest {
	
	public static double timeTrial(int N) {
		int MAX = 1000000;
		int[] a = new int[N];
		
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform(-MAX, MAX);
		}
		
		Stopwatch timer = new Stopwatch();
		ThreeSum.count(a);
		return timer.elapsedTime();
	}

	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
        
		for (int N = 2; true; N += 2) {
//			System.out.printf("%7d %5.1f\n", N, timeTrial(N));
//			StdDraw.point(N / 10, timeTrial(N) * 1000);
			StdDraw.point(10 * Math.log(N / 10), 10 * Math.log(timeTrial(N) * 1000));
		}
	}

}
