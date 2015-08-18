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
public class DoubleArgumentMarshaler implements ArgumentMarshaler {
	private double doubleValue = 0;

	/* (non-Javadoc)
	 * @see org.mingle.orange.args.ArgumentMarshaler#set(java.util.Iterator)
	 */
	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;
		
		try {
			parameter = currentArgument.next();
			doubleValue = Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_DOUBLE);
		} catch (NumberFormatException e) {
			throw new ArgsException(INVALID_DOUBLE, parameter);
		}
	}

	public static double getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof DoubleArgumentMarshaler)
			return ((DoubleArgumentMarshaler) am).doubleValue;
		else
			return 0;
	}
}
