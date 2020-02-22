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

package org.orange.arithmetic.base;

public class SmartDate {
    
    @SuppressWarnings("unused")
    private static final int[][] Month =
        {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 
         {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
    private final int month;
    private final int day;
    private final int year;
    
    public SmartDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day;
        result = prime * result + month;
        result = prime * result + year;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SmartDate other = (SmartDate) obj;
        if (day != other.day)
            return false;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Datable date = (Datable) new SmartDate(1, 26, 1988);
        
        System.out.println(date.getClass());
    }

}
