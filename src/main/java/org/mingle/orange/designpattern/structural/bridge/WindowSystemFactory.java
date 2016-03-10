/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.bridge;


/**
 * 负责构建WindowImplementor
 * 
 * @since 1.0
 * @author Mingle
 */
public class WindowSystemFactory {
    private static WindowSystemFactory instance;
    
    protected WindowSystemFactory() {}
    
    public static WindowSystemFactory getInstance() {
        if (instance == null)
            instance = new WindowSystemFactory();
        return instance;
    }
    
    public WindowImplementor makeWindowImplementor() {
        WindowImplementor implementor = null;
        
        return implementor;
    }
}
