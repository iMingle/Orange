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

package org.mingle.orange.java.collection;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 *
 * @author Mingle
 */
public class EnumSetMapTest {

    public static void main(String[] args) {
        EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
        System.out.println("always = " + always);
        EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
        System.out.println("never = " + never);
        EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
        System.out.println("workday = " + workday);
        EnumSet<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        System.out.println("mwf = " + mwf);
        
        EnumMap<Weekday, Integer> map = new EnumMap<Weekday, Integer>(Weekday.class);
        map.put(Weekday.MONDAY, 1);
        map.put(Weekday.TUESDAY, 2);
        map.put(Weekday.WEDNESDAY, 3);
        map.put(Weekday.THURSDAY, 4);
        map.put(Weekday.FRIDAY, 5);
        map.put(Weekday.SATURDAY, 6);
        map.put(Weekday.SUNDAY, 7);
        System.out.println("EnumMap = " + map);
    }
}

enum Weekday {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
