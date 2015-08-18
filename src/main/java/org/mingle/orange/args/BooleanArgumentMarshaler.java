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
public class BooleanArgumentMarshaler implements ArgumentMarshaler {
	private boolean booleanValue = false;

	/* (non-Javadoc)
	 * @see org.mingle.orange.args.ArgumentMarshaler#set(java.util.Iterator)
	 */
	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		booleanValue = true;
	}

	public static boolean getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof BooleanArgumentMarshaler)
			return ((BooleanArgumentMarshaler) am).booleanValue;
		else
			return false;
	}
}
