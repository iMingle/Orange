/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.observer;

/**
 * 数字时钟
 * 
 * @since 1.0
 * @author Mingle
 */
public class DigitalClock implements Observer, Widget {
    private final ClockTimer subject;

    public DigitalClock(ClockTimer subject) {
        super();
        this.subject = subject;
        subject.attach(this);
    }

    @Override public void update(Subject subject) {
        if (subject == this.subject)
            draw();
    }

    @Override public void draw() {
        int hour = subject.hour();
        int minute = subject.minute();
        int second = subject.second();
        
        drawClock(hour, minute, second);
    }

    private void drawClock(int hour, int minute, int second) {
        
    }

}