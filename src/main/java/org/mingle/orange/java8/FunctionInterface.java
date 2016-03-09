/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java8;

/**
 * 函数式接口
 * 
 * @since 1.0
 * @author Mingle
 */
public class FunctionInterface {
    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted); // 123
    }
}

/**
 * "函数式接口"是指仅仅只包含一个抽象方法的接口
 */
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
