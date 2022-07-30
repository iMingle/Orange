package org.orange.features.java9;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mingle
 */
public class CollectionFactory {
    public static void main(String[] args) {
        Set<Integer> ints = Set.of(1, 2, 3);
        List<String> strings = List.of("first", "second");
        Map<String, String> maps = Map.of("key", "value");

        System.out.println("ints: " + ints);
        System.out.println("strings: " + strings);
        System.out.println("maps: " + maps);
    }
}
