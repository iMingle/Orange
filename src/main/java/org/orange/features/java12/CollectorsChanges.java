package org.orange.features.java12;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mingle
 */
public class CollectorsChanges {
    public static void main(String[] args) {
        List<Integer> score = List.of(89, 56, 90, 77, 99);

        // 平均分 总分
        String result = score.stream().collect(Collectors.teeing(
                Collectors.averagingInt(Integer::intValue),
                Collectors.summingInt(Integer::intValue),
                (s1, s2) -> s1 + ":" + s2));

        System.out.println(result);
    }
}
