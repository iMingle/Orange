/*
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
 * limitations under the License.
 */

package org.mingle.orange.designpattern.behavioral.state;


/**
 * TCP已建立
 *
 * @author mingle
 */
public class TcpEstablished extends TcpState {
    private static TcpEstablished instance;

    private TcpEstablished() {
        if (instance != null)
            throw new IllegalStateException("singleton, cannot create another object");
    }

    public static synchronized TcpState getInstance() {
        if (instance == null)
            instance = new TcpEstablished();
        return instance;
    }

    @Override public void transmit(TcpConnection connection,
                                   TcpOctetStream stream) {
        connection.processOctet(stream);
    }

    @Override public void close(TcpConnection connection) {
        // send FIN, receive ACK of FIN
        changeState(connection, TcpListen.getInstance());
    }

}
