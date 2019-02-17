/*
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
 * limitations under the License.
 */

package org.mingle.orange.java.speciality;

/**
 * 内部类测试
 *
 * @author mingle
 */
@SuppressWarnings("unused")
public class InnerClassTest {
    private int outerNo;
    /**
     * 内部类的创建不依赖以外部类的创建
     */
    private Inner inner = new Inner(5);

    /**
     * @param outerNo
     */
    public InnerClassTest(int outerNo) {
        super();
        this.outerNo = outerNo;
        System.out.println("Outer class create");
    }

    class Inner extends InnerStatic implements Service {
        public Inner(int x) {
            System.out.println("Inner class create");
        }

        public int getOuterNo() {
            return outerNo;
        }
    }

    private static class InnerStatic implements Service {
        @Override
        public void service() {
            System.out.println("InnerStatic service you");
        }
    }

    public static Service getServiceFromInnerClass() {
        return new InnerStatic();
    }

    public String innerFunction() {
        class InnerFunction {
            private String name;
        }

        return new InnerFunction().name;
    }

    public String innerScope() {
        String result = "";
        if (outerNo == 1) {
            class InnerScope {
                private String name;
            }
            return new InnerScope().name;
        }
        return result;
    }

    public Inner innerAnonymous(int x, final int xx) {
        return new Inner(x) {
            private int age = xx;

            public int getX() {
                return xx;
            }
        };
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        InnerClassTest outer = new InnerClassTest(6);
        InnerClassTest.Inner inner = outer.new Inner(5);
        InnerClassTest.InnerStatic innerStatic = new InnerStatic();
        inner.service();
    }

}

class InheritInnerClass extends InnerClassTest.Inner {

    /**
     * @param innerClassTest
     * @param x
     */
    public InheritInnerClass(InnerClassTest innerClassTest, int x) {
        innerClassTest.super(x);
    }

}

interface Service {
    void service();
}

interface ServiceFactory {
    Service getService();
}

class Implementation1 implements Service {
    private Implementation1() {
    }

    public static ServiceFactory factory = new ServiceFactory() {

        @Override
        public Service getService() {
            return new Implementation1();
        }
    };

    @Override
    public void service() {
        System.out.println("Implementation1 service you");
    }

}

class Implementation2 implements Service {
    private Implementation2() {
    }

    public static ServiceFactory factory = new ServiceFactory() {

        @Override
        public Service getService() {
            return new Implementation2();
        }
    };

    @Override
    public void service() {
        System.out.println("Implementation2 service you");
    }

}

/**
 * 用匿名内部类实现的工厂方法
 */
class Factories {
    public static void serviceConsumer(ServiceFactory factory) {
        Service service = factory.getService();
        service.service();
    }

    public static void main(String[] args) {
        Factories.serviceConsumer(Implementation1.factory);
        Factories.serviceConsumer(Implementation2.factory);
    }
}

@SuppressWarnings("unused") interface ClassInInterface {
    void service();

    /**
     * 默认是public static
     */
    class Implementation implements ClassInInterface {

        @Override
        public void service() {

        }

        public static void main(String[] args) {
            ClassInInterface in = new Implementation();
        }
    }
}

@SuppressWarnings("unused") class TestClassInInterface implements ClassInInterface {

    @Override
    public void service() {
        new Implementation().service();
    }
}
