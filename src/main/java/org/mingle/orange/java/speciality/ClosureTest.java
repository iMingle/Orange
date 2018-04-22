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

/**
 * 闭包测试
 *
 * @author mingle
 */
public class ClosureTest {
    interface AnonInner {
        int add();
    }

    private AnonInner getAnonInner(int x) {
        int y = 5;
        return () -> x + y;
    }

    public static void main(String[] args) {
        ClosureTest closure = new ClosureTest();
        AnonInner anonInner = closure.getAnonInner(3);
        System.out.println(anonInner.add());
        System.out.println(anonInner.add());
    }
}
