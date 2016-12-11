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

package org.mingle.orange.distributed.rmi.allinone;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class Impletation implements Interface, Serializable {
    private static final long serialVersionUID = -2645048100632268327L;
    
    private String message;

    /**
     * 必须定义构造方法，即使是默认构造方法，也必须把它明确地写出来，因为它必须抛出出RemoteException异常
     */
    public Impletation(String msg) throws RemoteException {
        message = msg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mingle.orange.distributed.rmi.temp.HelloInterface#say()
     */
    @Override
    public String say() throws RemoteException {
        System.out.println("Called by HelloClient");
        return message;
    }

}
