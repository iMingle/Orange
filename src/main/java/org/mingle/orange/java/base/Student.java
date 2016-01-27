/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.base;

import java.util.Date;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
public class Student extends Person implements Cloneable {
    
    private int no;
    private String name;
    private String email;
    private int age;
    private int sex;
    private Date birthday;
    
    public Student() {
        
    }
    
    public Student(int no, String name, String email, int age, int sex, Date birthday) {
        super();
        this.no = no;
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Student clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        Student student = (Student) super.clone();
//        student.birthday = (Date) this.birthday.clone();
        return student;
    }

    /**
     * @return the no
     */
    public int getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(int no) {
        this.no = no;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
    
    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
