package org.orange.features.java11;

import java.util.Optional;

/**
 * @author mingle
 */
public class OptionalChanges {
    public static void main(String[] args) {
        Optional<String> str = Optional.of("Mano");
        System.out.println(str.isEmpty());
    }
}
