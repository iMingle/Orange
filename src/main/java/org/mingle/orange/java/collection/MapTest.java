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

package org.mingle.orange.java.collection;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @since 1.0
 * @author Mingle
 */
public class MapTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Employee> staff = new HashMap<String, Employee>();
        staff.put("135-4647-9335", new Employee("Jack"));
        staff.put("155-2456-6289", new Employee("Mingle"));
        staff.put("135-0372-3456", new Employee("Mary"));
        staff.put("139-3998-1143", new Employee("Mom"));
        
        System.out.println(staff);
        
        staff.remove("135-0372-3456");
        
        System.out.println(staff);
        
        staff.put("139-3998-1143", new Employee("Mother"));
        
        System.out.println(staff.get("155-2456-6289"));
        
        // iterate through all entries
        for (Map.Entry<String, Employee> entry : staff.entrySet()) {
            String key = entry.getKey();
            Employee value = entry.getValue();
            System.out.println("key = " + key + ", value = " + value);
        }
    }
}

/**
 * A minimalist employee class for testing purposes.
 */
class Employee {
    private String name;
    private double salary;
    /**
     * @param name
     */
    public Employee(String name) {
        super();
        this.name = name;
        this.salary = 0;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Employee [name=" + name + ", salary=" + salary + "]";
    }
}