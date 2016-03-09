/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 迭代器测试
 * 
 * @since 1.0
 * @author Mingle
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
