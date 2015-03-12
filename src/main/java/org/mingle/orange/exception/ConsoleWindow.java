/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年7月21日
 * @version 1.0
 */
public class ConsoleWindow {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private static final int DEFAULT_LEFT = 200;
	private static final int DEFAULT_TOP = 200;
	
	public static void init() {
		JFrame frame = new JFrame();
		frame.setTitle("ConsoleWindow");
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		frame.add(new JScrollPane(output));
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setLocation(DEFAULT_LEFT, DEFAULT_TOP);
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		
		// define a PrintStream that sends its bytes to the output text area
		PrintStream consoleStream = new PrintStream(new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {}
			/* (non-Javadoc)
			 * @see java.io.OutputStream#write(byte[], int, int)
			 */
			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				output.append(new String(b, off, len));
			}
		});
		
		// set both the System.out and the System.err to the stream
		System.setOut(consoleStream);
		System.setErr(consoleStream);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleWindow.init();
		System.out.println("Hello, Console");
	}

}
