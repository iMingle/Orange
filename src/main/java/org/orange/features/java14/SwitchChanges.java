package org.orange.features.java14;

import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.TUESDAY;
import static java.util.Calendar.WEDNESDAY;

/**
 * @author mingle
 */
public class SwitchChanges {
    public static void main(String[] args) {
        var day = SATURDAY;
        boolean isWeekend = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> false;
            case SATURDAY, SUNDAY -> true;
            default -> throw new IllegalStateException("Illegal day entry :: " + day);
        };

        System.out.println(isWeekend);  //true or false - based on current day
    }
}
