/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

/**
 * 枚举类型
 * 
 * @since 1.0
 * @author Mingle
 */
public class Enum {
    /**
     * 基本运算
     */
    enum Operation {
        PLUS("+") {
            @Override
            double apply(double x, double y) {
                return x + y;
            }
        },
        MINUS("-") {
            @Override
            double apply(double x, double y) {
                return x - y;
            }
        },
        TIMES("*") {
            @Override
            double apply(double x, double y) {
                return x * y;
            }
        },
        DIVIDE("/") {
            @Override
            double apply(double x, double y) {
                return x / y;
            }
        };
        
        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }
        
        @Override public String toString() {
            return symbol;
        }
        
        abstract double apply(double x, double y);
    }
    
    /**
     * 策略枚举
     */
    enum PayrollDay {
        MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WEDNESDAY(PayType.WEEKDAY),
        THURSDAY(PayType.WEEKDAY), FRIDAY(PayType.WEEKDAY),
        SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
        
        private final PayType payType;
        
        PayrollDay(PayType payType) {
            this.payType = payType;
        }

        double pay(double hoursWorked, double payRate) {
            return payType.pay(hoursWorked, payRate);
        }

        /**
         * 策略枚举类型
         */
        private enum PayType {
            WEEKDAY {
                @Override
                double overtimePay(double hours, double payRate) {
                    return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
                }
            },
            WEEKEND {
                @Override
                double overtimePay(double hours, double payRate) {
                    return hours * payRate / 2;
                }
            };
            private static final int HOURS_PER_SHIFT = 8;
            
            abstract double overtimePay(double hours, double payRate);
            
            double pay(double hoursWorked, double payRate) {
                double basePay = hoursWorked * payRate;
                return basePay + overtimePay(hoursWorked, payRate);
            }
        }
    }
}
