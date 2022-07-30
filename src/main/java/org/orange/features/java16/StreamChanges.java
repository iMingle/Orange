package org.orange.features.java16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mingle
 */
public class StreamChanges {
    public static void main(String[] args) {
        List<String> integersAsString = Arrays.asList("1", "2", "3");
        // 之前这样写
        List<Integer> ints = integersAsString.stream().map(Integer::parseInt).collect(Collectors.toList());
        // 现在可以这样写
        List<Integer> intsEquivalent = integersAsString.stream().map(Integer::parseInt).toList();

        System.out.println(ints);
        System.out.println(intsEquivalent);
    }
}
