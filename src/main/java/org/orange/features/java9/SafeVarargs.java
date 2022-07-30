package org.orange.features.java9;

import java.util.List;

/**
 * @author mingle
 */
public class SafeVarargs {
    @java.lang.SafeVarargs
    private <T> void addToList(List<T> listArg, T... elements) {
        for (T x : elements) {
            listArg.add(x);
        }
    }
}
