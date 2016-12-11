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

package org.mingle.orange.guava.base;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * most common use: transforming collections (view)
 * 
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
