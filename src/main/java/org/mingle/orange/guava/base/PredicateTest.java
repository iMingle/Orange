/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.guava.base;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * most common use: filtering collections (view)
 * 
 * @since 1.0
 * @author Mingle
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
