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

package org.mingle.orange.java.io.api;

import java.io.File;
import java.io.IOException;

/**
 * @author mingle
 */
public class InterceptFilterLineTest {
    public static void main(String[] args) throws IOException {
        File source = new File("in");
        File destination = new File("out");

        Input<String, IOException> input = Inputs.text(source);

        Output<String, IOException> output = Outputs.text(destination);

        Specification<String> specification = new Specification<String>() {
            public boolean test(String item) {
                if (item.trim().length() == 0) return false; // 过滤空行
                return true;
            }
        };

        input.transferTo(Filters.filter(specification, output));
    }
}
