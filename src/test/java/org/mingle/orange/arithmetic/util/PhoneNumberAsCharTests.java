/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import org.junit.Test;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class PhoneNumberAsCharTests {
    @Test
    public void changeNumberToStr() {
        int[] answer = new int[] {0, 0};
        PhoneNumberAsChar.changeNumberToStr(45L, answer);
        PhoneNumberAsChar.changeNumberToStrRecursive(45L, answer, 0);
    }
}
