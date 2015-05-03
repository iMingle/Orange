/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.exception;

import javax.swing.JFrame;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年7月22日
 * @version 1.0
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
