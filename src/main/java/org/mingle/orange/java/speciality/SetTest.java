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

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Set测试类
 *
 * @since 1.0
 * @author Mingle
 */
public class SetTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
//        Set<Integer> ints = new HashSet<>();        // Hash函数Set
//        Set<Integer> ints = new TreeSet<>();        // 排序的Set
        Set<Integer> ints = new LinkedHashSet<>();    // 插入顺序Set
        for (int i = 0; i < 1000; i++) {
            ints.add(rand.nextInt(10));
        }
        
        System.out.println(ints);
    }

}
