package org.mingle.orange.designpattern.facade;

public class Post {
	private LetterProcess letterProcess;
	
	public Post() {
		letterProcess = new LetterProcessImpl();
	}

	public void send(String context, String address) {
		letterProcess.writeContext(context);
		letterProcess.writeEnvelope(address);
		letterProcess.letterIntoEnvelope();
		letterProcess.sendLetter();
	}
}
