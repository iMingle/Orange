package org.mingle.orange.test;

public class Salesman extends Employee {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4677675899569711439L;
	private String mobile = "15524566288";

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "Son " + super.getInfo();
	}
	
	public int sum(int ... numbers) {
		int total = 0;
		
		for (int number : numbers) {
			total += number;
		}
		
		return total;
	}
	
	public static void main(String[] args) {
		Salesman e = new Salesman();
		
		System.out.println(e.mobile);
		System.out.println(((Employee)e).mobile);
		
		System.out.println(e.getInfo());
		
		System.out.println(e.sum(1, 5));
	}
}
