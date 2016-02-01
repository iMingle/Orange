/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.base;

/**
 * 用stack实现计算公式
 * 
 * @since 1.8
 * @author Mingle
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
