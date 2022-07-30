package org.orange.features.java11;

import java.util.stream.Collectors;

/**
 * @author mingle
 */
public class StringChanges {
    public static void main(String[] args) {
        System.out.println(" ".isBlank());

        String str = "I\nam\nthe\ncreator of this Blog";
        System.out.println(str.lines().collect(Collectors.toList()));

        str = "name\u2000";
        System.out.println(str.trim());

        System.out.println("=".repeat(2));
    }
}
