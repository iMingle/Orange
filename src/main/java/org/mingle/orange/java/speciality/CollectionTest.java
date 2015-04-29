/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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

