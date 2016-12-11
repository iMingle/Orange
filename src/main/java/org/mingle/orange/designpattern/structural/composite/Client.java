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

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class Client {

    public static void main(String[] args) {
        Cabinet cabinet = new Cabinet("PC Cabinet");
        Chassis chassis = new Chassis("PC Chassis");
        cabinet.add(chassis);
        
        Bus bus = new Bus("MCA Bus");
        bus.add(new Card("16Mbs Token Ring"));
        
        chassis.add(bus);
        chassis.add(new FloppyDisk("3.5in Floppy"));
        
        System.out.println(chassis.netPrice());
    }

}
