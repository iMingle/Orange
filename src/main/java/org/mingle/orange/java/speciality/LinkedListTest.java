/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.LinkedList;

/**
 * LinkedList测试
 *
 * @since 1.0
 * @author Mingle
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> ints = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            ints.add(i + 1);
        }
        System.out.println(ints);                // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        System.out.println(ints.getFirst());    // 1
        System.out.println(ints.getLast());        // 10
        System.out.println(ints.element());        // 1
        System.out.println(ints.peek());        // 1 empty return null
        System.out.println(ints.remove());        // 1
        System.out.println(ints);                // [2, 3, 4, 5, 6, 7, 8, 9, 10]
        System.out.println(ints.removeFirst());    // 2
        System.out.println(ints.poll());        // 3 empty return null
        System.out.println(ints);                // [4, 5, 6, 7, 8, 9, 10]
        ints.addFirst(0);
        System.out.println(ints.offer(1));        // true
        System.out.println(ints);                // [0, 4, 5, 6, 7, 8, 9, 10, 1]
        System.out.println(ints.add(2));        // true
        ints.addLast(3);
        System.out.println(ints);                // [0, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3]
        System.out.println(ints.removeLast());    // 3
        System.out.println(ints);                // [0, 4, 5, 6, 7, 8, 9, 10, 1, 2]
    }
}
