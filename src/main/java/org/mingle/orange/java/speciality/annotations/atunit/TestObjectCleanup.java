/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.annotations.atunit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 清理对象
 *
 * @since 1.8
 * @author Mingle
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestObjectCleanup {

}
