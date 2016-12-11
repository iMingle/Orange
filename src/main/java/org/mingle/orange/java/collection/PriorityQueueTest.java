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

package org.mingle.orange.java.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

/**
 * tree structure
 * 
 * @author mingle
 */
public class PriorityQueueTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        PriorityQueue<GregorianCalendar> queue = new PriorityQueue<GregorianCalendar>();
        queue.add(new GregorianCalendar(1988, Calendar.JANUARY, 26));
        queue.add(new GregorianCalendar(1815, Calendar.DECEMBER, 12));
        queue.add(new GregorianCalendar(1903, Calendar.MAY, 3));
        queue.add(new GregorianCalendar(1910, Calendar.JUNE, 22));
        
        System.out.println("Iterating over elements...");
        for (GregorianCalendar date : queue) {
            System.out.println(date.get(Calendar.YEAR));
        }
        System.out.println("Removing elements...");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove().get(Calendar.YEAR));
        }
    }
}
