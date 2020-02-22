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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author mingle
 */
public class CompositeEquipment extends Equipment {
    private List<Equipment> equipments = new ArrayList<>();

    public CompositeEquipment(String name) {
        super(name);
    }

    @Override public Watt power() {
        return null;
    }

    @Override public double netPrice() {
        Iterator<Equipment> iterator = iterator();
        double total = 0;

        while (iterator.hasNext())
            total += iterator.next().netPrice();

        return total;
    }

    @Override public double discountPrice() {
        return 0;
    }

    @Override public void add(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override public void remove(Equipment equipment) {
        equipments.remove(equipment);
    }

    @Override public Iterator<Equipment> iterator() {
        return equipments.iterator();
    }

    @Override public void accept(EquipmentVisitor visitor) {
        Iterator<Equipment> it = iterator();
        while (it.hasNext())
            it.next().accept(visitor);
    }

}
