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

package org.mingle.orange.arithmetic.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

/**
 * 
 * 
 * @author mingle
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
