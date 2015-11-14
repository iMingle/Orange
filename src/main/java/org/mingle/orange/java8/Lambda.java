/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda表达式
 * 函数式接口的重要属性是:我们能够使用 Lambda 实例化它们,Lambda 表达式让你能够将函数作为方法参数,
 * 或者将代码作为数据对待.Lambda 表达式的引入给开发者带来了不少优点:在 Java 8之前,匿名内部类,
 * 监听器和事件处理器的使用都显得很冗长,代码可读性很差,Lambda表达式的应用则使代码变得更加紧凑,
 * 可读性增强;Lambda表达式使并行操作大集合变得很方便,可以充分发挥多核 CPU 的优势,更易于为多核处理器编写代码;
 * Lambda表达式由三个部分组成:
 * 第一部分为一个括号内用逗号分隔的形式参数,参数是函数式接口里面方法的参数;
 * 第二部分为一个箭头符号:->;
 * 第三部分为方法体,可以是表达式和代码块.语法如下:
 * 1. 方法体为表达式,该表达式的值作为返回值返回.
 * (parameters) -> expression
 * 2. 方法体为代码块,必须用 {} 来包裹起来,且需要一个 return 返回值,但若函数式接口里面方法返回值是 void,
 * 则无需返回值.
 * (parameters) -> { statements; }
 * 
 * Lambda表达式中无法访问到函数式接口的默认方法
 * 
 * @since 1.8
 * @author Mingle
 */
public class Lambda {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});

		// Lambda表达式
		Collections.sort(names, (String a, String b) -> b.compareTo(a));
		Collections.sort(names, (a, b) -> b.compareTo(a));
	}
}
