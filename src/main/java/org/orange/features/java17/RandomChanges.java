package org.orange.features.java17;

import java.util.random.RandomGenerator;

/**
 * @author mingle
 */
public class RandomChanges {
    public static void main(String[] args) {
        RandomGenerator random = RandomGenerator.of("L64X128MixRandom");
        System.out.println(random.nextInt());
    }
}
