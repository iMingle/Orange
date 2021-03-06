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

package org.orange.designpattern.structural.composite;

import org.orange.designpattern.behavioral.visitor.EquipmentVisitor;

import java.util.Collections;
import java.util.Iterator;

/**
 * 设备
 *
 * @author mingle
 */
public abstract class Equipment {
    private final String name;

    public Equipment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Watt power();

    public abstract double netPrice();

    public abstract double discountPrice();

    /**
     * 访问者模式
     *
     * @param visitor
     */
    public abstract void accept(EquipmentVisitor visitor);

    public void add(Equipment equipment) {
        throw new UnsupportedOperationException();
    }

    public void remove(Equipment equipment) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked") public Iterator<Equipment> iterator() {
        return Collections.EMPTY_LIST.iterator();
    }

    public void inventory() {
    }
}
