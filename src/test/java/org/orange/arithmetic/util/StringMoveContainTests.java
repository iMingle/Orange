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

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 * 
 * 
 * @author mingle
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
