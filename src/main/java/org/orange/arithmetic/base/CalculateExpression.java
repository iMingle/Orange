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
 * 用stack实现计算公式
 * 
 * @author mingle
 */
public class CalculateExpression {
    public static double getVal(String str) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i, i + 1);
            if (temp.equals("(")) {
            } else if (temp.equals("+")) {
                ops.push(temp);
            } else if (temp.equals("-")) {
                ops.push(temp);
            } else if (temp.equals("*")) {
                ops.push(temp);
            } else if (temp.equals("/")) {
                ops.push(temp);
            } else if (temp.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();

                if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("-")) {
                    v = vals.pop() - v;
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v;
                }
                vals.push(v);
            } else if (temp.equals(" ")) {

            } else {
                vals.push(Double.parseDouble(temp));
            }
        }

        return vals.pop();
    }

    public static void main(String[] args) {
        System.out.println(CalculateExpression.getVal("(1 + ((2 + 3) + (4 * 5)))"));
    }

}
