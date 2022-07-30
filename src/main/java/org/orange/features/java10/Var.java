package org.orange.features.java10;

import java.util.List;

/**
 * @author mingle
 */
public class Var {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4);
        for (var value : list) {
            System.out.println(value);
        }

        for (var i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
