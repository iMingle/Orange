/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.lang.reflect.Method;

/**
 * 接口与类型信息，使用类型信息，耦合性还是会传播出去
 *
 * @since 1.8
 * @author Mingle
 */
public class InterfaceViolation {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        IA a = new CB();
        a.f();
//        a.g(); // compile error
        if (a instanceof CB) {
            CB b = (CB) a;
            ((CB) a).g(); // 传播处,a调用其没有的方法
        }
    }
}

interface IA {
    void f();
}

class CB implements IA {

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.A#f()
     */
    @Override
    public void f() {}
    
    public void g() {}
}

/**
 * 解决方法是对实现使用包访问权限
 */
class CC implements IA {

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.IA#f()
     */
    @Override
    public void f() {
        System.out.println("public CC.f()");
    }
    
    public void g() {
        System.out.println("public CC.g()");
    }
    
    void u() {
        System.out.println("package CC.u()");
    }
    
    protected void v() {
        System.out.println("protected CC.v()");
    }
    
    @SuppressWarnings("unused")
    private void w() {
        System.out.println("private CC.w()");
    }
}

/**
 * 此类应该定义为public的
 */
class HiddenCC {
    /**
     * 只能访问IA类型,不能在包的外部访问CC
     * @return
     */
    public static IA makeIA() {
        return new CC();
    }
}

class HiddenImplementation {
    static void callHiddenMethod(Object o, String methodName) throws Exception {
        Method g = o.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(o);
    }
    
    public static void main(String[] args) throws Exception {
        IA a = HiddenCC.makeIA();
        a.f();
        System.out.println(a.getClass().getName());
        /* 如果在包的外部，下句编译不通过 */
        if (a instanceof CC) {}
        
        callHiddenMethod(a, "g");
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");    // private方法也可以访问
    }
}