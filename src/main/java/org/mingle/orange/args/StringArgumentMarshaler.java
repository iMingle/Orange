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
public class StringArgumentMarshaler implements ArgumentMarshaler {
	private String stringValue = "";

	/* (non-Javadoc)
	 * @see org.mingle.orange.args.ArgumentMarshaler#set(java.util.Iterator)
	 */
	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			stringValue = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_STRING);
		}
	}

	public static String getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof StringArgumentMarshaler)
			return ((StringArgumentMarshaler) am).stringValue;
		else
			return "";
	}
}
