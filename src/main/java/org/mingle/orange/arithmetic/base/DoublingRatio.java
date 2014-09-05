package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdRandom;

public class DoublingRatio {

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
		double prev = timeTrial(125);
		
		for (int N = 250; true; N += N) {
			double time = timeTrial(N);
			System.out.printf("%6d %7.1f ", N, time);
			System.out.printf("%5.1f\n", time/prev);
			prev = time;
		}
	}
}
