/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.i18n;

import java.text.MessageFormat;
import java.util.GregorianCalendar;

/**
 * 格式化带变量的文本
 * 
 * @since 1.0
 * @author Mingle
 */
public class MessageFormatTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // {2, date, long} 占位符，类型，风格
        // 类型:number, time, date, choice
        // number:integer, currency, percent, $, ##0
        // time&date:short, medium, long, full, yyyy-MM-dd
        Object[] format = {"hurricane", 3, new GregorianCalendar(2000, 8, 8).getTime(), 10.0E8};
        String msg = MessageFormat.format(
        "on {2, date, long}, a {0} destroyed {1, choice, 0#no houses|1#one house|2#{1} houses}"
        + " and caused {3, number, currency} of damage.", format);
        
        System.out.println(msg);
    }

}
