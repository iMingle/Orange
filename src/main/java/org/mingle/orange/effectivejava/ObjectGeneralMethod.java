/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.Arrays;
import java.util.Date;

/**
 * 所有对象都通用的方法
 * 
 * @since 1.0
 * @author Mingle
 */
public class ObjectGeneralMethod implements Comparable<ObjectGeneralMethod> {
    private byte b;
    private short sex;
    private int age;
    private long date;
    private float f;
    private double d;
    private boolean is;
    private char c;
    private String name;
    private Date[] array;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + Arrays.hashCode(array);
        result = prime * result + b;
        result = prime * result + c;
        long temp;
        temp = Double.doubleToLongBits(d);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (int) (date ^ (date >>> 32));
        result = prime * result + Float.floatToIntBits(f);
        result = prime * result + (is ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + sex;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
        if (!(obj instanceof ObjectGeneralMethod))
            return false;
        ObjectGeneralMethod other = (ObjectGeneralMethod) obj;
        if (age != other.age)
            return false;
        if (!Arrays.equals(array, other.array))
            return false;
        if (b != other.b)
            return false;
        if (c != other.c)
            return false;
//        if (Double.doubleToLongBits(d) != Double.doubleToLongBits(other.d))
//            return false;
        if (Double.compare(d, other.d) != 0)
            return false;
        if (date != other.date)
            return false;
//        if (Float.floatToIntBits(f) != Float.floatToIntBits(other.f))
//            return false;
        if (Float.compare(f, other.f) != 0)
            return false;
        if (is != other.is)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (sex != other.sex)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "ObjectGeneralMethod [b=" + b + ", sex=" + sex + ", age=" + age
                + ", date=" + date + ", f=" + f + ", d=" + d + ", is=" + is
                + ", c=" + c + ", name=" + name + ", array="
                + Arrays.toString(array) + "]";
    }

    @Override
    public int compareTo(ObjectGeneralMethod o) {
        int bDiff = b - o.b;
        if (bDiff != 0)
            return bDiff;
        int sexDiff = sex - o.sex;
        if (sexDiff != 0)
            return sexDiff;
        int ageDiff = age - o.age;
        if (ageDiff != 0)
            return ageDiff;
        int dateDiff = Long.compare(date, o.date);
        if (dateDiff != 0)
            return dateDiff;
        int fDiff = Float.compare(f, o.f);
        if (fDiff != 0)
            return fDiff;
        int dDiff = Double.compare(d, o.d);
        if (dDiff != 0)
            return dDiff;
        int isDiff = Boolean.compare(is, o.is);
        if (isDiff != 0)
            return isDiff;
        return c - o.c;
    }

    public static void main(String[] args) {
        byte b = -128;
        System.out.println(b);
    }
}
