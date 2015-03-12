package org.mingle.orange.designpattern.factory;

public class Mingle {

	public static void main(String[] args) {
		Human human = HumanFactory.createHuman(YellowHuman.class);
		
		human.laugh();
		
		human = HumanFactory.createHuman(BlackHuman.class);
		human.say();
		
		human = HumanFactory.createHuman(WhiteHuman.class);
		human.laugh();
	}

}
