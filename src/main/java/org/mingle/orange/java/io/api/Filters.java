/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.java.io.api;

/**
 * 过滤器
 *
 * @author mingle
 */
public class Filters {
    static class SpecificationOutputWrapper<T, ReceiverThrowableType extends Throwable>
            implements Output<T, ReceiverThrowableType> {

        final Output<T, ReceiverThrowableType> output;
        final Specification<T> specification;

        public SpecificationOutputWrapper(Output<T, ReceiverThrowableType> output, Specification<T> specification) {
            this.output = output;
            this.specification = specification;
        }

        public <SenderThrowableType extends Throwable> void receiveFrom(Sender<T, SenderThrowableType> sender)
                throws ReceiverThrowableType, SenderThrowableType {
            output.receiveFrom(new SpecificationSenderWrapper<T, SenderThrowableType>(sender, specification));
        }
    }

    static class SpecificationSenderWrapper<T, SenderThrowableType extends Throwable>
            implements Sender<T, SenderThrowableType> {

        final Sender<T, SenderThrowableType> sender;
        final Specification<T> specification;

        public SpecificationSenderWrapper(Sender<T, SenderThrowableType> sender, Specification<T> specification) {
            this.sender = sender;
            this.specification = specification;
        }

        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<T, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, SenderThrowableType {
            sender.sendTo(new SpecificationReceiverWrapper<T, ReceiverThrowableType>(receiver, specification));
        }
    }

    static class SpecificationReceiverWrapper<T, ReceiverThrowableType extends Throwable>
            implements Receiver<T, ReceiverThrowableType> {

        final Receiver<T, ReceiverThrowableType> receiver;
        final Specification<T> specification;

        public SpecificationReceiverWrapper(Receiver<T, ReceiverThrowableType> receiver,
                                            Specification<T> specification) {
            this.receiver = receiver;
            this.specification = specification;
        }

        public void receive(T item) throws ReceiverThrowableType {
            if (specification.test(item)) {
                receiver.receive(item);
            }
        }

        public void finished() throws ReceiverThrowableType {
            receiver.finished();
        }
    }

    public static <T, ReceiverThrowableType extends Throwable>
    Output<T, ReceiverThrowableType> filter(Specification<T> specification,
                                            final Output<T, ReceiverThrowableType> output) {
        return new SpecificationOutputWrapper<T, ReceiverThrowableType>(output, specification);
    }


    static class FunctionOutputWrapper<From, To, ReceiverThrowableType extends Throwable>
            implements Output<From, ReceiverThrowableType> {

        final Output<To, ReceiverThrowableType> output;
        final Function<From, To> function;

        public FunctionOutputWrapper(Output<To, ReceiverThrowableType> output, Function<From, To> function) {
            this.output = output;
            this.function = function;
        }

        public <SenderThrowableType extends Throwable> void receiveFrom(Sender<From, SenderThrowableType> sender)
                throws ReceiverThrowableType, SenderThrowableType {
            output.receiveFrom(new FunctionSenderWrapper<From, To, SenderThrowableType>(sender, function));
        }
    }

    static class FunctionSenderWrapper<From, To, SenderThrowableType extends Throwable> implements
            Sender<To, SenderThrowableType> {
        final Sender<From, SenderThrowableType> sender;
        final Function<From, To> function;

        public FunctionSenderWrapper(Sender<From, SenderThrowableType> sender, Function<From, To> function) {
            this.sender = sender;
            this.function = function;
        }

        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<To, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, SenderThrowableType {
            sender.sendTo(new FunctionReceiverWrapper<From, To, ReceiverThrowableType>(receiver, function));
        }
    }

    static class FunctionReceiverWrapper<From, To, ReceiverThrowableType extends Throwable>
            implements Receiver<From, ReceiverThrowableType> {

        final Receiver<To, ReceiverThrowableType> receiver;
        final Function<From, To> function;

        public FunctionReceiverWrapper(Receiver<To, ReceiverThrowableType> receiver, Function<From, To> function) {
            this.receiver = receiver;
            this.function = function;
        }

        public void receive(From item) throws ReceiverThrowableType {
            receiver.receive(function.map(item));
        }

        public void finished() throws ReceiverThrowableType {
            receiver.finished();
        }
    }

    public static <From, To, ReceiverThrowableType extends Throwable>
    Output<From, ReceiverThrowableType> filter(Function<From, To> function,
                                               final Output<To, ReceiverThrowableType> output) {
        return new FunctionOutputWrapper<From, To, ReceiverThrowableType>(output, function);
    }

    private Filters() {
    }
}
