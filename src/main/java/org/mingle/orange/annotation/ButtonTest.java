/**
 * @version 1.0 2014年6月22日
 * @author mingle
 */
package org.mingle.orange.annotation;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.junit.Test;

/**
 * @version 1.0 2014年6月22日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
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
