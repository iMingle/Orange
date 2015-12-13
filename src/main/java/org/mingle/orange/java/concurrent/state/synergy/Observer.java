/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.synergy;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 协同处理-分离观察者
 * 
 * @since 1.8
 * @author Mingle
 */
public class Observer {
	protected double cachedState; // last known state
	protected final Subject subj; // only one allowed here

	Observer(Subject s) {
		subj = s;
		cachedState = s.getValue();
		display();
	}

	synchronized void changed(Subject s) {
		if (s != subj)
			return; // only one subject

		double oldState = cachedState;
		cachedState = subj.getValue(); // probe
		if (oldState != cachedState)
			display();
	}

	protected void display() {
		// somehow display subject state; for example just:
		System.out.println(cachedState);
	}
}

class Subject {
	protected double val = 0.0; // modeled state
	protected final CopyOnWriteArrayList<Observer> observers = new CopyOnWriteArrayList<>();

	public synchronized double getValue() {
		return val;
	}

	protected synchronized void setValue(double d) {
		val = d;
	}

	public void attach(Observer o) {
		observers.add(o);
	}

	public void detach(Observer o) {
		observers.remove(o);
	}

	/**
	 * 使用CopyOnWriteArrayList避免锁定观察者集合
	 * 
	 * @param newstate
	 */
	public void changeValue(double newstate) {
		setValue(newstate);
		for (Iterator<Observer> it = observers.iterator(); it.hasNext();)
			it.next().changed(this);
	}

}
