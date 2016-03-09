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
public class StringMoveContainTests {
    @Test
    public void isContain1() {
        assertThat(StringMoveContain.isContain1("AABCD", "CDAA")).isTrue();
        assertThat(StringMoveContain.isContain1("AABCD", "AAB")).isTrue();
        assertThat(StringMoveContain.isContain1("AABCD", "A")).isTrue();
        assertThat(StringMoveContain.isContain1("AABCD", "D")).isTrue();
        assertThat(StringMoveContain.isContain1("AABCD", "DAA")).isTrue();
        assertThat(StringMoveContain.isContain1("AABCD", "CDB")).isFalse();
        assertThat(StringMoveContain.isContain1("AABCD", "CB")).isFalse();
        
        assertThat(StringMoveContain.isContain2("AABCD", "CDAA")).isTrue();
        assertThat(StringMoveContain.isContain2("AABCD", "AAB")).isTrue();
        assertThat(StringMoveContain.isContain2("AABCD", "A")).isTrue();
        assertThat(StringMoveContain.isContain2("AABCD", "D")).isTrue();
        assertThat(StringMoveContain.isContain2("AABCD", "DAA")).isTrue();
        assertThat(StringMoveContain.isContain2("AABCD", "CDB")).isFalse();
        assertThat(StringMoveContain.isContain2("AABCD", "CB")).isFalse();
    }
}
