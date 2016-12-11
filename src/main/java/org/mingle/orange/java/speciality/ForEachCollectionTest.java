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
import java.util.Collection;

/**
 * foreach List测试
 * 
 * @author mingle
 */
public class ForEachCollectionTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Collection<Integer> array = new ArrayList<Integer>();
        array.add(6);
        array.add(8);
        
        for (Integer i : array) {
            System.out.println(i);
        }
    }

}
