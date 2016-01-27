/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;


/**
 * 字符串移位包含的问题
 * 
 * @since 1.8
 * @author Mingle
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
