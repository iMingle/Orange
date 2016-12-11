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
class Professor0 implements Cloneable {
    String name;
    int age;
 
    Professor0(String name, int age) {
        this.name = name;
        this.age = age;
    }
 
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
 
class Student0 implements Cloneable {
    String name;// 常量对象。
    int age;
    Professor0 p;// 学生1和学生2的引用值都是一样的。
 
    Student0(String name, int age, Professor0 p) {
        this.name = name;
        this.age = age;
        this.p = p;
    }
 
    public Object clone() {
        Student0 o = null;
        try {
            o = (Student0) super.clone();
//            o.p = (Professor0) this.p.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
 
        return o;
    }
}
 
public class ShallowCopy {
    public static void main(String[] args) {
        Professor0 p = new Professor0("wangwu", 50);
        Student0 s1 = new Student0("zhangsan", 18, p);
        Student0 s2 = (Student0) s1.clone();
        s2.p.name = "lisi";
        s2.p.age = 30;
        s2.name = "z";
        s2.age = 45;
        System.out.println("学生s1的姓名：" + s1.name + "\n学生s1教授的姓名：" + s1.p.name + "," + "\n学生s1教授的年纪" + s1.p.age);// 学生1的教授
    }
}
