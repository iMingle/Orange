/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.singleton;

/**
 * 静态内部类实现的单例模式
 *
 * @since 1.0
 * @author Mingle
 */
public class SingletonThree {
    private SingletonThree() {}
    
    private static class SingletonThreeInstance {
        private static final SingletonThree INSTANCE = new SingletonThree();
    }
    
    public static SingletonThree getInstance() {
        return SingletonThreeInstance.INSTANCE;
    }
}
