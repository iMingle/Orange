/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.memento;

/**
 * 原发器
 * 
 * @since 1.0
 * @author Mingle
 */
public class Originator {
    private State state;
    
    public Memento createMemento() {
        return new Memento(state);
    }
    
    public void setMemento(Memento memento) {
        state = memento.getState();
    }
}
