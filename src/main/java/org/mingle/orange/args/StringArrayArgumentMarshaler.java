/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.args;

import static org.mingle.orange.args.ArgsException.ErrorCode.INVALID_INTEGER;
import static org.mingle.orange.args.ArgsException.ErrorCode.MISSING_INTEGER;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
    private String[] stringArrayValue = new String[0];

    /* (non-Javadoc)
     * @see org.mingle.orange.args.ArgumentMarshaler#set(java.util.Iterator)
     */
    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        
        try {
            parameter = currentArgument.next();
            stringArrayValue = parameter.split(",");
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_INTEGER);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_INTEGER, parameter);
        }
    }

    public static String[] getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof StringArrayArgumentMarshaler)
            return ((StringArrayArgumentMarshaler) am).stringArrayValue;
        else
            return new String[0];
    }
}
