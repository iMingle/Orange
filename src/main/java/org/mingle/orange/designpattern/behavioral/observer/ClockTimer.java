/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.observer;

/**
 * 用于存储和维护一天时间的具体目标
 * 
 * @since 1.0
 * @author Mingle
 */
public class ClockTimer extends Subject {
    public int hour() {
        return 23;
    }
    
    public int minute() {
        return 59;
    }
    
    public int second() {
        return 59;
    }
    
    /**
     * 由一个内部计时器以固定的时间间隔调用,从而提供一个精确的时间基准
     */
    public void tick() {
        // update internal time-keeping state
        // ...
        notifyObservers();
    }
}
