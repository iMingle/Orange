/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.state;


/**
 * TCP监听
 * 
 * @since 1.0
 * @author Mingle
 */
public class TcpListen extends TcpState {
    private static TcpListen instance;
    
    private TcpListen() {
        if (instance != null)
            throw new IllegalStateException("singleton, cannot create another object");
    }
    
    public static synchronized TcpState getInstance() {
        if (instance == null)
            instance = new TcpListen();
        return instance;
    }

    @Override public void send(TcpConnection connection) {
        // send SYN, receive SYN, ACK, etc.
        changeState(connection, TcpEstablished.getInstance());
    }

}
