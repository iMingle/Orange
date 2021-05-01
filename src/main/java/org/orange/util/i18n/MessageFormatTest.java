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

package org.orange.util.i18n;

import java.text.MessageFormat;
import java.util.GregorianCalendar;

/**
 * 格式化带变量的文本
 * 
 * @author mingle
 */
public class MessageFormatTest {

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
