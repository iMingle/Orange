/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年7月13日
 * @version 1.0
 */
public class ComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				HelloFrame frame = new HelloFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
}

class HelloFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1229708991295349611L;
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	public HelloFrame() {
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setTitle("Hello Mingle");
		
		HelloPanel panel = new HelloPanel();
		panel.setBackground(SystemColor.windowText);
		this.add(panel);
	}
}

class HelloPanel extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6714555395101746025L;
	
	private static final int MESSAGE_X = 75;
	private static final int MESSAGE_Y = 100;

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
//		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.RED.brighter().brighter().brighter().brighter().brighter().brighter().brighter());
		g.drawString("Create Component", MESSAGE_X, MESSAGE_Y);
		super.paintComponent(g);
	}
}