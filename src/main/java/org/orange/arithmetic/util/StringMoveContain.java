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

package org.orange.arithmetic.util;


/**
 * 字符串移位包含的问题
 * 
 * @author mingle
 */
public class StringMoveContain {
    
    /**
     * 节省空间
     * 
     * @param src
     * @param dst
     * @return
     */
    public static boolean isContain1(String src, String dst) {
        boolean result = false;
        
        for (int i = 0; i < src.length(); i++) {
            if (src.contains(dst))
                return true;
            src = src.substring(1) + src.charAt(0);
        }
        
        return result;
    }
    
    /**
     * 节省时间
     * 
     * @param src
     * @param dst
     * @return
     */
    public static boolean isContain2(String src, String dst) {
        boolean result = false;
        
        src = src + src;
        result = src.contains(dst);
        
        return result;
    }
    
}
