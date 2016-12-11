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

import lombok.Getter;
import lombok.Setter;

import static org.mingle.orange.args.ArgsException.ErrorCode.OK;

/**
 * 
 * 
 * @author mingle
 */
public class ArgsException extends Exception {
    private static final long serialVersionUID = -6370329612238075513L;

    private @Getter @Setter char errorArgumentId = '\0';
    private @Getter @Setter String errorParameter = null;
    private @Getter @Setter ErrorCode errorCode = OK;
    
    public ArgsException() {}
    
    public ArgsException(String message) {
        super(message);
    }
    
    public ArgsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    public ArgsException(ErrorCode errorCode, String errorParameter) {
        this.errorCode = errorCode;
        this.errorParameter = errorParameter;
    }
    
    public ArgsException(ErrorCode errorCode, char errorArgumentId, String errorParameter) {
        this.errorCode = errorCode;
        this.errorParameter = errorParameter;
        this.errorArgumentId = errorArgumentId;
    }
    
    public String errorMessage() {
        switch (errorCode) {
        case OK:
            return "TILT: Should not get here.";
        case UNEXPECTED_ARGUMENT:
            return String.format("Argument -%c unexpected", errorArgumentId);
        case MISSING_STRING:
            return String.format("Could not find string parameter for -%c", errorArgumentId);
        case INVALID_INTEGER:
            return String.format("Argument -%c expects an integer but was '%s'", errorArgumentId, errorParameter);
        case MISSING_INTEGER:
            return String.format("Could not find integer parameter for -%c", errorArgumentId);
        case INVALID_DOUBLE:
            return String.format("Argument -%c expects an double but was '%s'", errorArgumentId, errorParameter);
        case MISSING_DOUBLE:
            return String.format("Could not find double parameter for -%c", errorArgumentId);
        case INVALID_ARGUMENT_NAME:
            return String.format("'-%c' is not a valid argument name.", errorArgumentId);
        case INVALID_ARGUMENT_FORMAT:
            return String.format("'-%s' is not a valid argument format.", errorParameter);
        }
        return "";
    }
    
    public enum ErrorCode {
        OK, INVALID_ARGUMENT_FORMAT, UNEXPECTED_ARGUMENT, INVALID_ARGUMENT_NAME, MISSING_STRING,
        MISSING_INTEGER, INVALID_INTEGER, MISSING_DOUBLE, INVALID_DOUBLE
    }
}
