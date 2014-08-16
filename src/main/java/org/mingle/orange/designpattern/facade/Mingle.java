package org.mingle.orange.designpattern.facade;

public class Mingle {

	public static void main(String[] args) {
		Post post = new Post();
		
		String context = "Hello, Mother, I'm missing you";
		String address = "Happy Road No.888, Henan Province";
		
		post.send(context, address);

	}

}
