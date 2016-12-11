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

package org.mingle.orange.arithmetic.base;

/**
 * Circular Rotation(回环变位)-字符串s中的字符循环移动任意位置之后得到另一个字符串t,
 * 那么s就被称为t的回环变位
 * 
 * @author mingle
 */
public class CircularRotation {
    
    public static boolean isRotation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        } else {
            for (int i = 0; i < str1.length(); i++) {
                int index = str1.indexOf(str2.charAt(i));
                System.out.println("index = " + index);
                if (-1 == index) {
                    return false;
                } else {
                    str1 = str1.substring(0, index) + str1.substring(index + 1, str1.length());
                    str2 = str2.substring(1, str2.length());
                    System.out.println("str1 = " + str1);
                    System.out.println("str2 = " + str2);
                    i--;
                    if (str1.equals(str2)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        String s = "ACTGACG";
        String t = "TGACGAC";

        if (CircularRotation.isRotation(s, t)) {
            System.out.println("is a circular rotation");
        } else {
            System.out.println("not is a circular rotation");
        }
    }
}
