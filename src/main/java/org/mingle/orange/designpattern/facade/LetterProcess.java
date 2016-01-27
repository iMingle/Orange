package org.mingle.orange.designpattern.facade;

public interface LetterProcess {
    
    public void writeContext(String context);
    public void writeEnvelope(String address);
    public void letterIntoEnvelope();
    public void sendLetter();

}
