/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.servicesinthread.future;

import java.net.URL;

/**
 * Future接口实现原理
 * 
 * @since 1.8
 * @author Mingle
 */
public class PicturAppWithFuture {
	private final Renderer renderer = new AsynchRenderer();

	void displayBorders() {
	}

	void displayCaption() {
	}

	void displayImage(byte[] b) {
	}

	void cleanup() {
	}

	public void show(final URL imageSource) {
		Pic pic = renderer.render(imageSource);

		displayBorders(); // do other things ...
		displayCaption();

		byte[] im = pic.getImage();
		if (im != null)
			displayImage(im);
		else {} // deal with assumed rendering failure
	}
}

class AsynchRenderer implements Renderer {
	private final Renderer renderer = new StandardRenderer();

	static class FuturePic implements Pic { // inner class
		private Pic pic = null;
		private boolean ready = false;

		synchronized void setPic(Pic p) {
			pic = p;
			ready = true;
			notifyAll();
		}

		public synchronized byte[] getImage() {
			while (!ready)
				try {
					wait();
				} catch (InterruptedException e) {
					return null;
				}
			return pic.getImage();
		}
	}

	public Pic render(final URL src) {
		final FuturePic p = new FuturePic();
		new Thread(new Runnable() {
			public void run() {
				p.setPic(renderer.render(src));
			}
		}).start();
		return p;
	}
}

interface Pic {
	byte[] getImage();
}

interface Renderer {
	Pic render(URL src);
}

class StandardRenderer implements Renderer {
	public Pic render(URL src) {
		return null;
	}
}
