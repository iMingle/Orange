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

package org.mingle.orange.effectivejava;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 避免创建不必要的对象
 * 
 * @since 1.0
 * @author Mingle
 */
public class AvoidCreateNeedlessObject {
    @SuppressWarnings("unused")
    private String s = new String("stringette");    // DON'T DO THIS
    
    public static void main(String[] args) {
        Long sum = 0L;
        /**
         * 无意识的自动装箱
         */
        for (long i = 0; i < Integer.MAX_VALUE; i++)
            sum += i;
    }
}

class Person {
    private final Date birthDate;
    
    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 是否在生育高峰期出生的小孩
     * 
     * @return
     */
    public boolean isBabyBoomer() {
        // 没有必要分配一个expensive object
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
    }
}

class PersonBetter {
    private final Date birthDate;
    
    private static final Date BOOM_START;
    private static final Date BOOM_END;
    
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }
    
    public PersonBetter(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 是否在生育高峰期出生的小孩
     * 
     * @return
     */
    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }
}