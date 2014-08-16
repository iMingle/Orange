/**
 * @version 1.0 2014��6��22��
 * @author mingle
 */
package org.mingle.orange.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 1.0 2014��6��22��
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionListenerFor {
	String source();
}
