package org.orange.features.java16;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author mingle
 */
public class TimeInterval {
    public static void main(String[] args) {
        String date1 = DateTimeFormatter.ofPattern("a").format(LocalTime.now());
        String date2 = DateTimeFormatter.ofPattern("B").format(LocalTime.now());
        String date3 = DateTimeFormatter.ofPattern("k").format(LocalTime.now());
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
    }
}
