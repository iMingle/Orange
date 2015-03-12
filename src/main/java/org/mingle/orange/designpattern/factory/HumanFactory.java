package org.mingle.orange.designpattern.factory;

public class HumanFactory {

	@SuppressWarnings("rawtypes")
	public static Human createHuman(Class c) {
		Human human = null;
		
		try {
			human = (Human) Class.forName(c.getName()).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return human;
	}
}
