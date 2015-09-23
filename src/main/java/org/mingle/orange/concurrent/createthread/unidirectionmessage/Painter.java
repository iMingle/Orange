/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.unidirectionmessage;

import java.awt.Color;
import java.awt.Dimension;

/**
 * 线性阶段
 * 
 * @since 1.8
 * @author Mingle
 */
public class Painter extends SingleOutputPushStage implements PushStage {
	protected final Color color; // the color to paint things

	public Painter(Color c) {
		color = c;
	}

	public void putA(Box p) {
		p.setColor(color);
		next1().putA(p);
	}
}

class Wrapper extends SingleOutputPushStage implements PushStage {
	protected final int thickness;

	public Wrapper(int t) {
		thickness = t;
	}

	public void putA(Box p) {
		Dimension d = new Dimension(thickness, thickness);
		next1().putA(new WrappedBox(p, d));
	}
}

class Flipper extends SingleOutputPushStage implements PushStage {
	public void putA(Box p) {
		if (p instanceof JoinedPair)
			((JoinedPair) p).flip();
		next1().putA(p);
	}
}