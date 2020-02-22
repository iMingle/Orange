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

package org.orange.arithmetic.base;

/**
 * 括号匹配
 * 
 * @author mingle
 */
public class ParenMatch {
    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';
    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';

    public static boolean parentheses(String s) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == LEFT_PAREN)
                stack.push(LEFT_PAREN);
            if (s.charAt(i) == LEFT_BRACE)
                stack.push(LEFT_BRACE);
            if (s.charAt(i) == LEFT_BRACKET)
                stack.push(LEFT_BRACKET);

            if (s.charAt(i) == RIGHT_PAREN) {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != LEFT_PAREN)
                    return false;
            }

            else if (s.charAt(i) == RIGHT_BRACE) {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != LEFT_BRACE)
                    return false;
            }

            else if (s.charAt(i) == RIGHT_BRACKET) {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != LEFT_BRACKET)
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(ParenMatch.parentheses("{[()][]()}"));
    }
}
