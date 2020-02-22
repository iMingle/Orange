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

package org.orange.java.generic;

/**
 *
 * @author mingle
 */
public class PairTest1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg1.minmax(words);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
        
        System.out.println("middle1 = " + ArrayAlg1.<String>getMiddle(words));
        System.out.println("middle2 = " + ArrayAlg1.getMiddle(words));
    }
}

class ArrayAlg1 {

    /**
     * get the minimum and maximum of an array of strings.
     * @param words an array of strings
     * @return a pair with the min and max value, or null if words is null or empty
     */
    public static Pair<String> minmax(String[] words) {
        if (null == words || 0 == words.length) return null;
        String min = words[0];
        String max = words[0];
        
        for (int i = 0; i < words.length; i++) {
            if (min.compareTo(words[i]) > 0) min = words[i];
            if (max.compareTo(words[i]) < 0) max = words[i];
        }
        
        return new Pair<String>(min, max);
    }
    
    public static <T> T getMiddle(T[] t) {
        return t[t.length / 2];
    }
}
