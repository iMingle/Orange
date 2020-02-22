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

package org.orange.effectivejava;

import java.util.Date;

/**
 * 保护性拷贝
 * 
 * @author mingle
 */
public class DefensiveCopy {

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);
        period.end().setTime(123);
        System.out.println(period.start());
        System.out.println(period.end());
    }

}

/**
 * 不可变的时间周期
 */
final class Period {
    private final Date start;
    private final Date end;
    
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        
        if (start.compareTo(end) > 0)
            throw new IllegalArgumentException(start + " after " + end);
    }
    
    public Date start() {
        return new Date(start.getTime());
    }
    
    public Date end() {
        return new Date(end.getTime());
    }

}
