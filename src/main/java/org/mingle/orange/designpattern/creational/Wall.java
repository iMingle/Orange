/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational;

/**
 * 墙
 * 
 * @since 1.0
 * @author Mingle
 */
public class Wall extends MapSite implements Cloneable {

    @Override
    public void enter() {
        
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
