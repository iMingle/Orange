/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.unidirectionmessage;

/**
 * 适配器
 * 
 * @since 1.8
 * @author Mingle
 */
public class DualInputAdapter implements PushStage {
	protected final DualInputPushStage stage;

	public DualInputAdapter(DualInputPushStage s) {
		stage = s;
	}

	@Override
	public void putA(Box p) {
		stage.putB(p);
	}

}
