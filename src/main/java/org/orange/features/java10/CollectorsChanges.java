package org.orange.features.java10;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jinminglei
 */
public class CollectorsChanges {
    public static void main(String[] args) {
        List<Integer> list = List.of(10, 15, 20, 24);

        List<Integer> newList = list.stream().filter(i -> i % 3 == 0).collect(Collectors.toUnmodifiableList());

        System.out.println(newList);
    }
}
