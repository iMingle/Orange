/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.thread;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * The component that draws the balls.
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年9月13日
 * @version 1.0
 */
public class BallComponent extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -541334173689699317L;
	
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	
	/**
	 * add a ball to the component.
	 * @param b
	 */
	public void add(Ball b) {
		balls.add(b);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getShape());
		}
	}
}
