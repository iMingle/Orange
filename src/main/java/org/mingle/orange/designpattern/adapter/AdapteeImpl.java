package org.mingle.orange.designpattern.adapter;

public class AdapteeImpl implements Adaptee {
    private Person person;
    
    public AdapteeImpl(Person person) {
        this.person = person;
    }

    public Person getPersonInfo() {
        return person;
    }

}
