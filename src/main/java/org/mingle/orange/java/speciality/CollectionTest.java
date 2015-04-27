/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 容器深入
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
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
		System.out.println(sortedSet);	// [eight, five, four, one, seven, six, three, two]
		String low = sortedSet.first();
		String high = sortedSet.last();
		System.out.println(low);		// eight
		System.out.println(high);		// two
		
		Iterator<String> it = sortedSet.iterator();
		for (int i = 0; i <= 6; i++) {
			if (i == 3) low = it.next();
			if (i == 6) high = it.next();
			else it.next();
		}
		System.out.println(low);	// one
		System.out.println(high);	// two
		System.out.println(sortedSet.subSet(low, high));	// [one, seven, six, three]
		System.out.println(sortedSet.headSet(high));		// [eight, five, four, one, seven, six, three]
		System.out.println(sortedSet.tailSet(low));	// [one, seven, six, three, two]
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