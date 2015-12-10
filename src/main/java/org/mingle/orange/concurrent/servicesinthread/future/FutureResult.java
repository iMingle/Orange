/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.servicesinthread.future;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Callable接口的实现原理
 * 
 * @since 1.8
 * @author Mingle
 */
public class FutureResult {
	protected Object value = null;
	protected boolean ready = false;
	protected InvocationTargetException exception = null;

	public synchronized Object get() throws InterruptedException,
			InvocationTargetException {
		while (!ready)
			wait();
		if (exception != null)
			throw exception;
		else
			return value;
	}

	public Runnable setter(final Callable<Object> function) {
		return new Runnable() {
			public void run() {
				try {
					set(function.call());
				} catch (Throwable e) {
					setException(e);
				}
			}
		};
	}

	synchronized void set(Object result) {
		value = result;
		ready = true;
		notifyAll();
	}

	synchronized void setException(Throwable e) {
		exception = new InvocationTargetException(e);
		ready = true;
		notifyAll();
	}

	// ... other auxiliary and convenience methods ...
}

class PictureDisplayWithFutureResult {
	void displayBorders() {
	}

	void displayCaption() {
	}

	void displayImage(byte[] b) {
	}

	void cleanup() {
	}

	private final Renderer renderer = new StandardRenderer();

	public void show(final URL imageSource) {
		try {
			FutureResult futurePic = new FutureResult();
			Runnable command = futurePic.setter(new Callable<Object>() {
				
				@Override
				public Object call() {
					return renderer.render(imageSource);
				}
			});
			new Thread(command).start();

			displayBorders();
			displayCaption();

			displayImage(((Pic) (futurePic.get())).getImage());
		} catch (InterruptedException ex) {
			cleanup();
			return;
		} catch (InvocationTargetException ex) {
			cleanup();
			return;
		}
	}
}