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

package org.mingle.orange.java.generic;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author mingle
 */
public class GenericReflectionTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // read class name from command-line or user input
        String name = null;
        if (args.length != 0)
            name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Collections): ");
            name = in.next();
            in.close();
        }

        try {
            Class<?> c1 = Class.forName(name);
            printClass(c1);

            for (Method m : c1.getDeclaredMethods()) {
                printMethod(m);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param c1
     */
    private static void printClass(Class<?> c1) {
        System.out.print(c1);
        printTypes(c1.getTypeParameters(), "<", ", ", ">", true);
        Type sc = c1.getGenericSuperclass();

        if (sc != null) {
            System.out.print(" extends ");
            printType(sc, false);
        }

        printTypes(c1.getGenericInterfaces(), " implements ", ", ", "", false);
        System.out.println();
    }

    /**
     * @param m
     */
    private static void printMethod(Method m) {
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print(" ");
        printTypes(m.getTypeParameters(), "<", ", ", ">", true);

        printType(m.getGenericReturnType(), false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(m.getGenericParameterTypes(), "", ", ", "", false);
        System.out.println(")");
    }

    /**
     * @param type
     * @param isDefinition
     */
    private static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class) {
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        } else if (type instanceof TypeVariable) {
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());

            if (isDefinition) {
                printTypes(((TypeVariable<?>) type).getBounds(), " extends ",
                        " & ", "", false);
            }
        } else if (type instanceof WildcardType) {
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(t.getLowerBounds(), " super ", " & ", "", false);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);
        } else if (type instanceof GenericArrayType) {
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(), isDefinition);
            System.out.print("[]");
        }
    }

    /**
     * 
     * @param types
     * @param pre
     * @param sep
     * @param suf
     * @param isDefinition
     */
    private static void printTypes(Type[] types, String pre, String sep,
            String suf, boolean isDefinition) {
        if (pre.equals(" extends ")
                && Arrays.equals(types, new Type[] { Object.class }))
            return;
        if (types.length > 0)
            System.out.print(pre);
        for (int i = 0; i < types.length; i++) {
            if (i > 0)
                System.out.print(sep);
            printType(types[i], isDefinition);
        }
        if (types.length > 0)
            System.out.print(suf);
    }

}
