package org.mingle.orange.designpattern.strategy;

public class Mingle {

	public static void main(String[] args) {
		Context context;
		
		context = new Context(new StrategyImplOne());
		context.operate();
		
		context = new Context(new StrategyImplTwo());
		context.operate();
	}
}
