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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;

/**
 * Class测试
 *
 * @author mingle
 */
public class ClassTest {

    /**
     * @param args
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    @SuppressWarnings({ "rawtypes", "unused" })
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Integer i = null;
        try {
            // Integer没有默认构造方法
            i = Integer.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(i);
        System.out.println(int.class);

        Class intClass = int.class;
        intClass = double.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class;
//        Class<Number> genericNumberClass = int.class; // error
        Class<? extends Number> bounded = int.class;
        Class<?> intClazz = int.class;
        intClazz = double.class;
        Class<? super Integer> up = genericIntClass.getSuperclass();
        Object obj = up.newInstance();
    }

}

@SuppressWarnings("unused")
class Individual {
    private String name;

    public Individual() {}
    
    public Individual(String name) {
        super();
        this.name = name;
    }
    
}

class Person extends Individual {

    public Person(String name) {
        super(name);
    }
    
}

class Pet extends Individual {
    public Pet() {
        super();
    }
    
    public Pet(String name) {
        super(name);
    }
    
}

class Dog extends Pet {
    public Dog() {
        super();
    }
    
    public Dog(String name) {
        super(name);
    }
}

class Mutt extends Dog {
    public Mutt() {
        super();
    }
    
    public Mutt(String name) {
        super(name);
    }
}

class Pug extends Dog {
    public Pug() {
        super();
    }
    
    public Pug(String name) {
        super(name);
    }
}

class Cat extends Pet {
    public Cat() {
        super();
    }
    
    public Cat(String name) {
        super(name);
    }
}

class EgyptianMau extends Cat {
    public EgyptianMau() {
        super();
    }
    
    public EgyptianMau(String name) {
        super(name);
    }
}

class Manx extends Cat {
    public Manx() {
        super();
    }
    
    public Manx(String name) {
        super(name);
    }
}

class Cymric extends Manx {
    public Cymric() {
        super();
    }
    
    public Cymric(String name) {
        super(name);
    }
}

class Rodent extends Pet {
    public Rodent() {
        super();
    }
    
    public Rodent(String name) {
        super(name);
    }
}

class Rat extends Rodent {
    public Rat() {
        super();
    }
    
    public Rat(String name) {
        super(name);
    }
}

class Mouse extends Rodent {
    public Mouse() {
        super();
    }
    
    public Mouse(String name) {
        super(name);
    }
}

class Hamster extends Rodent {
    public Hamster() {
        super();
    }
    
    public Hamster(String name) {
        super(name);
    }
}

/**
 * 抽象的宠物生成器
 */
abstract class PetCreator {
    private Random rand = new Random(47);
    
    public abstract List<Class<? extends Pet>> types();
    
    public Pet randompet() {
        int n = rand.nextInt(types().size());
        
        try {
            return types().get(n).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = randompet();
        }
        return result;
    }
    
    public ArrayList<Pet> arrayList(int size) {
        ArrayList<Pet> result = new ArrayList<Pet>();
        Collections.addAll(result, createArray(size));
        return result;
    }
}

/**
 * 宠物生成器
 */
class Pets {
    public static final PetCreator creator = new LiteralPetCreator();
    
    public static Pet randomPet() {
        return creator.randompet();
    }
    
    public static Pet[] createArray(int size) {
        return creator.createArray(size);
    }
    
    public static ArrayList<Pet> arrayList(int size) {
        return creator.arrayList(size);
    }
}

/**
 * Class.forName()实现
 */
class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
    
    private static String[] typeNames = {
        "org.mingle.orange.java.speciality.Mutt",
        "org.mingle.orange.java.speciality.Pug",
        "org.mingle.orange.java.speciality.EgyptianMau",
        "org.mingle.orange.java.speciality.Manx",
        "org.mingle.orange.java.speciality.Cymric",
        "org.mingle.orange.java.speciality.Rat",
        "org.mingle.orange.java.speciality.Mouse",
        "org.mingle.orange.java.speciality.Hamster"
    };
    
    @SuppressWarnings("unchecked")
    private static void loader() {
        for (String name : typeNames) {
            try {
                types.add((Class<? extends Pet>) Class.forName(name));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    static {
        loader();
    }

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.PetCreator#types()
     */
    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
    
}

/**
 * 类字面常量实现
 */
class LiteralPetCreator extends PetCreator {
    public static final List<Class<? extends Pet>> allTypes = Collections.unmodifiableList(Arrays.asList(
            Pet.class, Dog.class, Cat.class, Rodent.class, Mutt.class, Pug.class,
            EgyptianMau.class, Manx.class, Cymric.class, Rat.class, Mouse.class, Hamster.class));
    
    private static final List<Class<? extends Pet>> types = allTypes.subList(allTypes.indexOf(Mutt.class), allTypes.size());
    
    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.PetCreator#types()
     */
    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
    
    public static void main(String[] args) {
        System.out.println(types);
    }
}

/**
 * 宠物计数器
 */
class PetCount {
    static class PetCounter extends HashMap<String, Integer> {
        private static final long serialVersionUID = 4928891489805520664L;

        public void count(String type) {
            Integer quantity = get(type);
            if (quantity == null) {
                put(type, 1);
            } else {
                put(type, quantity + 1);
            }
        }
    }
    
    public static void CountPets(PetCreator creator) {
        PetCounter counter = new PetCounter();
        for (Pet pet : creator.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + ", ");
            if (pet instanceof Pet) {
                counter.count("Pet");
            }
            if (pet instanceof Dog) {
                counter.count("Dog");
            }
            if (pet instanceof Mutt) {
                counter.count("Mutt");
            }
            if (pet instanceof Pug) {
                counter.count("Pug");
            }
            if (pet instanceof Cat) {
                counter.count("Cat");
            }
            if (pet instanceof Manx) {
                counter.count("Manx");
            }
            if (pet instanceof Cymric) {
                counter.count("Cymric");
            }
            if (pet instanceof Rodent) {
                counter.count("Rodent");
            }
            if (pet instanceof Mouse) {
                counter.count("Mouse");
            }
            if (pet instanceof Hamster) {
                counter.count("Hamster");
            }
        }
        System.out.println();
        System.out.println(counter);
    }
    
    public static void main(String[] args) {
        CountPets(new ForNameCreator());
    }
}

/**
 * 宠物计数器
 */
class PetCount2 {
    public static void main(String[] args) {
        PetCount.CountPets(Pets.creator);
    }
}

class MapData {
    /**
     * 将list中的数据作为key添加到map中，初始化为value值
     * @param keys
     * @param value
     * @return
     */
    public static <K, V> Map<K, V> map(List<K> keys, V value) {
        Map<K, V> map = Maps.newLinkedHashMap();
        for (K k : keys) {
            map.put(k, value);
        }
        return map;
    }
}

/**
 * 宠物计数器
 */
class PetCount3 {
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
        private static final long serialVersionUID = -3497218729875804206L;

        public PetCounter() {
            super(MapData.map(LiteralPetCreator.allTypes, 0));
        }
        
        public void count(Pet pet) {
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                if (pair.getKey().isInstance(pet)) {
                    put(pair.getKey(), pair.getValue() + 1);
                }
            }
        }
        
        public String toString() {
            StringBuilder result = new StringBuilder("{");
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                result.append(pair.getKey().getSimpleName()).append("=").append(pair.getValue());
                result.append(", ");
            }
            result.delete(result.length() - 2, result.length());
            result.append("}");
            return result.toString();
        }
    }
    
    public static void main(String[] args) {
        PetCounter counter = new PetCounter();
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + ", ");
            counter.count(pet);
        }
        System.out.println();
        System.out.println(counter);
    }
}

/**
 * 递归宠物计数器
 */
class TypeCounter extends HashMap<Class<?>, Integer> {
    private static final long serialVersionUID = 8707429620946138292L;
    
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        super();
        this.baseType = baseType;
    }
    
    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type)) {
            throw new RuntimeException(obj + " incorrect type:" + type + ", should be type of subtype of " + baseType);
        }
        countClass(type);
    }
    
    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> superClass = type.getSuperclass();
        if (superClass != null && baseType.isAssignableFrom(superClass)) {
            countClass(superClass);
        }
    }
    
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName()).append("=").append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("}");
        return result.toString();
    }
    
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + ", ");
            counter.count(pet);
        }
        System.out.println();
        System.out.println(counter);
    }
}

class Base {}

class Derived extends Base {}

/**
 * 测试instanceof和Class的等价性
 */
class FamilyVsExactType {
    static void test(Object o) {
        System.out.println("Testing o of type " + o.getClass());
        System.out.println("o instanceof Base " + (o instanceof Base));
        System.out.println("o instanceof Derived " + (o instanceof Derived));
        System.out.println("Base.isInstance(o) " + Base.class.isInstance(o));
        System.out.println("Derived.isInstance(o) " + Derived.class.isInstance(o));
        System.out.println("o.getClass() == Base.class " + (o.getClass() == Base.class));
        System.out.println("o.getClass() == Derived.class " + (o.getClass() == Derived.class));
        System.out.println("o.getClass().equals(Base.class) " + (o.getClass().equals(Base.class)));
        System.out.println("o.getClass().equals(Derived.class) " + (o.getClass().equals(Derived.class)));
    }
    
    public static void main(String[] args) {
        test(new Base());
        test(new Derived());
/*        
Output:
        Testing o of type class org.mingle.orange.java.speciality.Base
        o instanceof Base true
        o instanceof Derived false
        Base.isInstance(o) true
        Derived.isInstance(o) false
        o.getClass() == Base.class true
        o.getClass() == Derived.class false
        o.getClass().equals(Base.class) true
        o.getClass().equals(Derived.class) false
        Testing o of type class org.mingle.orange.java.speciality.Derived
        o instanceof Base true
        o instanceof Derived true
        Base.isInstance(o) true
        Derived.isInstance(o) true
        o.getClass() == Base.class false
        o.getClass() == Derived.class true
        o.getClass().equals(Base.class) false
        o.getClass().equals(Derived.class) true
*/
    }
}
