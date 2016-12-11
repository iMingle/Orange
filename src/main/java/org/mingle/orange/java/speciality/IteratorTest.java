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

package org.mingle.orange.java.speciality;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 迭代器测试
 * 
 * @author mingle
 */
public class IteratorTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Mingle");
        names.add("Jack");
        names.add("Lucy");
        
        Iterator<String> it = names.iterator();
        ListIterator<String> listIt = names.listIterator();
        
        System.out.println("ListIterator正序");
        while (listIt.hasNext()) {
            System.out.println(listIt.next() + ", " + listIt.previousIndex() + ", " + listIt.nextIndex());
        }
        
        System.out.println("ListIterator倒序");
        while (listIt.hasPrevious()) {
            System.out.println(listIt.previous());
        }
        
        System.out.println("Iterator正序");
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }
        System.out.println(names);
    }

}
