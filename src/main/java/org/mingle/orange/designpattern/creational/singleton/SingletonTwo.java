package org.mingle.orange.designpattern.creational.singleton;

public class SingletonTwo {

    private static SingletonTwo INSTANCE = null;

    private SingletonTwo() {
        // 防止借助AccessibleObject.setAccessible的反射机制调用私有构造器
        if (INSTANCE != null)
            throw new IllegalStateException("singleton, cannot create another object");
    }

    public static SingletonTwo getInstance() {
        if (null == INSTANCE)
            INSTANCE = new SingletonTwo();

        return INSTANCE;
    }
}
