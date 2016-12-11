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

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 容器深入
 *
 * @author mingle
 */
public class CollectionTest {

    public static void main(String[] args) {
        
    }

}

class StringAddress {
    private String s;

    public StringAddress(String s) {
        super();
        this.s = s;
    }
    
    public String toString() {
        return super.toString() + " " + s;
    }
}

/**
 * 填充List
 */
class FillingLists {
    public static void main(String[] args) {
        List<StringAddress> list = new ArrayList<StringAddress>(Collections.nCopies(4, new StringAddress("Hello")));
        System.out.println(list);
        Collections.fill(list, new StringAddress("World!"));
        System.out.println(list);
    }
}

/**
 * 容器
 */
class CollectionData<T> extends ArrayList<T> {
    private static final long serialVersionUID = -3840733009729211792L;
    
    public CollectionData(Generator<T> gen, int quantity) {
        for (int i = 0; i < quantity; i++) {
            add(gen.next());
        }
    }
    
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
        return new CollectionData<T>(gen, quantity);
    }
}

/**
 * 产生政府
 */
class Government implements Generator<String> {
    String[] foundation = ("strange women lying in ponds distributing swords is no"
            + " basis for a system of government").split(" ");
    private int index;

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.Generator#next()
     */
    @Override
    public String next() {
        return foundation[index++];
    }
    
}

class CollectionDataTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>(new CollectionData<String>(new Government(), 15));
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
        
        System.out.println(new ArrayList<String>(CollectionData.list(new RandomGenerator.String(9), 10)));
        System.out.println(new ArrayList<Integer>(CollectionData.list(new RandomGenerator.Integer(), 10)));
    }
}

/**
 * 键值对,只读的数据传输对象
 */
class Pair<K,V> {
    public final K key;
    public final V value;
    
    /**
     * @param key
     * @param value
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Map适配器
 */
class MappedData<K,V> extends LinkedHashMap<K,V> {

    private static final long serialVersionUID = 5288213832194900901L;
    
    public MappedData(Generator<Pair<K,V>> gen, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Pair<K,V> p = gen.next();
            put(p.key, p.value);
        }
    }
    
    public MappedData(Generator<K> genK, Generator<V> genV, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(genK.next(), genV.next());
        }
    }
    
    public MappedData(Generator<K> genK, V value, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(genK.next(), value);
        }
    }
    
    public MappedData(Iterable<K> genK, Generator<V> genV) {
        for (K k : genK) {
            put(k, genV.next());
        }
    }
    
    public MappedData(Iterable<K> genK, V value) {
        for (K k : genK) {
            put(k, value);
        }
    }
    
    public static <K,V> MappedData<K,V> map(Generator<Pair<K,V>> gen, int quantity) {
        return new MappedData<K,V>(gen, quantity);
    }
    
    public static <K,V> MappedData<K,V> map(Generator<K> genK, Generator<V> genV, int quantity) {
        return new MappedData<K,V>(genK, genV, quantity);
    }
    
    public static <K,V> MappedData<K,V> map(Generator<K> genK, V value, int quantity) {
        return new MappedData<K,V>(genK, value, quantity);
    }
    
    public static <K,V> MappedData<K,V> map(Iterable<K> genK, Generator<V> genV) {
        return new MappedData<K,V>(genK, genV);
    }
    
    public static <K,V> MappedData<K,V> map(Iterable<K> genK, V value) {
        return new MappedData<K,V>(genK, value);
    }
}

/**
 * 数字&字母
 */
class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {
    private int size = 9;
    private int number = 1;
    private char letter = 'A';

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.Generator#next()
     */
    @Override
    public Pair<Integer, String> next() {
        return new Pair<Integer, String>(number++, "" + letter++);
    }
    
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            
            @Override
            public Integer next() {
                return number++;
            }
            
            @Override
            public boolean hasNext() {
                return number < size;
            }
        };
    }
}

class MappedDataTest {
    public static void main(String[] args) {
        System.out.println(MappedData.map(new Letters(), 11));
        System.out.println(MappedData.map(new CountingGenerator.Character(), new RandomGenerator.String(3), 8));
        System.out.println(MappedData.map(new CountingGenerator.Character(), "Value", 6));
        System.out.println(MappedData.map(new Letters(), new RandomGenerator.String(3)));
        System.out.println(MappedData.map(new Letters(), "Pop"));
    }
}

/**
 * 国家&首都
 */
class Countries {
    public static final String[][] DATA = {
            // Africa
            { "ALGERIA", "Algiers" },
            { "ANGOLA", "Luanda" },
            { "BENIN", "Porto-Novo" },
            { "BOTSWANA", "Gaberone" },
            { "BURKINA FASO", "Ouagadougou" },
            { "BURUNDI", "Bujumbura" },
            { "CAMEROON", "Yaounde" },
            { "CAPE VERDE", "Praia" },
            { "CENTRAL AFRICAN REPUBLIC", "Bangui" },
            { "CHAD", "N'djamena" },
            { "COMOROS", "Moroni" },
            { "CONGO", "Brazzaville" },
            { "DJIBOUTI", "Dijibouti" },
            { "EGYPT", "Cairo" },
            { "EQUATORIAL GUINEA", "Malabo" },
            { "ERITREA", "Asmara" },
            { "ETHIOPIA", "Addis Ababa" },
            { "GABON", "Libreville" },
            { "THE GAMBIA", "Banjul" },
            { "GHANA", "Accra" },
            { "GUINEA", "Conakry" },
            { "BISSAU", "Bissau" },
            { "COTE D'IVOIR (IVORY COAST)", "Yamoussoukro" },
            { "KENYA", "Nairobi" },
            { "LESOTHO", "Maseru" },
            { "LIBERIA", "Monrovia" },
            { "LIBYA", "Tripoli" },
            { "MADAGASCAR", "Antananarivo" },
            { "MALAWI", "Lilongwe" },
            { "MALI", "Bamako" },
            { "MAURITANIA", "Nouakchott" },
            { "MAURITIUS", "Port Louis" },
            { "MOROCCO", "Rabat" },
            { "MOZAMBIQUE", "Maputo" },
            { "NAMIBIA", "Windhoek" },
            { "NIGER", "Niamey" },
            { "NIGERIA", "Abuja" },
            { "RWANDA", "Kigali" },
            { "SAO TOME E PRINCIPE", "Sao Tome" },
            { "SENEGAL", "Dakar" },
            { "SEYCHELLES", "Victoria" },
            { "SIERRA LEONE", "Freetown" },
            { "SOMALIA", "Mogadishu" },
            { "SOUTH AFRICA", "Pretoria/Cape Town" },
            { "SUDAN", "Khartoum" },
            { "SWAZILAND", "Mbabane" },
            { "TANZANIA", "Dodoma" },
            { "TOGO", "Lome" },
            { "TUNISIA", "Tunis" },
            { "UGANDA", "Kampala" },
            { "DEMOCRATIC REPUBLIC OF THE CONGO (ZAIRE)", "Kinshasa" },
            { "ZAMBIA", "Lusaka" },
            { "ZIMBABWE", "Harare" },
            // Asia
            { "AFGHANISTAN", "Kabul" },
            { "BAHRAIN", "Manama" },
            { "BANGLADESH", "Dhaka" },
            { "BHUTAN", "Thimphu" },
            { "BRUNEI", "Bandar Seri Begawan" },
            { "CAMBODIA", "Phnom Penh" },
            { "CHINA", "Beijing" },
            { "CYPRUS", "Nicosia" },
            { "INDIA", "New Delhi" },
            { "INDONESIA", "Jakarta" },
            { "IRAN", "Tehran" },
            { "IRAQ", "Baghdad" },
            { "ISRAEL", "Jerusalem" },
            { "JAPAN", "Tokyo" },
            { "JORDAN", "Amman" },
            { "KUWAIT", "Kuwait City" },
            { "LAOS", "Vientiane" },
            { "LEBANON", "Beirut" },
            { "MALAYSIA", "Kuala Lumpur" },
            { "THE MALDIVES", "Male" },
            { "MONGOLIA", "Ulan Bator" },
            { "MYANMAR (BURMA)", "Rangoon" },
            { "NEPAL", "Katmandu" },
            { "NORTH KOREA", "P'yongyang" },
            { "OMAN", "Muscat" },
            { "PAKISTAN", "Islamabad" },
            { "PHILIPPINES", "Manila" },
            { "QATAR", "Doha" },
            { "SAUDI ARABIA", "Riyadh" },
            { "SINGAPORE", "Singapore" },
            { "SOUTH KOREA", "Seoul" },
            { "SRI LANKA", "Colombo" },
            { "SYRIA", "Damascus" },
            { "TAIWAN (REPUBLIC OF CHINA)", "Taipei" },
            { "THAILAND", "Bangkok" },
            { "TURKEY", "Ankara" },
            { "UNITED ARAB EMIRATES", "Abu Dhabi" },
            { "VIETNAM", "Hanoi" },
            { "YEMEN", "Sana'a" },
            // Australia and Oceania
            { "AUSTRALIA", "Canberra" },
            { "FIJI", "Suva" },
            { "KIRIBATI", "Bairiki" },
            { "MARSHALL ISLANDS", "Dalap-Uliga-Darrit" },
            { "MICRONESIA", "Palikir" },
            { "NAURU", "Yaren" },
            { "NEW ZEALAND", "Wellington" },
            { "PALAU", "Koror" },
            { "PAPUA NEW GUINEA", "Port Moresby" },
            { "SOLOMON ISLANDS", "Honaira" },
            { "TONGA", "Nuku'alofa" },
            { "TUVALU", "Fongafale" },
            { "VANUATU", "< Port-Vila" },
            { "WESTERN SAMOA", "Apia" },
            // Eastern Europe and former USSR
            { "ARMENIA", "Yerevan" },
            { "AZERBAIJAN", "Baku" },
            { "BELARUS (BYELORUSSIA)", "Minsk" },
            { "BULGARIA", "Sofia" },
            { "GEORGIA", "Tbilisi" },
            { "KAZAKSTAN", "Almaty" },
            { "KYRGYZSTAN", "Alma-Ata" },
            { "MOLDOVA", "Chisinau" },
            { "RUSSIA", "Moscow" },
            { "TAJIKISTAN", "Dushanbe" },
            { "TURKMENISTAN", "Ashkabad" },
            { "UKRAINE", "Kyiv" },
            { "UZBEKISTAN", "Tashkent" },
            // Europe
            { "ALBANIA", "Tirana" }, { "ANDORRA", "Andorra la Vella" },
            { "AUSTRIA", "Vienna" }, { "BELGIUM", "Brussels" },
            { "BOSNIA", "-" },
            { "HERZEGOVINA", "Sarajevo" },
            { "CROATIA", "Zagreb" },
            { "CZECH REPUBLIC", "Prague" },
            { "DENMARK", "Copenhagen" },
            { "ESTONIA", "Tallinn" },
            { "FINLAND", "Helsinki" },
            { "FRANCE", "Paris" },
            { "GERMANY", "Berlin" },
            { "GREECE", "Athens" },
            { "HUNGARY", "Budapest" },
            { "ICELAND", "Reykjavik" },
            { "IRELAND", "Dublin" },
            { "ITALY", "Rome" },
            { "LATVIA", "Riga" },
            { "LIECHTENSTEIN", "Vaduz" },
            { "LITHUANIA", "Vilnius" },
            { "LUXEMBOURG", "Luxembourg" },
            { "MACEDONIA", "Skopje" },
            { "MALTA", "Valletta" },
            { "MONACO", "Monaco" },
            { "MONTENEGRO", "Podgorica" },
            { "THE NETHERLANDS", "Amsterdam" },
            { "NORWAY", "Oslo" },
            { "POLAND", "Warsaw" },
            { "PORTUGAL", "Lisbon" },
            { "ROMANIA", "Bucharest" },
            { "SAN MARINO", "San Marino" },
            { "SERBIA", "Belgrade" },
            { "SLOVAKIA", "Bratislava" },
            { "SLOVENIA", "Ljuijana" },
            { "SPAIN", "Madrid" },
            { "SWEDEN", "Stockholm" },
            { "SWITZERLAND", "Berne" },
            { "UNITED KINGDOM", "London" },
            { "VATICAN CITY", "---" },
            // North and Central America
            { "ANTIGUA AND BARBUDA", "Saint John's" }, { "BAHAMAS", "Nassau" },
            { "BARBADOS", "Bridgetown" }, { "BELIZE", "Belmopan" },
            { "CANADA", "Ottawa" }, { "COSTA RICA", "San Jose" },
            { "CUBA", "Havana" }, { "DOMINICA", "Roseau" },
            { "DOMINICAN REPUBLIC", "Santo Domingo" },
            { "EL SALVADOR", "San Salvador" },
            { "GRENADA", "Saint George's" },
            { "GUATEMALA", "Guatemala City" },
            { "HAITI", "Port-au-Prince" },
            { "HONDURAS", "Tegucigalpa" },
            { "JAMAICA", "Kingston" },
            { "MEXICO", "Mexico City" },
            { "NICARAGUA", "Managua" },
            { "PANAMA", "Panama City" },
            { "ST. KITTS", "-" },
            { "NEVIS", "Basseterre" },
            { "ST. LUCIA", "Castries" },
            { "ST. VINCENT AND THE GRENADINES", "Kingstown" },
            { "UNITED STATES OF AMERICA", "Washington, D.C." },
            // South America
            { "ARGENTINA", "Buenos Aires" },
            { "BOLIVIA", "Sucre (legal)/La Paz(administrative)" },
            { "BRAZIL", "Brasilia" }, { "CHILE", "Santiago" },
            { "COLOMBIA", "Bogota" }, { "ECUADOR", "Quito" },
            { "GUYANA", "Georgetown" }, { "PARAGUAY", "Asuncion" },
            { "PERU", "Lima" }, { "SURINAME", "Paramaribo" },
            { "TRINIDAD AND TOBAGO", "Port of Spain" },
            { "URUGUAY", "Montevideo" }, { "VENEZUELA", "Caracas" }, };
    
    private static class FlyweightMap extends AbstractMap<String, String> {
        private static class Entry implements Map.Entry<String, String> {
            // 只存储索引,不是实际的键和值,享元模式
            int index;

            public Entry(int index) {
                this.index = index;
            }

            /* (non-Javadoc)
             * @see java.lang.Object#equals(java.lang.Object)
             */
            @Override
            public boolean equals(Object obj) {
                return DATA[index][0].equals(obj);
            }

            /* (non-Javadoc)
             * @see java.util.Map.Entry#getKey()
             */
            @Override
            public String getKey() {
                return DATA[index][0];
            }

            /* (non-Javadoc)
             * @see java.util.Map.Entry#getValue()
             */
            @Override
            public String getValue() {
                return DATA[index][1];
            }

            /* (non-Javadoc)
             * @see java.util.Map.Entry#setValue(java.lang.Object)
             */
            @Override
            public String setValue(String value) {
                throw new UnsupportedOperationException();
            }

            /* (non-Javadoc)
             * @see java.lang.Object#hashCode()
             */
            @Override
            public int hashCode() {
                return DATA[index][0].hashCode();
            }
            
        }

        static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
            private int size;

            public EntrySet(int size) {
                if (size < 0)
                    this.size = 0;
                else if (size > DATA.length)
                    this.size = DATA.length;
                else
                    this.size = size;
            }
            
            private class Iter implements Iterator<Map.Entry<String, String>> {
                // 每个迭代器只包含一个Map.Entry,享元模式
                private Entry entry = new Entry(-1);

                /* (non-Javadoc)
                 * @see java.util.Iterator#hasNext()
                 */
                @Override
                public boolean hasNext() {
                    return entry.index < size - 1;
                }

                /* (non-Javadoc)
                 * @see java.util.Iterator#next()
                 */
                @Override
                public java.util.Map.Entry<String, String> next() {
                    entry.index++;
                    return entry;
                }
                
            }
            
            /* (non-Javadoc)
             * @see java.util.AbstractCollection#iterator()
             */
            @Override
            public Iterator<java.util.Map.Entry<String, String>> iterator() {
                return new Iter();
            }

            /* (non-Javadoc)
             * @see java.util.AbstractCollection#size()
             */
            @Override
            public int size() {
                return size;
            }
        }
        
        private static Set<Map.Entry<String, String>> entries = new EntrySet(DATA.length);
        
        /* (non-Javadoc)
         * @see java.util.AbstractMap#entrySet()
         */
        @Override
        public Set<java.util.Map.Entry<String, String>> entrySet() {
            return entries;
        }
        
    }
    
    static Map<String, String> select(final int size) {
        return new FlyweightMap() {
            @Override
            public Set<java.util.Map.Entry<String, String>> entrySet() {
                return new EntrySet(size);
            }
        };
    }
    
    static Map<String, String> map = new FlyweightMap();
    
    public static Map<String, String> capitals() {
        return map;
    }
    
    public static Map<String, String> capitals(int size) {
        return select(size);
    }
    
    static List<String> names = new ArrayList<>(map.keySet());
    
    public static List<String> names() {
        return names;
    }
    
    public static List<String> names(int size) {
        return new ArrayList<>(select(size).keySet());
    }
    
    public static void main(String[] args) {
        System.out.println(capitals(10));
        System.out.println(names(10));
        System.out.println(new HashMap<>(capitals(3)));
        System.out.println(new LinkedHashMap<>(capitals(3)));
        System.out.println(new TreeMap<>(capitals(3)));
        System.out.println(new Hashtable<>(capitals(3)));
        
        System.out.println(new HashSet<>(names(6)));
        System.out.println(new LinkedHashSet<>(names(6)));
        System.out.println(new TreeSet<>(names(6)));
        
        System.out.println(new ArrayList<>(names(6)));
        System.out.println(new LinkedList<>(names(6)));
        
        System.out.println(capitals().get("BRAZIL"));
    }
}

/**
 * 任意尺寸的Integer列表
 */
class CountingIntegerList extends AbstractList<Integer> {
    private int size;

    public CountingIntegerList(int size) {
        super();
        this.size = size < 0 ? 0 : size;
    }

    /* (non-Javadoc)
     * @see java.util.AbstractList#get(int)
     */
    @Override
    public Integer get(int index) {
        return Integer.valueOf(index);
    }

    /* (non-Javadoc)
     * @see java.util.AbstractCollection#size()
     */
    @Override
    public int size() {
        return size;
    }
    
    public static void main(String[] args) {
        System.out.println(new CountingIntegerList(30));
    }
}

/**
 * 任意尺寸的<Integer, String>Map列表
 */
class CountingMapData extends AbstractMap<Integer, String> {
    private int size;
    private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
    
    public CountingMapData(int size) {
        if (size < 0)
            this.size = 0;
        else
            this.size = size;
    }
    
    private static class Entry implements Map.Entry<Integer, String> {
        int index;

        public Entry(int index) {
            this.index = index;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            return Integer.valueOf(index).equals(obj);
        }

        /* (non-Javadoc)
         * @see java.util.Map.Entry#getKey()
         */
        @Override
        public Integer getKey() {
            return index;
        }

        /* (non-Javadoc)
         * @see java.util.Map.Entry#getValue()
         */
        @Override
        public String getValue() {
            return chars[index % chars.length] + Integer.toString(index / chars.length);
        }

        /* (non-Javadoc)
         * @see java.util.Map.Entry#setValue(java.lang.Object)
         */
        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return Integer.valueOf(index).hashCode();
        }
        
    }

    /* (non-Javadoc)
     * @see java.util.AbstractMap#entrySet()
     */
    @Override
    public Set<java.util.Map.Entry<Integer, String>> entrySet() {
        Set<Map.Entry<Integer, String>> entries = new LinkedHashSet<>();
        for (int i = 0; i < size; i++) {
            entries.add(new Entry(i));
        }
        return entries;
    }
    
    public static void main(String[] args) {
        System.out.println(new CountingMapData(60));
    }
}

class CollectionMethods {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.addAll(Countries.names(6));
        c.add("ten");
        c.add("eleven");
        System.out.println(c);
        
        Object[] array = c.toArray();
        System.out.println(array);
        
        String[] str = c.toArray(new String[0]);
        System.out.println(str);
        
        System.out.println(Collections.max(c));
        System.out.println(Collections.min(c));
        
        Collection<String> c2 = new ArrayList<>();
        c2.addAll(Countries.names(6));
        c.addAll(c2);
        System.out.println(c);
        c.remove(Countries.DATA[0][0]);
        System.out.println(c);
        c.remove(Countries.DATA[1][0]);
        System.out.println(c);
        
        c.removeAll(c2);
        System.out.println(c);
        c.addAll(c2);
        System.out.println(c);
        
        System.out.println(c.contains(Countries.DATA[3][0]));
        System.out.println(c.containsAll(c2));
        
        Collection<String> c3 = ((List<String>)c).subList(3, 5);
        c2.retainAll(c3);
        System.out.println(c2);
        
        c2.removeAll(c3);
        System.out.println(c2.isEmpty());
        
        c = new ArrayList<>();
        c.addAll(Countries.names(6));
        System.out.println(c);
        c.clear();
        System.out.println(c);
    }
}

/**
 * Collection可选操作
 */
class Unsupported {
    static void test(String msg, List<String> list) {
        System.out.println("--- " + msg + " ---");
        Collection<String> c = list;
        Collection<String> subList = list.subList(1, 8);
        Collection<String> c2 = new ArrayList<>(subList);
        
        try {
            c.retainAll(c2);
        } catch (Exception e) {
            System.out.println("retainAll(): " + e);
        }
        
        try {
            c.removeAll(c2);
        } catch (Exception e) {
            System.out.println("removeAll(): " + e);
        }
        
        try {
            c.clear();
        } catch (Exception e) {
            System.out.println("clear(): " + e);
        }
        
        try {
            c.add("X");
        } catch (Exception e) {
            System.out.println("add(): " + e);
        }
        
        try {
            c.addAll(c2);
        } catch (Exception e) {
            System.out.println("addAll(): " + e);
        }
        
        try {
            c.remove("C");
        } catch (Exception e) {
            System.out.println("remove(): " + e);
        }
        
        try {
            list.set(0, "X");
        } catch (Exception e) {
            System.out.println("List.set(): " + e);
        }
    }
    
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
        test("Modifiable Copy", new ArrayList<>(list));
        test("Arrays.asList()", list);
        test("unmodifiableList", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}


class Lists {
    @SuppressWarnings("unused")
    private static boolean b;
    @SuppressWarnings("unused")
    private static String s;
    @SuppressWarnings("unused")
    private static int i;
    @SuppressWarnings("unused")
    private static Iterator<String> it;
    @SuppressWarnings("unused")
    private static ListIterator<String> lit;
    
    public static void basicTest(List<String> a) {
        a.add(1, "x");
        a.add("x");
        a.addAll(Countries.names(5));
        a.addAll(3, Countries.names(5));
        b = a.contains("1");
        b = a.containsAll(Countries.names(5));
        s = a.get(1);
        i = a.indexOf("1");
        it = a.iterator();
        lit = a.listIterator();
        lit = a.listIterator(3);
        i = a.lastIndexOf("1");
        a.remove(1);
        a.remove("3");
        a.set(1, "y");
        a.retainAll(Countries.names(5));
        a.removeAll(Countries.names(5));
        i = a.size();
        a.clear();
    }
    
    public static void iterMotion(List<String> a) {
        ListIterator<String> it = a.listIterator();
        b = it.hasNext();
        b = it.hasPrevious();
        s = it.next();
        i = it.nextIndex();
        s = it.previous();
        i = it.previousIndex();
    }
    
    public static void iterManipulation(List<String> a) {
        ListIterator<String> it = a.listIterator();
        System.out.println(a);
        it.add("47");
        System.out.println(a);
        // 必须在调用add后移动一个元素
        System.out.println(it.next());
        System.out.println(a);
        it.remove();
        System.out.println(a);
        // 必须在调用remove后移动一个元素
        System.out.println(it.next());
        it.set("47");
        System.out.println(a);
    }
    
    public static void testVisual(List<String> a) {
        System.out.println(a);
        List<String> b = Countries.names(5);
        System.out.println("b = " + b);
        a.addAll(b);
        a.addAll(b);
        System.out.println(a);
        
        ListIterator<String> x = a.listIterator(a.size() / 2);
        x.add("one");
        System.out.println(a);
        System.out.println(x.next());
        x.remove();
        System.out.println(x.next());
        x.set("47");
        System.out.println(a);
        
        x = a.listIterator(a.size());
        while (x.hasPrevious()) {
            System.out.print(x.previous() + " ");
        }
        System.out.println();
        System.out.println("testVisual finished");
    }
    
    public static void testLinkedList() {
        LinkedList<String> ll = new LinkedList<String>();
        ll.addAll(Countries.names(5));
        System.out.println(ll);
        ll.addFirst("one");
        ll.addFirst("two");
        System.out.println(ll);
        System.out.println(ll.getFirst());
        System.out.println(ll.removeFirst());
        System.out.println(ll.removeFirst());
        System.out.println(ll.removeLast());
        System.out.println(ll);
    }
    
    public static void main(String[] args) {
        basicTest(new LinkedList<>(Countries.names(5)));
        basicTest(new ArrayList<>(Countries.names(5)));
        iterMotion(new LinkedList<>(Countries.names(5)));
        iterMotion(new ArrayList<>(Countries.names(5)));
        iterManipulation(new LinkedList<>(Countries.names(5)));
        iterManipulation(new ArrayList<>(Countries.names(5)));
        testVisual(new LinkedList<>(Countries.names(5)));
        testLinkedList();
    }
}

/**
 * Set测试
 */
class SetType {
    int i;

    /**
     * @param n
     */
    public SetType(int n) {
        this.i = n;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof SetType && (i == ((SetType)obj).i);
    }
    
    public String toString() {
        return Integer.toString(i);
    }
}

class HashType extends SetType {

    public HashType(int n) {
        super(n);
    }
    
    public int hashCode() {
        return i;
    }
}

class TreeType extends SetType implements Comparable<TreeType> {

    public TreeType(int n) {
        super(n);
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(TreeType o) {
        return o.i < i ? -1 : (o.i == i ? 0 : 1);
    }
    
}

class TypesForSets {
    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for (int i = 0; i < 10; i++) {
                set.add(type.getConstructor(int.class).newInstance(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return set;
    }
    
    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        fill(set, type);
        fill(set, type);
        System.out.println(set);
    }
    
    public static void main(String[] args) {
        test(new HashSet<HashType>(), HashType.class);
        test(new LinkedHashSet<HashType>(), HashType.class);
        test(new TreeSet<TreeType>(), TreeType.class);
        
        test(new HashSet<SetType>(), SetType.class);
        test(new HashSet<TreeType>(), TreeType.class);
        test(new LinkedHashSet<SetType>(), SetType.class);
        test(new LinkedHashSet<TreeType>(), TreeType.class);
        
        try {
            test(new TreeSet<SetType>(), SetType.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            test(new TreeSet<HashType>(), HashType.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        Set<SetType> sets = new HashSet<>();
        sets.add(new SetType(1));
        sets.add(new SetType(1));
        System.out.println(sets);
    }
}

class SortedSetDemo {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet, "one two three four five six seven eight".split(" "));
        System.out.println(sortedSet);    // [eight, five, four, one, seven, six, three, two]
        String low = sortedSet.first();
        String high = sortedSet.last();
        System.out.println(low);        // eight
        System.out.println(high);        // two
        
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) low = it.next();
            if (i == 6) high = it.next();
            else it.next();
        }
        System.out.println(low);    // one
        System.out.println(high);    // two
        System.out.println(sortedSet.subSet(low, high));    // [one, seven, six, three]
        System.out.println(sortedSet.headSet(high));        // [eight, five, four, one, seven, six, three]
        System.out.println(sortedSet.tailSet(low));    // [one, seven, six, three, two]
    }
}

/**
 * 队列测试
 */
class QueueBehavior {
    private static int count = 10;
    
    static <T> void test(Queue<T> queue, Generator<T> gen) {
        for (int i = 0; i < count; i++) {
            queue.offer(gen.next());
        }
        while (queue.peek() != null) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }
    
    static class Gen implements Generator<String> {
        String[] s = ("one two three four five six seven eight nine ten").split(" ");
        int i;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public String next() {
            return s[i++];
        }
        
    }
    
    public static void main(String[] args) {
        test(new LinkedList<String>(), new Gen());
        test(new PriorityQueue<String>(), new Gen());
        test(new ArrayBlockingQueue<String>(count), new Gen());
        test(new ConcurrentLinkedQueue<String>(), new Gen());
        test(new LinkedBlockingQueue<String>(), new Gen());
        test(new PriorityBlockingQueue<String>(), new Gen());
    }
}

/**
 * 优先级队列
 */
class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
    private static final long serialVersionUID = -4202975097781905232L;

    static class ToDoItem implements Comparable<ToDoItem> {
        private char primary;
        private int secondary;
        private String item;
        
        /**
         * @param primary
         * @param secondary
         * @param item
         */
        public ToDoItem(char primary, int secondary, String item) {
            this.primary = primary;
            this.secondary = secondary;
            this.item = item;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        @Override
        public int compareTo(ToDoItem o) {
            if (primary > o.primary) return 1;
            if (primary == o.primary)
                if (secondary > o.secondary) return 1;
                else if (secondary == o.secondary) return 0;
            return -1;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return Character.toString(primary) + secondary + ": " + item;
        }

    }
    
    public void add(char primary, int secondary, String item) {
        super.add(new ToDoItem(primary, secondary, item));
    }
    
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.add('C', 4, "Empty trash");
        toDoList.add('A', 2, "Feed dog");
        toDoList.add('B', 7, "Feed bird");
        toDoList.add('C', 3, "Mow lawn");
        toDoList.add('A', 1, "Water lawn");
        toDoList.add('B', 1, "Feed cat");
        while (!toDoList.isEmpty())
            System.out.println(toDoList.remove());
    }
}

/**
 * 关联数组,模仿Map
 */
class AssociativeArray<K,V> {
    private Object[][] pairs;
    private int index;
    
    public AssociativeArray(int length) {
        pairs = new Object[length][2];
    }
    
    public void put(K key, V value) {
        if (index > pairs.length)
            throw new ArrayIndexOutOfBoundsException();
        pairs[index++] = new Object[] {key, value};
    }
    
    @SuppressWarnings("unchecked")
    public V get(K key) {
        for (int i = 0; i < index; i++) {
            if (key.equals(pairs[i][0])) {
                return (V) pairs[i][1];
            }
        }
        return null;
    }
    
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++) {
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());
            if (i < index - 1)
                result.append("\n");
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        AssociativeArray<String, String> map = new AssociativeArray<String, String>(6);
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tail");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try {
            map.put("extra", "object");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("too many objects");
        }
        System.out.println(map);
        System.out.println(map.get("ocean"));
    }
}

class Maps {
    public static void printKeys(Map<Integer, String> map) {
        System.out.print("Size = " + map.size() + ", ");
        System.out.print("Keys: ");
        System.out.println(map.keySet());
    }
    
    public static void test(Map<Integer, String> map) {
        System.out.println(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        map.putAll(new CountingMapData(25));
        printKeys(map);
        
        System.out.print("Values: ");
        System.out.println(map.values());
        System.out.println(map);
        
        System.out.println(map.containsKey(11));
        System.out.println(map.get(11));
        System.out.println(map.containsValue("F0"));
        
        Integer key = map.keySet().iterator().next();
        System.out.println("First key in map: " + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        System.out.println(map.isEmpty());
        map.putAll(new CountingMapData(25));
        map.keySet().removeAll(map.keySet());
        System.out.println(map.isEmpty());
    }
    
    public static void main(String[] args) {
        test(new HashMap<>());
        test(new TreeMap<>());
        test(new LinkedHashMap<>());
        test(new IdentityHashMap<>());
        test(new ConcurrentHashMap<>());
        test(new WeakHashMap<>());
    }
}

/**
 * 排序Map
 */
class SortedMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> sortedMap = new TreeMap<>(new CountingMapData(10));
        System.out.println(sortedMap);    // {0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0}
        
        Integer low = sortedMap.firstKey();
        Integer high = sortedMap.lastKey();
        System.out.println(low);    // 0
        System.out.println(high);    // 9
        
        Iterator<Integer> it = sortedMap.keySet().iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) low = it.next();
            if (i == 6) high = it.next();
            else it.next();
        }
        System.out.println(low);    // 3
        System.out.println(high);    // 7
        System.out.println(sortedMap.subMap(low, high));    // {3=D0, 4=E0, 5=F0, 6=G0}
        System.out.println(sortedMap.headMap(high));    // {0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0}
        System.out.println(sortedMap.tailMap(low));    // {3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0}
    }
}

class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>(new CountingMapData(9));
        System.out.println(linkedMap);    // {0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
        // LRU最近最少使用,没有被访问过的出现在队列的前面
        linkedMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedMap.putAll(new CountingMapData(9));
        System.out.println(linkedMap);    // {0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
        for (int i = 0; i < 6; i++) {
            linkedMap.get(i);
        }
        System.out.println(linkedMap);    // {6=G0, 7=H0, 8=I0, 0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0}
        linkedMap.get(0);
        System.out.println(linkedMap);    // {6=G0, 7=H0, 8=I0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 0=A0}
    }
}

/**
 * 用ArrayList实现的Map
 */
class SlowMap<K,V> extends AbstractMap<K, V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();
    
    public V put(K key, V value) {
        V oldValue = get(key);
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);
        }
        
        return oldValue;
    }
    
    public V get(Object key) {
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    /* (non-Javadoc)
     * @see java.util.AbstractMap#entrySet()
     */
    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while (ki.hasNext()) {
            set.add(new MapEntry<K,V>(ki.next(), vi.next()));
        }
        return set;
    }
    
    public static void main(String[] args) {
        SlowMap<String, String> m = new SlowMap<String, String>();
        m.putAll(Countries.capitals(15));
        System.out.println(m);
        System.out.println(m.get("BULGARIL"));
        System.out.println(m.entrySet());
    }
}

class MapEntry<K,V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    /**
     * @param key
     * @param value
     */
    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /* (non-Javadoc)
     * @see java.util.Map.Entry#getKey()
     */
    @Override
    public K getKey() {
        return key;
    }

    /* (non-Javadoc)
     * @see java.util.Map.Entry#getValue()
     */
    @Override
    public V getValue() {
        return value;
    }

    /* (non-Javadoc)
     * @see java.util.Map.Entry#setValue(java.lang.Object)
     */
    @Override
    public V setValue(V value) {
        V result = this.value;
        this.value = value;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MapEntry)) return false;
        MapEntry me = (MapEntry) obj;
        return (key == null ? me.getKey() == null : key.equals(me.getKey())) &&
                (value == null ? me.getValue() == null : key.equals(me.getValue()));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return key + "=" + value;
    }
    
}

/**
 * 散列Map
 */
class SimpleHashMap<K,V> extends AbstractMap<K, V> {
    static final int SIZE = 997;
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];
    
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K,V>>();
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<K,V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        
        if (!found)
            buckets[index].add(pair);
        
        return oldValue;
    }
    
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        for (MapEntry<K, V> iPair : buckets[index]) {
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        }
        
        return null;
    }

    /* (non-Javadoc)
     * @see java.util.AbstractMap#entrySet()
     */
    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mPair : bucket) {
                set.add(mPair);
            }
        }
        return set;
    }
    
    public static void main(String[] args) {
        SimpleHashMap<String, String> m = new SimpleHashMap<String, String>();
        m.putAll(Countries.capitals(25));
        System.out.println(m);
        System.out.println(m.get("ERITREA"));
        System.out.println(m.entrySet());
    }
}

/**
 * 容器性能测试
 */
@SuppressWarnings("hiding")
abstract class Test<C> {
    String name;

    public Test(String name) {
        this.name = name;
    }
    
    abstract int test(C container, TestParam tp);
}

class TestParam {
    public final int size;
    public final int loops;
    
    public TestParam(int size, int loops) {
        this.size = size;
        this.loops = loops;
    }
    
    public static TestParam[] array(int... values) {
        int size = values.length / 2;
        TestParam[] result = new TestParam[size];
        int n = 0;
        for (int i = 0; i < size; i++) {
            result[i] = new TestParam(values[n++], values[n++]);
        }
        
        return result;
    }
    
    public static TestParam[] array(String[] values) {
        int[] vals = new int[values.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.decode(values[i]);
        }
        
        return array(vals);
    }
}

@SuppressWarnings("hiding")
class Tester<C> {
    public static int fieldWidth = 8;
    public static TestParam[] defaultParams = TestParam.array(
            10, 5000, 100, 5000, 1000, 5000, 10000, 500);
    protected C container;
    private String headline = "";
    private List<Test<C>> tests;
    private static int sizeWidth = 5;
    private static String sizeField = "%" + sizeWidth + "s";
    private TestParam[] paramList = defaultParams;
    
    public Tester(C container, List<Test<C>> tests) {
        this.container = container;
        this.tests = tests;
        if (container != null)
            headline = container.getClass().getSimpleName();
    }
    
    public Tester(C container, List<Test<C>> tests, TestParam[] paramList) {
        this(container, tests);
        this.paramList = paramList;
    }

    // 覆盖以修改测试前的初始化
    protected C initialize(int size) {
        return container;
    }
    
    private static String stringField() {
        return "%" + fieldWidth + "s";
    }
    
    private static String numberField() {
        return "%" + fieldWidth + "d";
    }
    
    public void setHeadline(String newHeadline) {
        this.headline = newHeadline;
    }
    
    public static <C> void run(C cntcr, List<Test<C>> tests) {
        new Tester<C>(cntcr, tests).timedTest();
    }
    
    public static <C> void run(C cntcr, List<Test<C>> tests, TestParam[] paramList) {
        new Tester<C>(cntcr, tests, paramList).timedTest();
    }
    
    private void displayHeader() {
        // Calculate width and pad with '-'
        int width = fieldWidth * tests.size() + sizeWidth;
        int dashLength = width - headline.length() - 1;
        StringBuilder head = new StringBuilder(width);
        for (int i = 0; i < dashLength / 2; i++) {
            head.append('-');
        }
        head.append(' ');
        head.append(headline);
        head.append(' ');
        for (int i = 0; i < dashLength / 2; i++) {
            head.append('-');
        }
        System.out.println(head);
        
        // Print column headers
        System.out.format(sizeField, "size");
        for (Test<?> test : tests)
            System.out.format(stringField(), test.name);
        System.out.println();
    }
    
    public void timedTest() {
        displayHeader();
        for (TestParam param : paramList) {
            System.out.format(sizeField, param.size);
            for (Test<C> test : tests) {
                C container = initialize(param.size);
                long start = System.nanoTime();
                // Call the overriden method
                int reps = test.test(container, param);
                long duration = System.nanoTime() - start;
                long timePerRep = duration / reps;
                System.out.format(numberField(), timePerRep);
            }
            System.out.println();
        }
    }
}

/**
 * 对List操作中最本质部分的性能测试
 */
class ListPerformance {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests = new ArrayList<Test<List<Integer>>>();
    static List<Test<LinkedList<Integer>>> qTests = new ArrayList<Test<LinkedList<Integer>>>();
    static {
        tests.add(new Test<List<Integer>>("add") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < listSize; j++)
                        list.add(j);
                }
                return loops * listSize;
            }
        });
        tests.add(new Test<List<Integer>>("get") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++)
                    list.get(rand.nextInt(listSize));
                return loops;
            }
        });
        tests.add(new Test<List<Integer>>("set") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++)
                    list.set(rand.nextInt(listSize), 47);
                return loops;
            }
        });
        tests.add(new Test<List<Integer>>("iteradd") {
            int test(List<Integer> list, TestParam tp) {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<Integer> it = list.listIterator(half);
                for (int i = 0; i < LOOPS; i++)
                    it.add(47);
                return LOOPS;
            }
        });
        tests.add(new Test<List<Integer>>("insert") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                for (int i = 0; i < loops; i++)
                    list.add(5, 47); // Minimize random-access cost
                return loops;
            }
        });
        tests.add(new Test<List<Integer>>("remove") {
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 5)
                        list.remove(5); // Minimize random-access cost
                }
                return loops * size;
            }
        });
        // Tests for queue behavior:
        qTests.add(new Test<LinkedList<Integer>>("addFirst") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addFirst(47);
                }
                return loops * size;
            }
        });
        qTests.add(new Test<LinkedList<Integer>>("addLast") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addLast(47);
                }
                return loops * size;
            }
        });
        qTests.add(new Test<LinkedList<Integer>>("rmFirst") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 0)
                        list.removeFirst();
                }
                return loops * size;
            }
        });
        qTests.add(new Test<LinkedList<Integer>>("rmLast") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }

    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container,
                List<Test<List<Integer>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<Integer> initialize(int size) {
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }

        // Convenience method:
        public static void run(List<Integer> list,
                List<Test<List<Integer>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        // Can only do these two tests on an array:
        Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null,
                tests.subList(1, 3)) {
            // This will be called before each test. It
            // produces a non-resizeable array-backed list:
            @Override
            protected List<Integer> initialize(int size) {
                Integer[] ia = Generated.array(Integer.class,
                        new CountingGenerator.Integer(), size);
                return Arrays.asList(ia);
            }
        };
        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        Tester.defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 1000,
                10000, 200);
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        ListTester.run(new ArrayList<Integer>(), tests);
        ListTester.run(new LinkedList<Integer>(), tests);
        ListTester.run(new Vector<Integer>(), tests);
        Tester.fieldWidth = 12;
        Tester<LinkedList<Integer>> qTest = new Tester<LinkedList<Integer>>(
                new LinkedList<Integer>(), qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }
}

/**
 * 对Set操作中最本质部分的性能测试
 */
class SetPerformance {
    static List<Test<Set<Integer>>> tests = new ArrayList<Test<Set<Integer>>>();
    static {
        tests.add(new Test<Set<Integer>>("add") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    set.clear();
                    for (int j = 0; j < size; j++)
                        set.add(j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Set<Integer>>("contains") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        set.contains(j);
                return loops * span;
            }
        });
        tests.add(new Test<Set<Integer>>("iterate") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<Integer> it = set.iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * set.size();
            }
        });
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.fieldWidth = 10;
        Tester.run(new TreeSet<Integer>(), tests);
        Tester.run(new HashSet<Integer>(), tests);
        Tester.run(new LinkedHashSet<Integer>(), tests);
    }
}

/**
 * 对Map操作中最本质部分的性能测试
 */
class MapPerformance {
    static List<Test<Map<Integer, Integer>>> tests = new ArrayList<Test<Map<Integer, Integer>>>();
    static {
        tests.add(new Test<Map<Integer, Integer>>("put") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++)
                        map.put(j, j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("get") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("iterate") {
            @SuppressWarnings("rawtypes")
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * map.size();
            }
        });
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.run(new TreeMap<Integer, Integer>(), tests);
        Tester.run(new HashMap<Integer, Integer>(), tests);
        Tester.run(new LinkedHashMap<Integer, Integer>(), tests);
        Tester.run(new IdentityHashMap<Integer, Integer>(), tests);
        Tester.run(new WeakHashMap<Integer, Integer>(), tests);
        Tester.run(new Hashtable<Integer, Integer>(), tests);
    }
}

/**
 * Collections的实用方法
 */
class Utilities {
    static List<String> list = Arrays.asList(("one two three Four five six one").split(" "));
    
    public static void main(String[] args) {
        System.out.println(list);    // [one, two, three, Four, five, six, one]
        System.out.println(Collections.disjoint(list, Collections.singletonList("Four")));    // false
        System.out.println(Collections.max(list));    // two
        System.out.println(Collections.min(list));    // Four
        System.out.println(Collections.max(list, String.CASE_INSENSITIVE_ORDER));    // two
        System.out.println(Collections.min(list, String.CASE_INSENSITIVE_ORDER));    // five
        
        List<String> sublist = Arrays.asList(("Four five six").split(" "));
        System.out.println(Collections.indexOfSubList(list, sublist));    // 3
        System.out.println(Collections.lastIndexOfSubList(list, sublist));    // 3
        Collections.replaceAll(list, "one", "Yo");
        System.out.println(list);    // [Yo, two, three, Four, five, six, Yo]
        Collections.reverse(list);
        System.out.println(list);    // [Yo, six, five, Four, three, two, Yo]
        Collections.rotate(list, 3);
        System.out.println(list);    // [three, two, Yo, Yo, six, five, Four]
        
        List<String> source = Arrays.asList(("in the matrix").split(" "));
        Collections.copy(list, source);
        System.out.println(list);    // [in, the, matrix, Yo, six, five, Four]
        Collections.swap(list, 0, list.size() - 1);
        System.out.println(list);    // [Four, the, matrix, Yo, six, five, in]
        Collections.shuffle(list, new Random(47));
        System.out.println(list);    // [six, matrix, the, Four, Yo, five, in]
        Collections.fill(list, "pop");
        System.out.println(list);    // [pop, pop, pop, pop, pop, pop, pop]
        System.out.println(Collections.frequency(list, "pop"));    // 7
        
        List<String> dups = Collections.nCopies(3, "snap");
        System.out.println(dups);    // [snap, snap, snap]
        System.out.println(Collections.disjoint(list, dups));    // true
        
        Enumeration<String> e = Collections.enumeration(dups);
        
        Vector<String> v = new Vector<>();
        while (e.hasMoreElements()) {
            v.addElement(e.nextElement());
        }
        ArrayList<String> arrayList = Collections.list(v.elements());
        System.out.println(arrayList);    // [snap, snap, snap]
    }
}

/**
 * 持有引用
 */
class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();
    
    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null)
            System.out.println("In queue: " + inq.get());
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        int size = 10;
        
        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            checkQueue();
        }
        
        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            wa.add(new WeakReference<VeryBig>(new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }
        
        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("Weak"));
        System.gc();
        
        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }
}

/**
 * 大对象
 */
class VeryBig {
    private static final int SIZE = 10000;
    @SuppressWarnings("unused")
    private long[] la = new long[SIZE];
    private String ident;
    
    public VeryBig(String id) {
        this.ident = id;
    }
    
    public String toString() {
        return ident;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing " + ident);
    }
}

/**
 * 引用测试
 */
class SoftWeakPhantomReference {
    static class Pilot {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    static class SoftRefedPilot extends SoftReference<Pilot> {
        public int key;

        public SoftRefedPilot(int key, Pilot referent, ReferenceQueue<Pilot> q) {
            super(referent, q);
            this.key = key;
        }
    }

    static void soft() {
        Map<Integer, SoftRefedPilot> map = new HashMap<Integer, SoftRefedPilot>();
        ReferenceQueue<Pilot> queue = new ReferenceQueue<Pilot>();
        int i = 0;
        while (i < 10000000) {
            Pilot p = new Pilot();
            map.put(i, new SoftRefedPilot(i, p, queue));
            // p = null;
            SoftRefedPilot pollref = (SoftRefedPilot) queue.poll();
            if (pollref != null) {// 找出被软引用回收的对象
                // 以key为标志，从map中移除
                map.remove(pollref.key);
            }
            i++;
        }
        System.out.println("done");
    }

    public static void main(String[] args) {
        soft();
    }
}
