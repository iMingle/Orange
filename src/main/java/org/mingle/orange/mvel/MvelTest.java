/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.mvel;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class MvelTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String expression = "((name contains 'Mingle') && (age >= 25))";
//        MvelDomainInner domainInner = new MvelDomainInner("JinMinglei", 25);            // error
        MvelDomain domain = new MvelDomain("JinMinglei", 25);
        
        Object compiledExpression = MVEL.compileExpression(expression, new ParserContext());
        System.out.println(MVEL.executeExpression(compiledExpression, domain, Boolean.class));
    }

}

/**
 * 非public类
 */
class MvelDomainInner {
    private String name;
    private int age;

    public MvelDomainInner() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param name
     * @param age
     */
    public MvelDomainInner(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
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
     * @param age
     *            the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
}
