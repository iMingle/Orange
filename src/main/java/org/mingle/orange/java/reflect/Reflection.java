package org.mingle.orange.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.Data;

public class Reflection {
    
    private String name;
    
    public String getName(int age, String k) {
        System.out.println("Mingle" + age + k);
        return name;
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Field[] fields = new Field[10];
        Method[] methods = new Method[10];
        Reflection r = new Reflection();
        
        fields = r.getClass().getDeclaredFields();
        methods = Reflection.class.getDeclaredMethods();
        
        System.out.println("fields" + fields.length);
        System.out.println("methods" + methods.length);
        
        try {
            methods[1].invoke(r, 26, "test");
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
        
        System.out.println(fields[0].getName());
        System.out.println(methods[0].getName());
        System.out.println(methods[1].getName());
        
        try {
            // Inner must has a default constructor, otherwise error
            Class<?> clazz = Class.forName("org.mingle.orange.reflect.Inner");
            Inner inner = (Inner) clazz.newInstance();
            System.out.println(inner);
            
            inner = Inner.class.newInstance();
            System.out.println(inner);
            
            Constructor<Inner> con = Inner.class.getConstructor(new Class<?>[] { String.class });
            inner = con.newInstance("Mingle");
            System.out.println(inner);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

@Data class Inner {
    private String name;
    
    public Inner() {}

    public Inner(String name) {
        super();
        this.name = name;
    }

}