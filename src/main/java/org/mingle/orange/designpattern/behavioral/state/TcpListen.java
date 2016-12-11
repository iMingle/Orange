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

package org.mingle.orange.designpattern.behavioral.state;


/**
 * TCP监听
 * 
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
