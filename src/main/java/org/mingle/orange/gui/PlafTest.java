/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class PlafTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PlatFrame frame = new PlatFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

/**
 * a frame with button panel for changing look and feel
 */
class PlatFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 678829679977158871L;
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	private JPanel panel;
	
	public PlatFrame() {
		this.setTitle("PlatTest");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		
		for (UIManager.LookAndFeelInfo info : infos) {
			makeButton(info.getName(), info.getClassName());
		}
		
		this.add(panel);
	}

	/**
	 * Makes a button to change the pluggable look and feel
	 * @param name
	 * @param className
	 */
	private void makeButton(String name, final String className) {
		JButton button = new JButton(name);
		panel.add(button);
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				try {
					UIManager.setLookAndFeel(className);
					SwingUtilities.updateComponentTreeUI(panel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
}