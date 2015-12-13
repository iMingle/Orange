/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.limit;

/**
 * 组策略-环(多个相互交互的对象一起限制一个资源的情况下)
 * 
 * @since 1.8
 * @author Mingle
 */
public class PrintService {
	protected PrintService neighbor = null; // node to take from
	protected Printer printer = null;

	public synchronized void print(byte[] doc) {
		getPrinter().printDocument(doc);
	}

	protected Printer getPrinter() { // PRE: synch lock held
		if (printer == null) // need to take from neighbor
			printer = neighbor.takePrinter();
		return printer;
	}

	synchronized Printer takePrinter() { // called from others
		if (printer != null) {
			Printer p = printer; // implement take protocol
			printer = null;
			return p;
		} else
			return neighbor.takePrinter(); // propagate
	}

	// initialization methods called only during start-up
	synchronized void setNeighbor(PrintService n) {
		neighbor = n;
	}

	synchronized void givePrinter(Printer p) {
		printer = p;
	}

	// Sample code to initialize a ring of new services
	public static void startUpServices(int nServices, Printer p)
			throws IllegalArgumentException {

		if (nServices <= 0 || p == null)
			throw new IllegalArgumentException();

		PrintService first = new PrintService();
		PrintService pred = first;

		for (int i = 1; i < nServices; ++i) {
			PrintService s = new PrintService();
			s.setNeighbor(pred);
			pred = s;
		}

		first.setNeighbor(pred);
		first.givePrinter(p);
	}
}
