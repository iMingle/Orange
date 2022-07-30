package org.orange.features.java9;

/**
 * @author mingle
 */
public interface PrivateInterfaceMethods {
    private void init() {
    }

    default void defaultMethod() {
        init();
    }
}
