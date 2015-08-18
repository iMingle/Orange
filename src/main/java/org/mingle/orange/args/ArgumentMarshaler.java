/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.args;

import java.util.Iterator;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public interface ArgumentMarshaler {
	void set(Iterator<String> currentArgument) throws ArgsException;
}
