package org.mingle.orange.designpattern.facade;

public class LetterProcessImpl implements LetterProcess {

	public void writeContext(String context) {
		System.out.println("letter's context is " + context);
	}

	public void writeEnvelope(String address) {
		System.out.println("The address is " + address);
	}

	public void letterIntoEnvelope() {
		System.out.println("put the letter into the envelope");
	}

	public void sendLetter() {
		System.out.println("sent the letter");
	}

}
