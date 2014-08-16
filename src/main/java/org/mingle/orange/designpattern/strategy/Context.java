package org.mingle.orange.designpattern.strategy;

/**
 * ���Ե�Ӧ�ó���
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
