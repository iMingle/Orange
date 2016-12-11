package org.mingle.orange.java.io.api;

/**
 * @author mingle
 */
public interface Specification<T> {
    boolean test(T item);
}
