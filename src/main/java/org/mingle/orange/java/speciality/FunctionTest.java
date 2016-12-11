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

/**
 * 方法的测试
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @author Mingle
 */
public class FunctionTest {
    /**
     * 传递的是引用，对引用的修改相当于对对象的修改
     * @param l
     */
    public static void changeValue(Letter l) {
        l.avg = 8.0f;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Letter l = new Letter();
        l.avg = 5.0f;
        FunctionTest.changeValue(l);
        System.out.println(l.avg);                // 8.0
    }

}

class Letter {
    float avg;
}
