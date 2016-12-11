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

package org.mingle.orange.java.speciality;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Map测试类
 *
 * @author mingle
 */
public class MapTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int r = rand.nextInt(10);
            Integer freq = map.get(r);
            map.put(r, freq == null ? 1 : freq + 1);
        }
        System.out.println(map);
        
        Set<Map.Entry<Integer, Integer>> entrys = map.entrySet();
        System.out.println(entrys);
        Set<Integer> keys = map.keySet();
        System.out.println(keys);
    }

}
