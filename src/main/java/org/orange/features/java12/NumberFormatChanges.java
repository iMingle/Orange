package org.orange.features.java12;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author mingle
 */
public class NumberFormatChanges {
    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        String formattedString = formatter.format(25000L);
        System.out.println(formattedString);
    }
}
