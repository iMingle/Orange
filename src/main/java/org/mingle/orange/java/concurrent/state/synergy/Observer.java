/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.java.concurrent.state.synergy;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 协同处理-分离观察者
 * 
 * @author Mingle
 */
public class Observer {
    protected double cachedState; // last known state
    protected final Subject subj; // only one allowed here

    Observer(Subject s) {
        subj = s;
        cachedState = s.getValue();
        display();
    }

    synchronized void changed(Subject s) {
        if (s != subj)
            return; // only one subject

        double oldState = cachedState;
        cachedState = subj.getValue(); // probe
        if (oldState != cachedState)
            display();
    }

    protected void display() {
        // somehow display subject state; for example just:
        System.out.println(cachedState);
    }
}

class Subject {
    protected double val = 0.0; // modeled state
    protected final CopyOnWriteArrayList<Observer> observers = new CopyOnWriteArrayList<>();

    public synchronized double getValue() {
        return val;
    }

    protected synchronized void setValue(double d) {
        val = d;
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    /**
     * 使用CopyOnWriteArrayList避免锁定观察者集合
     * 
     * @param newstate
     */
    public void changeValue(double newstate) {
        setValue(newstate);
        for (Iterator<Observer> it = observers.iterator(); it.hasNext();)
            it.next().changed(this);
    }

}
