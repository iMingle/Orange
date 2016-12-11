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

package org.mingle.orange.java.text;

import java.text.DecimalFormat;

/**
 * 格式化浮点数
 * 
 * @author mingle
 */
public class DecimalFormatTest {

    public static void main(String[] args) {
        double d = 190.000;
        DecimalFormat format = new DecimalFormat("#.##");
        System.out.println(format.format(d));        // 190
        format.applyPattern("0.00");
        System.out.println(format.format(d));        // 190.00
        
    }

}
