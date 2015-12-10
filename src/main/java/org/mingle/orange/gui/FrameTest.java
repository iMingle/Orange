/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class FrameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				SimpleFrame sf = new SimpleFrame();
				sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sf.setVisible(true);
				// make the frame max
				sf.setExtendedState(Frame.MAXIMIZED_BOTH);
				// the frame must be displayable
//				sf.setUndecorated(true);
			}
			
		});
	}

}

class SimpleFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2571992970831415674L;
	
//	private static final int width = 800;
//	private static final int height = 600;
	
	/**
	 * 
	 */
	public SimpleFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dimension = kit.getScreenSize();
		
		Image image = kit.getImage("src/main/resources/images/icon.gif");
		
		this.setSize(dimension.width, dimension.height);
		this.setIconImage(image);
		this.setTitle("Mingle's frame test");
	}
}