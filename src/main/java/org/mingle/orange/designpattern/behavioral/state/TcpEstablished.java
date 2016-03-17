/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.state;


/**
 * TCP已建立
 * 
 * @since 1.0
 * @author Mingle
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
