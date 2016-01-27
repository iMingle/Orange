/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * 向下转型测试
 * @since 1.8
 * @author Mingle
 */
public class DownCastTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Son s = (Son) new Father(48);            // cannot be cast
        s.getAge();
    }

}

