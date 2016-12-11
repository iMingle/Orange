package org.mingle.orange.java.io.api;

/**
 * @author mingle
 */
public interface Input<T, SenderThrowableType extends Throwable> {
    <ReceiverThrowableType extends Throwable> void transferTo(Output<T, ReceiverThrowableType> output)
            throws SenderThrowableType, ReceiverThrowableType;
}
