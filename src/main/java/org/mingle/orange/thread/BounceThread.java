/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.thread;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * shows animated bouncing ball.
 * 
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年9月13日
 * @version 1.0
 */
public class BounceThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

/**
 * a runnable that animates a bouncing ball.
 */
class BourceRunnable implements Runnable {
	private Ball ball;
	private Component component;
	private static final int STEPS = 1000;
	private static final int DELAY = 5;

	/**
	 * @param ball
	 * @param component
	 */
	public BourceRunnable(Ball ball, Component component) {
		super();
		this.ball = ball;
		this.component = component;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			for (int i = 1; i < STEPS; i++) {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
}

/**
 * the frame with ball component and buttons.
 */
class BounceFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1032733806982376494L;
	
	private BallComponent comp;
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;

	/**
	 * Constructs the frame with the component for showing the bouncing ball and
	 * Start and Close buttons
	 */
	public BounceFrame() {
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setTitle("Bounce");
		
		comp = new BallComponent();
		this.add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		this.addButton(buttonPanel, "Start", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addBall();
			}

		});
		
		this.addButton(buttonPanel, "Close", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Adds a button to a container.
	 * @param c
	 * @param title
	 * @param listener
	 */
	private void addButton(Container c, String title,
			ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/*
	 * Adds a bouncing ball to the panel and starts a thread to make it bounce.
	 */
	public void addBall() {
		Ball b = new Ball();
		comp.add(b);
		Runnable r = new BourceRunnable(b, comp);
		Thread t = new Thread(r);
		t.start();
	}
}
