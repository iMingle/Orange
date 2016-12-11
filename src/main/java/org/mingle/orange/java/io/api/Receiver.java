package org.mingle.orange.java.io.api;

/**
 * @author mingle
 */
public interface Receiver<T, ReceiverThrowableType extends Throwable> {
    void receive(T item) throws ReceiverThrowableType;

    void finished() throws ReceiverThrowableType;
}
