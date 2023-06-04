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

package org.orange.abc;

/**
 * @author mingle
 */
public class AbcOfString {
    public static void main(String[] args) {
        String s2 = new String("a") + "b"; //s2 指向 堆 里面ab 的地址,并且常量池不存在 ab
//        String s3 = s2.intern();// 由于常量池并没有ab 因此会把 s2 的堆地址引用 放到常量池
        String s3 = "ab";// 由于常量池并没有ab 因此会把 s2 的堆地址引用 放到常量池
        System.out.println(s2 == s3);// 同时指向 ab 堆里面的地址 固为true

        String a = "abcd";
        String b = "abcd";
        System.out.println(a == b);  // True

        String a1 = "abcd";
        String b1 = "ab" + new String("cd");
        System.out.println(a1 == b1);  // False

        String c = new String("abcd");
        String d = new String("abcd");
        System.out.println(a == d);  // False
    }
}
