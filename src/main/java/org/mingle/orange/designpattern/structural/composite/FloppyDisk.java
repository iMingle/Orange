/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.designpattern.structural.composite;

import org.mingle.orange.designpattern.behavioral.visitor.EquipmentVisitor;

/**
 * 磁盘
 * 
 * @author Mingle
 */
public class FloppyDisk extends Equipment {

    public FloppyDisk(String name) {
        super(name);
    }

    @Override public Watt power() {
        return null;
    }

    @Override public double netPrice() {
        return 30;
    }

    @Override public double discountPrice() {
        return 3;
    }

    @Override public void accept(EquipmentVisitor visitor) {
        visitor.visitFloppyDisk(this);
    }
}
