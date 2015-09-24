/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.servicesinthread.parallel;

/**
 * 关卡
 * 
 * @since 1.8
 * @author Mingle
 */
public class CyclicBarrier {
	protected final int parties;
	protected int count; // parties currently being waited for
	protected int resets = 0; // times barrier has been tripped

	CyclicBarrier(int c) {
		count = parties = c;
	}

	synchronized int barrier() throws InterruptedException {
		int index = --count;
		if (index > 0) { // not yet tripped
			int r = resets; // wait until next reset

			do {
				wait();
			} while (resets == r);

		} else { // trip
			count = parties; // reset count for next time
			++resets;
			notifyAll(); // cause all other parties to resume
		}

		return index;
	}
}

class Segment implements Runnable { // Code sketch
	final CyclicBarrier bar; // shared by all segments

	Segment(CyclicBarrier b) {
		bar = b;
	}

	void update() {
	}

	public void run() {
		try {
			for (int i = 0; i < 10 /* iterations */; ++i) {
				update();
				bar.barrier();
			}
		} catch (InterruptedException ie) {}
	}
}

class Problem {
	int size;
}

class Driver {
	int granularity = 1;

	void compute(Problem problem) throws Exception {
		int n = problem.size / granularity;
		CyclicBarrier barrier = new CyclicBarrier(n);
		Thread[] threads = new Thread[n];

		// create
		for (int i = 0; i < n; ++i)
			threads[i] = new Thread(new Segment(barrier));

		// trigger
		for (int i = 0; i < n; ++i)
			threads[i].start();

		// await termination
		for (int i = 0; i < n; ++i)
			threads[i].join();
	}
}

class JacobiSegment implements Runnable { // Incomplete
	// These are same as in Leaf class version:
	static final double EPSILON = 0.001;
	double[][] A;
	double[][] B;
	final int firstRow;
	final int lastRow;
	final int firstCol;
	final int lastCol;
	volatile double maxDiff;
	int steps = 0;

	void update() { /* Nearly same as Leaf.run */
	}

	final CyclicBarrier bar;
	final JacobiSegment[] allSegments; // needed for convergence check
	volatile boolean converged = false;

	JacobiSegment(double[][] A, double[][] B, int firstRow, int lastRow,
			int firstCol, int lastCol, CyclicBarrier b,
			JacobiSegment[] allSegments) {
		this.A = A;
		this.B = B;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCol = firstCol;
		this.lastCol = lastCol;
		this.bar = b;
		this.allSegments = allSegments;
	}

	public void run() {
		try {
			while (!converged) {
				update();
				int myIndex = bar.barrier(); // wait for all to update
				if (myIndex == 0)
					convergenceCheck();
				bar.barrier(); // wait for convergence check
			}
		} catch (Exception ex) {
			// clean up ...
		}
	}

	void convergenceCheck() {
		for (int i = 0; i < allSegments.length; ++i)
			if (allSegments[i].maxDiff > EPSILON)
				return;
		for (int i = 0; i < allSegments.length; ++i)
			allSegments[i].converged = true;
	}
}