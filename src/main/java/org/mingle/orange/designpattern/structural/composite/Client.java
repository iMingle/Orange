/**
 * Copyright (c) 2016, Mingle. All rights reserved.
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
