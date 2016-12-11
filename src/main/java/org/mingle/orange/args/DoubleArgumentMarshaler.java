/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.mingle.orange.args.ArgsException.ErrorCode.*;

/**
 * 
 * 
 * @since 1.0
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
