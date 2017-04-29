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

package org.mingle.orange.java.speciality;

/**
 * this.toString()造成递归
 *
 * @author mingle
 */
public class ThisToString {

    @Override
    public String toString() {
        return "ThisToString [toString()=" + this.toString() + "]";
//        return "ThisToString [toString()=" + super.toString() + "]";
    }

    public static void main(String[] args) {
        System.out.println(new ThisToString());        // 递归错误
    }
}
