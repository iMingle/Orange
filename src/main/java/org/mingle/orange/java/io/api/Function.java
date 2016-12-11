package org.mingle.orange.java.io.api;

/**
 * @author mingle
 */
public interface Function<From, To> {
    /**
     * @return return the transformed data. {@code null} to indicate ignore the input data.
     */
    To map(From from);
}
