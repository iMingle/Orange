/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 * 
 * @since 1.8
 * @author Mingle
 */
public class FillAndEmpty {
	static final int SIZE = 1024; // buffer size, for demo
	protected Exchanger<byte[]> exchanger = new Exchanger<>();

	protected byte readByte() {
		return 1; /* ... */
	}

	protected void useByte(byte b) { /* ... */
	}

	public void start() {
		new Thread(new FillingLoop()).start();
		new Thread(new EmptyingLoop()).start();
	}

	class FillingLoop implements Runnable { // inner class
		public void run() {
			byte[] buffer = new byte[SIZE];
			int position = 0;

			try {
				for (;;) {
					if (position == SIZE) {
						buffer = (byte[]) (exchanger.exchange(buffer));
						position = 0;
					}

					buffer[position++] = readByte();
				}
			} catch (InterruptedException ie) {
			} // die
		}
	}

	class EmptyingLoop implements Runnable { // inner class
		public void run() {
			byte[] buffer = new byte[SIZE];
			int position = SIZE; // force exchange first time through

			try {
				for (;;) {
					if (position == SIZE) {
						buffer = (byte[]) (exchanger.exchange(buffer));
						position = 0;
					}

					useByte(buffer[position++]);
				}
			} catch (InterruptedException ex) {
			} // die
		}
	}
}
