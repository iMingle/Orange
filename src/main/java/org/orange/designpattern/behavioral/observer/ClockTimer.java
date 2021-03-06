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

package org.orange.designpattern.behavioral.observer;

/**
 * 用于存储和维护一天时间的具体目标
 *
 * @author mingle
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
        notifyObservers();
    }
}
