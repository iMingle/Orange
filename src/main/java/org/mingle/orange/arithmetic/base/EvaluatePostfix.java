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

package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 后序求值
 * 
 * @author mingle
 */
public class EvaluatePostfix {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+"))
                stack.push(stack.pop() + stack.pop());
            else if (s.equals("*"))
                stack.push(stack.pop() * stack.pop());
            else
                stack.push(Integer.parseInt(s));
        }
        StdOut.println(stack.pop());
    }

}
