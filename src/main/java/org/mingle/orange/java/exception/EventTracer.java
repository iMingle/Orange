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

package org.mingle.orange.java.exception;

import java.awt.Component;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author Mingle
 */
public class EventTracer {
    private InvocationHandler handler;
    
    /**
     * 
     */
    public EventTracer() {
        handler = new InvocationHandler() {
            
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                System.out.println(method + ":" + args[0]);
                return null;
            }
        };
    }

    /**
     * add event tracers for all events to witch this component and its children can listen
     * @param c
     */
    public void add(Component c) {
        try {
            BeanInfo info = Introspector.getBeanInfo(c.getClass());
            EventSetDescriptor[] eventSets = info.getEventSetDescriptors();
            
            for (EventSetDescriptor eventSet : eventSets) {
                this.add(c, eventSet);
            }
        } catch (IntrospectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * add a listener to the given event set
     * @param c
     * @param eventSet a descriptor of a listener interface
     */
    private void add(Component c, EventSetDescriptor eventSet) {
        // make proxy object for this listener type and route all calls to the handler
        Object proxy = Proxy.newProxyInstance(null, new Class[] { eventSet.getListenerType() }, handler);
        
        // add the proxy as a listener to the component
        Method addListenerMethod = eventSet.getAddListenerMethod();
        try {
            addListenerMethod.invoke(c, proxy);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
