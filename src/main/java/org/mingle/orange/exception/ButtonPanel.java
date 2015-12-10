/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.exception;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class ButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -215780346925182795L;

	public ButtonPanel() {
		// create buttons

		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");

		// add buttons to panel

		add(yellowButton);
		add(blueButton);
		add(redButton);

		// create button actions

		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);

		// associate actions with buttons

		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
	}

	/**
	 * An action listener that sets the panel's background color.
	 */
	private class ColorAction implements ActionListener {
		public ColorAction(Color c) {
			backgroundColor = c;
		}

		public void actionPerformed(ActionEvent event) {
			setBackground(backgroundColor);
		}

		private Color backgroundColor;
	}
}
