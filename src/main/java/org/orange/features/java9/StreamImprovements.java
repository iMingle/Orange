package org.orange.features.java9;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author mingle
 */
public class StreamImprovements {
    public static void main(String[] args) {
        IntStream.iterate(1, i -> i < 100, i -> i + 1)
                .takeWhile(value -> value < 50)
                .dropWhile(value -> value < 10)
                .forEach(System.out::println);

        Stream<Integer> s = Optional.of(1).stream();
        System.out.println(s);
        Stream.ofNullable(1).forEach(System.out::println);
    }
}
