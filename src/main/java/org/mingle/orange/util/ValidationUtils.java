/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Validation工具类
 *
 * @since 1.0
 * @author Mingle
 */
public class ValidationUtils {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.setName("大黄");
        d.setAge(30);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Dog>> result = validator.validate(d);
        for (ConstraintViolation<Dog> constraintViolation : result) {
            System.out.println(constraintViolation.getMessage());
        }
    }
}

class Dog {
    @NotNull(message = "不能为空")
    private String name;

    @Min(value = 1, message = "最少为1") @Max(value = 20, message = "最大为20")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
