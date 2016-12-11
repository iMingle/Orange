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

package org.mingle.orange.java.speciality;

import java.lang.reflect.Method;

/**
 * 接口与类型信息，使用类型信息，耦合性还是会传播出去
 *
 * @author mingle
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
