package org.mingle.orange.designpattern.strategy;

/**
 * 策略的应用场景
 * @author mingle
 *
 */
public class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		super();
		this.strategy = strategy;
	}
	
	public void operate() {
		this.strategy.operate();
	}
}
