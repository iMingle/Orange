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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * List测试
 *
 * @since 1.0
 * @author Mingle
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ints.add(i + 1);
        }
        System.out.println(ints);                // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        Integer i = Integer.valueOf(1);
        System.out.println(ints.contains(i));    // true
        ints.remove(i);
        System.out.println(ints);                // [2, 3, 4, 5, 6, 7, 8, 9, 10]
        System.out.println(ints.get(2));        // 4
        System.out.println(ints.indexOf(5));    // 3
        List<Integer> sub = ints.subList(1, 4);
        System.out.println(sub);                // [3, 4, 5]
        System.out.println(ints.containsAll(sub));    // true
        Collections.shuffle(sub);
        System.out.println(sub);                // [5, 4, 3]
        Collections.sort(sub);
        System.out.println(sub);                // [3, 4, 5]
        List<Integer> copy = new ArrayList<>(ints);
        sub = Arrays.asList(ints.get(2), ints.get(5), ints.get(6));
        System.out.println(sub);                // [4, 7, 8]
        System.out.println(copy);                // [2, 3, 4, 5, 6, 7, 8, 9, 10]
        System.out.println(copy.retainAll(sub));// true
        System.out.println(copy);                // [4, 7, 8]
        copy.removeAll(sub);
        System.out.println(copy);                // []
        System.out.println(copy.isEmpty());        // true
        copy.addAll(0, sub);
        System.out.println(copy);                // [4, 7, 8]
        
        Object[] o = ints.toArray();
        System.out.println(o[6]);                // 8
        Integer[] re = ints.toArray(new Integer[0]);
        System.out.println(re[6]);                // 8
    }
}