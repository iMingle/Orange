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

package org.orange.util.guava.base;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * most common use: filtering collections (view)
 * 
 * @author mingle
 */
public class PredicateTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // filter even number
        Predicate<Integer> evenFilter = new Predicate<Integer>() {
            
            @Override
            public boolean apply(Integer input) {
                return 0 != input % 2;
            }
        };
        
        List<Integer> numbers = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            numbers.add(i);
        }
        
        Collection<Integer> odd = Collections2.filter(numbers, evenFilter);
        
        System.out.println(odd);
    }
}
