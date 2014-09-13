package org.mingle.orange.designpattern.decorator;

public class DecoratorOne extends Decorator {

	public DecoratorOne(SchoolReport sr) {
		super(sr);
	}
	
	public void decoratorOne() {
		System.out.println("I'm a decorator");
	}

	@Override
	public void report() {
		super.report();
		this.decoratorOne();
	}

}
