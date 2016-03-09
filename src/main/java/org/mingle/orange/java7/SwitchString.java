/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

/**
 * 编译器在编译时先做处理：
 * ①case只有一种情况，直接转成if；
 * ②如果只有一个case和default，则直接转换为if...else...；
 * ③有多个case，先将String转换为hashCode，然后对应的进行处理，JavaCode在底层兼容Java7以前版本。
 * 
 * @since 1.0
 * @author Mingle
 */
public class SwitchString {
    public static void switchString(String str) {
        switch (str) {
        case "a":
            break;
        default:
            break;
        }
    }
}
