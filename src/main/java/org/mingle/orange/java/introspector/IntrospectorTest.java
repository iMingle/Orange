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

package org.mingle.orange.java.introspector;

import lombok.Data;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * java内省
 *
 * @author mingle
 */
public class IntrospectorTest {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", BeanIntrospector.class);
        System.out.println(propertyDescriptor.getPropertyType());
        System.out.println(propertyDescriptor.getPropertyEditorClass());
        BeanIntrospector beanIntrospector = new BeanIntrospector();
        propertyDescriptor.getWriteMethod().invoke(beanIntrospector, "mingle");
        System.out.println(propertyDescriptor.getReadMethod().invoke(beanIntrospector, null));
    }
}

@Data class BeanIntrospector {
    private int age;
    private String name;
}
