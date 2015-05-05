/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.mingle.orange.java.speciality.Food.Appetizer;
import org.mingle.orange.java.speciality.Food.Dessert;
import org.mingle.orange.java.speciality.Food.MainCourse;
import org.mingle.orange.java.speciality.Food.Coffee;

/**
 * 枚举测试
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}

enum Shrubbery {
	GROUND, CRAWLING, HANGING
}

class EnumClass {
	public static void main(String[] args) {
		for (Shrubbery s : Shrubbery.values()) {
			System.out.println(s + " ordinal: " + s.ordinal());
			System.out.print(s.compareTo(Shrubbery.CRAWLING) + " ");
			System.out.print(s.equals(Shrubbery.CRAWLING) + " ");
			System.out.println(s == Shrubbery.CRAWLING);
			System.out.println(s.getDeclaringClass());
			System.out.println(s.name());
			System.out.println("--------------------------------");
		}

		for (String s : "HANGING CRAWLING GROUND".split(" ")) {
			Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
			System.out.println(shrub);
		}
	}
}

/**
 * 枚举描述
 */
enum OzWitch {
	// Instances must be defined first, before methods:
	WEST("Miss Gulch, aka the Wicked Witch of the West"), NORTH(
			"Glinda, the Good Witch of the North"), EAST(
			"Wicked Witch of the East, wearer of the Ruby "
					+ "Slippers, crushed by Dorothy's house"), SOUTH(
			"Good by inference, but missing");

	private String description;

	// Constructor must be package or private access:
	private OzWitch(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public static void main(String[] args) {
		for (OzWitch witch : OzWitch.values()) {
			System.out.println(witch + ": " + witch.getDescription());
		}
	}
}

/**
 * 覆盖enum的方法
 */
enum SpaceShip {
	SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;

	public String toString() {
		String id = name();
		String lower = id.substring(1).toLowerCase();
		return id.charAt(0) + lower;
	}

	public static void main(String[] args) {
		for (SpaceShip s : values())
			System.out.println(s);
	}
}

/**
 * 交通灯,switch
 */
enum Signal {
	GREEN, YELLOW, RED
}

class TrafficLight {
	Signal color = Signal.RED;

	public void change() {
		switch (color) {

		case RED:
			color = Signal.GREEN;
			break;
		case GREEN:
			color = Signal.YELLOW;
			break;
		case YELLOW:
			color = Signal.RED;
			break;
		}
	}

	public String toString() {
		return "The traffic light is " + color;
	}

	public static void main(String[] args) {
		TrafficLight t = new TrafficLight();
		for (int i = 0; i < 7; i++) {
			System.out.println(t);
			t.change();
		}
	}
}

/**
 * values()的神秘之处
 */
enum Explore {
	HERE, THERE
}

class Reflection {
	public static Set<String> analyze(Class<?> enumClass) {
		System.out.println("----- Analyzing " + enumClass + " -----");
		System.out.println("Interfaces:");
		for (Type t : enumClass.getGenericInterfaces())
			System.out.println(t);
		System.out.println("Base: " + enumClass.getSuperclass());
		System.out.println("Methods: ");
		Set<String> methods = new TreeSet<String>();
		for (Method m : enumClass.getMethods())
			methods.add(m.getName());
		System.out.println(methods);
		return methods;
	}

	public static void main(String[] args) {
		Set<String> exploreMethods = analyze(Explore.class);
		Set<String> enumMethods = analyze(Enum.class);
		System.out.println("Explore.containsAll(Enum)? "
				+ exploreMethods.containsAll(enumMethods));
		System.out.print("Explore.removeAll(Enum): ");
		exploreMethods.removeAll(enumMethods);
		System.out.println(exploreMethods);
		// Decompile the code for the enum:
		OSExecute.command("javap Explore");
	}
}

/**
 * enum实现接口
 */
enum CartoonCharacter implements Generator<CartoonCharacter> {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
	private Random rand = new Random(47);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mingle.orange.java.speciality.Generator#next()
	 */
	@Override
	public CartoonCharacter next() {
		return values()[rand.nextInt(values().length)];
	}

	public static <T> void printNext(Generator<T> rg) {
		System.out.print(rg.next() + ", ");
	}

	public static void main(String[] args) {
		CartoonCharacter cc = CartoonCharacter.BOB;
		for (int i = 0; i < 10; i++) {
			printNext(cc); // BOB, PUNCHY, BOB, SPANKY, NUTTY, PUNCHY, SLAPPY,
							// NUTTY, NUTTY, SLAPPY,
		}
	}
}

/**
 * 随机选取
 */
class Enums {
	private static Random rand = new Random(47);

	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}
}

enum Activity {
	SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FALLING, FLYING
}

class RandomTest {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++)
			System.out.print(Enums.random(Activity.class) + " ");
		// STANDING FLYING RUNNING STANDING RUNNING STANDING LYING DODGING
		// SITTING RUNNING HOPPING HOPPING HOPPING RUNNING STANDING LYING
		// FALLING RUNNING FLYING LYING
	}
}

/**
 * 使用接口组织枚举
 */
interface Food {
	enum Appetizer implements Food {
		SALAD, SOUP, SPRING_ROLLS;
	}

	enum MainCourse implements Food {
		LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO;
	}

	enum Dessert implements Food {
		TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL;
	}

	enum Coffee implements Food {
		BLACK_COFFEE, DECAF_COFFEE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA;
	}
}

class TypeOfFood {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Food food = Appetizer.SALAD;
		food = MainCourse.BURRITO;
		food = Dessert.TIRAMISU;
		food = Coffee.BLACK_COFFEE;
	}
}

/**
 * 枚举的枚举
 */
enum Course {
	APPETIZER(Food.Appetizer.class), 
	MAINCOURSE(Food.MainCourse.class), 
	DESSERT(Food.Dessert.class), 
	COFFEE(Food.Coffee.class);
	
	private Food[] values;

	private Course(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}
	
	public static void main(String[] args) {
		// 生成菜单
		for (int i = 0; i < 5; i++) {
			for (Course course : Course.values()) {
				Food food = course.randomSelection();
				System.out.println(food);
			}
			System.out.println("----");
		}
	}
}

/**
 * enum的嵌套
 */
enum SecurityCategory {
	STOCK(Security.Stock.class), BOND(Security.Bond.class);
	Security[] values;

	SecurityCategory(Class<? extends Security> kind) {
		values = kind.getEnumConstants();
	}

	interface Security {
		enum Stock implements Security {
			SHORT, LONG, MARGIN
		}

		enum Bond implements Security {
			MUNICIPAL, JUNK
		}
	}

	public Security randomSelection() {
		return Enums.random(values);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			SecurityCategory category = Enums.random(SecurityCategory.class);
			System.out.println(category + ": " + category.randomSelection());
		}
//		BOND: MUNICIPAL
//		BOND: MUNICIPAL
//		STOCK: MARGIN
//		STOCK: MARGIN
//		BOND: JUNK
//		STOCK: SHORT
//		STOCK: LONG
//		STOCK: LONG
//		BOND: MUNICIPAL
//		BOND: JUNK

	}
}

/**
 * 警报传感器的安放位置
 */
enum AlarmPoints {
	STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY, KITCHEN
}

class EnumSets {
	public static void main(String[] args) {
		EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
		points.add(AlarmPoints.BATHROOM);
		points.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
		System.out.println(points);	// [STAIR1, STAIR2, BATHROOM, KITCHEN]
		
		points = EnumSet.allOf(AlarmPoints.class);
		points.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
		System.out.println(points);	// [LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY]
		
		points = EnumSet.complementOf(points);
		System.out.println(points);	// [STAIR1, STAIR2, KITCHEN]
	}
}

interface Command {
	void action();
}

class EnumMaps {
	public static void main(String[] args) {
		EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
		em.put(AlarmPoints.KITCHEN, new Command() {
			
			@Override
			public void action() {
				System.out.println("kitchen fire!");
			}
		});
		
		em.put(AlarmPoints.BATHROOM, new Command() {
			
			@Override
			public void action() {
				System.out.println("bathroom alert!");
			}
		});

		for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
			System.out.print(e.getKey() + ": ");
			e.getValue().action();
		}

		try {
			em.get(AlarmPoints.UTILITY).action();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

