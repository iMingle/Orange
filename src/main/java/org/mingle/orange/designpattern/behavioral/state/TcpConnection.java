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
public class TcpConnection {
    private TcpState state;
    
    public TcpConnection() {
        state = TcpClosed.getInstance();
    }
    
    public void activeOpen() {
        state.activeOpen(this);
    }
    
    public void passiveOpen() {
        state.passiveOpen(this);
    }

    public void close() {
        state.close(this);
    }
    
    public void synchronize() {
        state.synchronize(this);
    }

    public void acknowledge() {
        state.acknowledge(this);
    }
    
    public void send() {
        state.send(this);
    }
    
    void processOctet(TcpOctetStream stream) {
        
    }
    
    void changeState(TcpState state) {
        this.state = state;
    }
}
