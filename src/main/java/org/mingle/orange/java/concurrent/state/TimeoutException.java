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

package org.mingle.orange.java.concurrent.state;

/**
 * 
 * 
 * @author Mingle
 */
public class TimeoutException extends InterruptedException {
    private static final long serialVersionUID = -6354763818990858746L;
    
    protected long timeout;

    public TimeoutException(long timeout) {
        super();
        this.timeout = timeout;
    }
}
