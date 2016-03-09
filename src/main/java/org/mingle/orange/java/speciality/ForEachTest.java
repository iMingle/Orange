/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * foreach测试
 * 
 * @since 1.0
 * @author Mingle
 */
public class ForEachTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[] { 6, 8 };
        
        for (int i : array) {
            System.out.println(i);
        }
    }

}
