/**
 * Copyright (c) 2016, Mingle. All rights reserved.
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
