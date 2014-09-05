package org.mingle.orange.designpattern.decorator;

public class Mingle {

	public static void main(String[] args) {
		SchoolReport sr = new FourthGradeSchoolReport();
		
		sr = new DecoratorOne(sr);
		
		sr.report();
	}

}
