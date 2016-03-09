/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * 继承特性的测试
 * @since 1.0
 * @author Mingle
 */
public class InheritTest {

    /**
     * @param args
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        Father f = new Father(48);
        Son s = new Son(24);
        Father fs = new Son(12);
        System.out.println(f.inherit(f));    // I am father, i am age of 48
        System.out.println(s.inherit(s));    // I am son, i am age of 24
        System.out.println(s.inherit(f));    // I am father son, i am age of 48
        System.out.println(f.inherit(s));    // I am father, i am age of 24
        System.out.println(fs.inherit(f));    // I am father son, i am age of 48
        System.out.println(fs.inherit(s));    // I am father son, i am age of 24
        fs.retValInherit();                    // return value is Son
        s.staticFunction();                    // 子类继承父类的所有方法，包括静态方法
    }

}

class Father {
    private int age;
    
    /**
     * @param age
     */
    public Father(int age) {
        super();
        this.age = age;
    }

    public static void staticFunction() {
        System.out.println("Father static function!");
    }
    
    @SuppressWarnings("unused")
    private void privateFunction() {
        System.out.println("Father private function!");
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

    public String inherit(Father f) {
        return "I am father, i am age of " + f.age;
    }
    
    /**
     * 允许子类将覆盖方法的返回类型定义为原返回类型的子类型
     */
    public Father retValInherit() {
        System.out.println("return value is Father");
        return new Father(48);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Father [age=" + age + "]";
    }
}

class Son extends Father {
    private int selfId;
    
    /**
     * @return the selfId
     */
    public int getSelfId() {
        return selfId;
    }

    /**
     * @param selfId the selfId to set
     */
    public void setSelfId(int selfId) {
        this.selfId = selfId;
    }

    /**
     * @param age
     */
    public Son(int age) {
        super(age);
    }
    
    public Son(int age, int selfId) {
        super(age);
        this.selfId = selfId;
    }
    
    public String inherit(Father f) {
        return "I am father son, i am age of " + f.getAge();
    }

    public String inherit(Son f) {
        return "I am son, i am age of " + f.getAge();
    }
    
    public Son retValInherit() {
        System.out.println("return value is Son");
        return new Son(24);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Son [selfId=" + selfId + "]";
    }
}