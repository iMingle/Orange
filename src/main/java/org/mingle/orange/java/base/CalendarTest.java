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

package org.mingle.orange.java.base;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * @author mingle
 */
public class CalendarTest {
    /**
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Calendar calendar = new GregorianCalendar(Locale.UK);
        System.out.println(calendar.toString());
        int month = calendar.get(Calendar.MONTH);
        
        Date date = calendar.getTime();
        
        System.out.println(date);
        
        DateFormatSymbols dfs = new DateFormatSymbols();
        
        for (String s : dfs.getMonths()) {
            System.out.println(s);
        }
        
    }

}
