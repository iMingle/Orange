/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年7月13日
 * @version 1.0
 */
public class DrawTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				DrawFrame frame = new DrawFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
}

/**
 * a frame that contains a panel with drawings
 */
class DrawFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8018642326062624559L;
	
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	
	public DrawFrame() {
		this.setTitle("Draw Frame");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// add panel to frame
		DrawComponent component = new DrawComponent();
		this.add(component);
	}
}

/**
 * A component that displays rectangles and ellipses
 */
class DrawComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1517786089688913600L;

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// draw a rectangle
		double leftX = 100;
		double topY = 100;
		double width = 200;
		double height = 150;
		
		Rectangle2D rectangle = new Rectangle2D.Double(leftX, topY, width, height);
		g2.draw(rectangle);
		
		// draw a ellipse with rectangle
		Ellipse2D ellipse = new Ellipse2D.Double();
		ellipse.setFrame(rectangle);
		g2.draw(ellipse);
		
		// draw a line
		g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));
		
		// draw a circle
		double centerX = rectangle.getCenterX();
		double centerY = rectangle.getCenterY();
		double radius = 150;
		
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
		g2.draw(circle);
	}
	
}