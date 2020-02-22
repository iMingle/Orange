/*
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
 * limitations under the License.
 */

package org.orange.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.orange.args.ArgsException.ErrorCode.INVALID_INTEGER;
import static org.orange.args.ArgsException.ErrorCode.MISSING_INTEGER;

/**
 * 
 * 
 * @author mingle
 */
public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
    private String[] stringArrayValue = new String[0];

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
