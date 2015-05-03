/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.thread;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This program animates a sort algorithm.
 * 
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class AlgorithmAnimation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new AnimationFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

/**
 * This frame shows the array as it is sorted, together with buttons to
 * single-step the animation or to run it without interruption.
 */
class AnimationFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1903321498984042398L;
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;

	public AnimationFrame() {
		ArrayComponent comp = new ArrayComponent();
		add(comp, BorderLayout.CENTER);

		final Sorter sorter = new Sorter(comp);

		JButton runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sorter.setRun();
			}
		});

		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sorter.setStep();
			}
		});

		JPanel buttons = new JPanel();
		buttons.add(runButton);
		buttons.add(stepButton);
		add(buttons, BorderLayout.NORTH);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		Thread t = new Thread(sorter);
		t.start();
	}

}

/**
 * This runnable executes a sort algorithm. When two elements are compared, the
 * algorithm pauses and updates a component.
 */
class Sorter implements Runnable {
	private Double[] values;
	private ArrayComponent component;
	private Semaphore gate;
	private static final int DELAY = 100;
	private volatile boolean run;
	private static final int VALUES_LENGTH = 30;

	/**
	 * Constructs a Sorter.
	 * 
	 * @param values
	 *            the array to be sorted
	 * @param comp
	 *            the component on which to display the sorting progress
	 */
	public Sorter(ArrayComponent comp) {
		values = new Double[VALUES_LENGTH];
		for (int i = 0; i < values.length; i++)
			values[i] = new Double(Math.random());
		this.component = comp;
		this.gate = new Semaphore(1); // 将信号量初始化为 1，使得它在使用时最多只有一个可用的许可，从而可用作一个相互排斥的锁。
		this.run = false;
	}

	/**
	 * Sets the sorter to "run" mode. Called on the event dispatch thread.
	 */
	public void setRun() {
		run = true;
		gate.release();
	}

	/**
	 * Sets the sorter to "step" mode. Called on the event dispatch thread.
	 */
	public void setStep() {
		run = false;
		gate.release();
	}

	public void run() {
		Comparator<Double> comp = new Comparator<Double>() {
			public int compare(Double i1, Double i2) {
				component.setValues(values, i1, i2);
				try {
					if (run)
						Thread.sleep(DELAY);
					else
						gate.acquire();
				} catch (InterruptedException exception) {
					Thread.currentThread().interrupt();
				}
				return i1.compareTo(i2);
			}
		};
		Arrays.sort(values, comp);
		component.setValues(values, null, null);
	}

}

/**
 * This component draws an array and marks two elements in the array.
 */
class ArrayComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 50845022073269600L;
	private Double marked1;
	private Double marked2;
	private Double[] values;

	/**
	 * Sets the values to be painted. Called on the sorter thread.
	 * 
	 * @param values
	 *            the array of values to display
	 * @param marked1
	 *            the first marked element
	 * @param marked2
	 *            the second marked element
	 */
	public synchronized void setValues(Double[] values, Double marked1,
			Double marked2) {
		this.values = values.clone();
		this.marked1 = marked1;
		this.marked2 = marked2;
		repaint();
	}

	/**
	 * Called on the event dispatch thread
	 */
	public synchronized void paintComponent(Graphics g) {
		if (values == null)
			return;
		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth() / values.length;
		for (int i = 0; i < values.length; i++) {
			double height = values[i] * getHeight();
			Rectangle2D bar = new Rectangle2D.Double(width * i, 0, width,
					height);
			if (values[i] == marked1 || values[i] == marked2)
				g2.fill(bar);
			else
				g2.draw(bar);
		}
	}

}