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

package org.mingle.orange.designpattern.creational.factory;

/**
 * 用匿名内部类实现的工厂方法
 * 
 * @author mingle
 */
public class InnerClassFactory {
    public static void serviceConsumer(ServiceFactory factory) {
        Service service = factory.getService();
        service.service();
    }
    
    public static void main(String[] args) {
        InnerClassFactory.serviceConsumer(Implementation1.factory);
        InnerClassFactory.serviceConsumer(Implementation2.factory);
    }
}

interface Service {
    void service();
}

interface ServiceFactory {
    Service getService();
}

class Implementation1 implements Service {
    private Implementation1() {}
    public static ServiceFactory factory = new ServiceFactory() {
        
        @Override
        public Service getService() {
            return new Implementation1();
        }
    };

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.Service#service()
     */
    @Override
    public void service() {
        System.out.println("Implementation1 service you");
    }
    
}

class Implementation2 implements Service {
    private Implementation2() {}
    public static ServiceFactory factory = new ServiceFactory() {
        
        @Override
        public Service getService() {
            return new Implementation2();
        }
    };
    
    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.Service#service()
     */
    @Override
    public void service() {
        System.out.println("Implementation2 service you");
    }
    
}
