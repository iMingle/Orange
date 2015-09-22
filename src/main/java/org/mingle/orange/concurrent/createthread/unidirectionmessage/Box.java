/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.unidirectionmessage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public abstract class Box {
	protected Color color = Color.white;

	public synchronized Color getColor() {
		return color;
	}

	public synchronized void setColor(Color c) {
		color = c;
	}

	public abstract java.awt.Dimension size();

	public abstract Box duplicate(); // clone

	public abstract void show(Graphics g, Point origin);// display
}
