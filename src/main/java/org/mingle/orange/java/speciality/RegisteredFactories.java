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

package org.mingle.orange.java.speciality;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 注册工厂，将对象的创建工作交给类自己去完成
 *
 * @author mingle
 */
public class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}

interface Factory<T> {
    T create();
}

class Part {
    public String toString() {
        return getClass().getSimpleName();
    }
    
    static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();
    
    static {
        partFactories.add(new FuelFilter.InnerFactory());
        partFactories.add(new AirFilter.InnerFactory());
        partFactories.add(new CabinAirFilter.InnerFactory());
        partFactories.add(new OilFilter.InnerFactory());
        partFactories.add(new FanBelt.InnerFactory());
        partFactories.add(new GeneratorBelt.InnerFactory());
        partFactories.add(new PowerSteeringBelt.InnerFactory());
    }
    
    private static Random rand = new Random(47);
    
    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        
        return partFactories.get(n).create();
    }
}

class Filter extends Part {}

class FuelFilter extends Filter {
    public static class InnerFactory implements Factory<FuelFilter> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Factory#create()
         */
        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
        
    }
}

class AirFilter extends Filter {
    public static class InnerFactory implements Factory<AirFilter> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Factory#create()
         */
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
        
    }
}

class CabinAirFilter extends Filter {
    public static class InnerFactory implements Factory<CabinAirFilter> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Factory#create()
         */
        @Override
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
        
    }
}

class OilFilter extends Filter {
    public static class InnerFactory implements Factory<OilFilter> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Factory#create()
         */
        @Override
        public OilFilter create() {
            return new OilFilter();
        }
        
    }
}

class Belt extends Part {}

class FanBelt extends Belt {
    public static class InnerFactory implements Factory<FanBelt> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Factory#create()
         */
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
        
    }
}

class GeneratorBelt extends Belt {
    public static class InnerFactory implements Factory<GeneratorBelt> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Factory#create()
         */
        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
        
    }
}

class PowerSteeringBelt extends Belt {
    public static class InnerFactory implements Factory<PowerSteeringBelt> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Factory#create()
         */
        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
        
    }
}
