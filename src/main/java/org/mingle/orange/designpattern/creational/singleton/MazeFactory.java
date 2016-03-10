/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.singleton;

/**
 * 单例迷宫工厂
 * 
 * @since 1.0
 * @author Mingle
 */
public class MazeFactory {
    private static MazeFactory instance;
    
    protected MazeFactory() {
        
    }
    
    public static MazeFactory getInstance() {
        if (instance == null)
            instance = new MazeFactory();
        return instance;
    }
}
