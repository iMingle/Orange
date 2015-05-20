/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.annotation;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.junit.Test;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public class ButtonTest {

	/**
	 * @param args
	 */
	@Test
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				ButtonFrame frame = new ButtonFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
