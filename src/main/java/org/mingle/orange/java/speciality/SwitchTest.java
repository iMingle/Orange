/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.Random;

/**
 *
 * @since 1.0
 * @author Mingle
 */
public class SwitchTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i = 0; i < 100; i++) {
            int c = rand.nextInt(26) + 'a';
            System.out.print((char)c + ", " + c + ": ");
            switch(c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u': 
                System.out.println("you");
                break;
            case 'y':
            case 'w':
                System.out.println("wu");
                break;
            default:
                System.out.println("default");
            }
        }
        
        String str = "I and you!";
        switch(str) {
        case "I and you!":
            System.out.println(str);
            break;
        default:
            System.out.println("default");
        }
    }
}
