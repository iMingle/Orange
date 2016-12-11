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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author mingle
 */
public class LinkedHashMapTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> linked = new LinkedHashMap<String, String>(128, 0.75F, true);
        linked.put("4", "four");
        linked.put("5", "five");
        linked.put("6", "six");
        linked.put("1", "one");
        linked.put("2", "two");
        linked.put("3", "three");

        Iterator<String> ita = linked.keySet().iterator();
        while (ita.hasNext()) {
            System.out.print(ita.next() + ",");
        }
        System.out.println();
        
        Map<String, String> linkedMap = new LinkedHashMap<String, String>(128, 0.75F, true);
        linkedMap.put("4", "four");
        linkedMap.put("5", "five");
        linkedMap.put("6", "six");
        linkedMap.put("1", "one");
        linkedMap.put("2", "two");
        linkedMap.put("3", "three");
        linkedMap.get("4");
        Iterator<String> it = linkedMap.keySet().iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ",");
        }
        System.out.println();
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");
        map.put("5", "five");
        map.put("6", "six");
        Iterator<String> its = map.keySet().iterator();
        while (its.hasNext()) {
            System.out.print(its.next() + ",");
        }
    }
}
