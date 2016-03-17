/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class Subject {
    private List<Observer> observers;
    
    public void attach(Observer observer) {
        if (observer == null)
            observers = new ArrayList<>();
        observers.add(observer);
    }
    
    public void detach(Observer observer) {
        if (observer == null)
            return;
        observers.remove(observer);
    }
    
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(this);
    }
}
