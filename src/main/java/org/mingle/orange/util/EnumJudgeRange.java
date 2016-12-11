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

package org.mingle.orange.util;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author mingle
 */
public class EnumJudgeRange {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Range.getList().get(2).getNumber());
    }

}

enum Range {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
    private int number;
    private static List<Range> list;
    
    static {
        list = Arrays.asList(Range.values());
    }
    
    private Range(int number) {
        this.number = number;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return the list
     */
    public static List<Range> getList() {
        return list;
    }
}
