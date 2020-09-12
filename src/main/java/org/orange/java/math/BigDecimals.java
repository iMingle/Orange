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

package org.orange.java.math;

import java.math.BigDecimal;

/**
 * @author mingle
 */
public class BigDecimals {

    public static void main(String[] args) {
        System.out.println("0.05 + 0.01 = " + (0.05 + 0.01));
        System.out.println("1.0 - 0.42 = " + (1.0 - 0.42));
        System.out.println("4.015 * 100 = " + (4.015 * 100));
        System.out.println("123.3 / 100 = " + (123.3 / 100));

        BigDecimal data = new BigDecimal(0.1);
        System.out.println(data);
        data = new BigDecimal("0.1");
        System.out.println(data);
        data = BigDecimal.valueOf(0.1);
        System.out.println(data);

        BigDecimal f1 = new BigDecimal("0.05");
        BigDecimal f2 = BigDecimal.valueOf(0.01);
        BigDecimal f3 = new BigDecimal(0.05);

        System.out.println("使用String作为BigDecimal构造器参数：");
        System.out.println("0.05 + 0.01 = " + f1.add(f2));
        System.out.println("0.05 - 0.01 = " + f1.subtract(f2));
        System.out.println("0.05 * 0.01 = " + f1.multiply(f2));
        System.out.println("0.05 / 0.01 = " + f1.divide(f2));

        System.out.println("使用double作为BigDecimal构造器：");
        System.out.println("0.05 + 0.01 = " + f3.add(f2));
        System.out.println("0.05 - 0.01 = " + f3.subtract(f2));
        System.out.println("0.05 * 0.01 = " + f3.multiply(f2));
        System.out.println("0.05 / 0.01 = " + f3.divide(f2));

        /** 银行家舍入算法(四舍六入五考虑，五后非零就进一，五后为零看奇偶，五前为偶应舍去，五前为奇要进一) */
        System.out.println(BigDecimal.valueOf(3.454).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        System.out.println(BigDecimal.valueOf(3.456).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        System.out.println(BigDecimal.valueOf(3.4551).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        System.out.println(BigDecimal.valueOf(3.4550).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        System.out.println(BigDecimal.valueOf(3.4450).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
    }

}
