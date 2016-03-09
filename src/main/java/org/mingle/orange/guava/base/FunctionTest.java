/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.guava.base;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * most common use: transforming collections (view)
 * 
 * @since 1.0
 * @author Mingle
 */
public class FunctionTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {

            @Override
            public Integer apply(String input) {
                return input.length();
            }
        };
        
        System.out.println(lengthFunction.apply("JinMinglei"));
        
        List<String> names = Lists.newArrayList();
        names.add("JinMinglei");
        names.add("LiuDehua");
        names.add("LiMing");
        names.add("ZhangXueyou");
        names.add("GuoFucheng");
        
        Collection<Integer> lengths = Collections2.transform(names, lengthFunction);
        
        System.out.println(lengths);
    }
}
