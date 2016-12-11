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

package org.mingle.orange.java.base;

/**
 * 
 * @author mingle
 */
public class Person {
    private String name;
    private int age;
    private int sex;
    
    public Person() {
        
    }
    
    public Person(String name, int age, int sex) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }
    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * @return the sex
     */
    public int getSex() {
        return sex;
    }
    /**
     * @param sex the sex to set
     */
    public void setSex(int sex) {
        this.sex = sex;
    }
    
}
