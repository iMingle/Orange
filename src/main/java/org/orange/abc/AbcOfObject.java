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

package org.orange.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mingle
 */
public class AbcOfObject {
    public static void main(String[] args) {
        new B(); // 1314

        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Banana());
        fruits.add(new Fruit());

//        f(fruits);

        People boy = new Boy();
        Fruit apple = new Apple();
        Fruit banana = new Banana();
        boy.eat(apple);
        boy.eat(banana);
    }

    static class A {
        A() {
            System.out.print(1);
        }

        A(String s) {
            System.out.print(2);
        }
    }

    static class B extends A {
        B() {
            System.out.print(3);
            new B("123");
        }

        B(String s) {
            System.out.print(4);
        }
    }

    static class Fruit {

    }

    static class Apple extends Fruit {

    }

    static class Banana extends Fruit {

    }

    static class People {
        public void eat(Fruit f) {
            System.out.println("People eat fruit");
        }

        public void eat(Apple f) {
            System.out.println("People eat apple");
        }

        public void eat(Banana f) {
            System.out.println("People eat banana");
        }
    }

    static class Boy extends People {
        @Override
        public void eat(Fruit f) {
            System.out.println("Boy eat fruit");
        }

        public void eat(Apple f) {
            System.out.println("Boy eat apple");
        }

        public void eat(Banana f) {
            System.out.println("Boy eat banana");
        }
    }
}
