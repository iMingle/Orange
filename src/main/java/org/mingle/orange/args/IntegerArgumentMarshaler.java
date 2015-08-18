/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.mingle.orange.args.ArgsException.ErrorCode.*;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class IntegerArgumentMarshaler implements ArgumentMarshaler {
	private int intValue = 0;

	/* (non-Javadoc)
	 * @see org.mingle.orange.args.ArgumentMarshaler#set(java.util.Iterator)
	 */
	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;
		
		try {
			parameter = currentArgument.next();
			intValue = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_INTEGER);
		} catch (NumberFormatException e) {
			throw new ArgsException(INVALID_INTEGER, parameter);
		}
	}

	public static int getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof IntegerArgumentMarshaler)
			return ((IntegerArgumentMarshaler) am).intValue;
		else
			return 0;
	}
}
