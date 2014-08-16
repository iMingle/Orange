/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @date 2014年7月13日
 * @version 1.0
 */
public class ButtonTest {

	/**
	 * @param args
	 */
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

class ButtonFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5532519298672050517L;
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	private JPanel panel;
	
	public ButtonFrame() {
		this.setTitle("ButtonTest");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// add button
		JButton yellow = new JButton("Yellow");
		JButton blue = new JButton("Blue");
		JButton red = new JButton("Red");
		
		panel = new JPanel();
		
		panel.add(yellow);
		panel.add(blue);
		panel.add(red);
		
		this.add(panel);
		
		yellow.addActionListener(new ColorAction(Color.YELLOW));
		blue.addActionListener(new ColorAction(Color.BLUE));
		red.addActionListener(new ColorAction(Color.RED));
	}
	
	public void makeButton(String label, final Color color) {
		JButton button = new JButton(label);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(color);
			}
		});
	}
	
	private class ColorAction implements ActionListener {
		private Color color;
		
		public ColorAction(Color color) {
			this.color = color;
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			panel.setBackground(color);
		}
		
	}
}