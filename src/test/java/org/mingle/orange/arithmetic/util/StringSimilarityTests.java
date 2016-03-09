/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class StringSimilarityTests {
    @Test
    public void calculateStringDistance() {
        String src = "abcdefg";
        String dst = "gfedcba";
        int result = StringSimilarity.calculateStringDistance(src, 0, src.length() - 1, dst, 0, dst.length() - 1);
        assertThat(result == 6).isTrue();
        
        result = StringSimilarity.calculateStringDistance(src, dst);
        assertThat(result == 6).isTrue();
    }
}
