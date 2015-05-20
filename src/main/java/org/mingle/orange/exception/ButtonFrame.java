/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.exception;

import javax.swing.JFrame;

/**
 *
 * @since 1.8
 * @author Mingle
 */
class ButtonFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5397697534999754286L;

	public ButtonFrame() {
		setTitle("ButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		// add panel to frame

		ButtonPanel panel = new ButtonPanel();
		add(panel);
	}

	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
}
