/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @since 1.8
 * @author Mingle
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionListenerFor {
	String source();
}
