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
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 四舍五入测试
 *
 * @author mingle
 */
public class RoundTest {
    public static void main(String[] args) {
        double i = 2, j = 2.1, k = 2.5, m = 2.9;
        System.out.println("舍掉小数取整:Math.floor(2)=" + (int) Math.floor(i));
        System.out.println("舍掉小数取整:Math.floor(2.1)=" + (int) Math.floor(j));
        System.out.println("舍掉小数取整:Math.floor(2.5)=" + (int) Math.floor(k));
        System.out.println("舍掉小数取整:Math.floor(2.9)=" + (int) Math.floor(m));

        /** 这段被注释的代码不能正确的实现四舍五入取整 */
        System.out.println("四舍五入取整:Math.rint(2)=" + (int) Math.rint(i));
        System.out.println("四舍五入取整:Math.rint(2.1)=" + (int) Math.rint(j));
        System.out.println("四舍五入取整:Math.rint(2.5)=" + (int) Math.rint(k));
        System.out.println("四舍五入取整:Math.rint(2.9)=" + (int) Math.rint(m));
        System.out.println("四舍五入取整:(2)=" + new DecimalFormat("0").format(i));
        System.out.println("四舍五入取整:(2.1)=" + new DecimalFormat("0").format(i));
        System.out.println("四舍五入取整:(2.5)=" + new DecimalFormat("0").format(i));
        System.out.println("四舍五入取整:(2.9)=" + new DecimalFormat("0").format(i));

        System.out.println("四舍五入取整:(2)=" + new BigDecimal("2").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(2.1)=" + new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(2.5)=" + new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(2.9)=" + new BigDecimal("2.9").setScale(0, BigDecimal.ROUND_HALF_UP));

        System.out.println("凑整:Math.ceil(2)=" + (int) Math.ceil(i));
        System.out.println("凑整:Math.ceil(2.1)=" + (int) Math.ceil(j));
        System.out.println("凑整:Math.ceil(2.5)=" + (int) Math.ceil(k));
        System.out.println("凑整:Math.ceil(2.9)=" + (int) Math.ceil(m));

        System.out.println("舍掉小数取整:Math.floor(-2)=" + (int) Math.floor(-i));
        System.out.println("舍掉小数取整:Math.floor(-2.1)=" + (int) Math.floor(-j));
        System.out.println("舍掉小数取整:Math.floor(-2.5)=" + (int) Math.floor(-k));
        System.out.println("舍掉小数取整:Math.floor(-2.9)=" + (int) Math.floor(-m));
        System.out.println("四舍五入取整:(-2)=" + new BigDecimal("-2").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(-2.1)=" + new BigDecimal("-2.1").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(-2.5)=" + new BigDecimal("-2.5").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(-2.9)=" + new BigDecimal("-2.9").setScale(0, BigDecimal.ROUND_HALF_UP));

        System.out.println("凑整:Math.ceil(-2)=" + (int) Math.ceil(-i));
        System.out.println("凑整:Math.ceil(-2.1)=" + (int) Math.ceil(-j));
        System.out.println("凑整:Math.ceil(-2.5)=" + (int) Math.ceil(-k));
        System.out.println("凑整:Math.ceil(-2.9)=" + (int) Math.ceil(-m));

        System.out.println(String.format("%.2f", 5.457));

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        System.out.println(numberFormat.format(5.457));
    }
}
