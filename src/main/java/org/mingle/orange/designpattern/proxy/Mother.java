package org.mingle.orange.designpattern.proxy;

public class Mother implements KindWomen {
	private KindWomen kindWomen;
	
	public Mother() {
		kindWomen = new Prostitute();
	}

	public void makeEyesWithMan() {
		this.kindWomen.makeEyesWithMan();
	}

	public void happyWithMan() {
		this.kindWomen.happyWithMan();
	}
}
