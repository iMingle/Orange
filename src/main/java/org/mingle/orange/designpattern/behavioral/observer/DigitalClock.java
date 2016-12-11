/*
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
 * limitations under the License.
 */

package org.mingle.orange.designpattern.behavioral.observer;

/**
 * 数字时钟
 * 
 * @author mingle
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
