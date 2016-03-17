/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.state;


/**
 * TCP关闭
 * 
 * @since 1.0
 * @author Mingle
 */
public class TcpClosed extends TcpState {
    private static TcpClosed instance;
    
    private TcpClosed() {
        if (instance != null)
            throw new IllegalStateException("singleton, cannot create another object");
    }
    
    public static synchronized TcpState getInstance() {
        if (instance == null)
            instance = new TcpClosed();
        return instance;
    }

    @Override public void activeOpen(TcpConnection connection) {
        changeState(connection, TcpEstablished.getInstance());
    }

    @Override public void passiveOpen(TcpConnection connection) {
        changeState(connection, TcpListen.getInstance());
    }

}
