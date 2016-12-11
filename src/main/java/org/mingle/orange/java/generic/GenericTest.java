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

package org.mingle.orange.java.generic;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;


/**
 *
 * @author mingle
 */
public class GenericTest<T> {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        
        System.out.println(GenericTest.min(words));
        
        GregorianCalendar[] birthdays = {
                new GregorianCalendar(1800, Calendar.DECEMBER, 4),
                new GregorianCalendar(1930, Calendar.DECEMBER, 8),
                new GregorianCalendar(1945, Calendar.DECEMBER, 16),
                new GregorianCalendar(1988, Calendar.JANUARY, 26)
        };
        
        System.out.println(GenericTest.min(birthdays));
        
        Pair<String> pair = new Pair<String>("first", "second");
        
        if (pair instanceof Pair) {
            System.out.println("pair is Pair");
        }
        // the same to up
        if (pair instanceof Pair<?>) {
            System.out.println("pair is Pair<?>");
        }
        
        System.out.println(pair.getClass());    // class org.mingle.orange.java.generic.Pair
        
        Map<Class<?>, Object> favorites = Maps.newHashMap();
        favorites.put(Integer.class, "Integer1");
        favorites.put(Integer.class, "Integer2");
        System.out.println(favorites);
    }
    
    public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T t = i.next();
            if (t.compareTo(result) > 0)
                result = t;
        }
        return result;
    }

    public static <T extends Comparable<? super T>> T min(T[] words) {
        if (null == words || 0 == words.length) return null;
        T min = words[0];
        
        for (int i = 0; i < words.length; i++) {
            if (min.compareTo(words[i]) > 0) min = words[i];
        }
        
        return min;
    }
    
    public T max(T[] numbers) {
        return numbers[0];
    }
    
    public <K extends Comparable<?>> void mm(Class<K> klass) {
        return;
    }
}
