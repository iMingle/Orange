/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.state;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class TcpState {
    public void transmit(TcpConnection connection, TcpOctetStream stream) {}
    
    public void activeOpen(TcpConnection connection) {}
    
    public void passiveOpen(TcpConnection connection) {}

    public void close(TcpConnection connection) {}
    
    public void synchronize(TcpConnection connection) {}

    public void acknowledge(TcpConnection connection) {}
    
    public void send(TcpConnection connection) {}
    
    protected void changeState(TcpConnection connection, TcpState state) {
        connection.changeState(state);
    }
}
