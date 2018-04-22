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

package org.mingle.orange.args;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

/**
 * @author mingle
 */
public class Test {
    private static final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
    private static final int NUM = 4;

    private int stackLength = 1;

    public static void main(String[] args) {
        Test test = new Test();
        String str1 = new StringBuilder().append("hello").toString();
        System.out.println(str1.intern() == str1);
        System.out.println(str1.intern() == str1.intern());
        System.out.println(str1 == str1);

        Map<Integer, String> map = new HashMap<>((1 << 31) - 1);
    }
}
