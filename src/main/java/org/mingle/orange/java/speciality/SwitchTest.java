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

import java.util.Random;

/**
 *
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
