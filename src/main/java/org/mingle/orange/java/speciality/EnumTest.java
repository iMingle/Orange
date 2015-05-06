/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.mingle.orange.java.speciality.Food.Appetizer;
import org.mingle.orange.java.speciality.Food.Coffee;
import org.mingle.orange.java.speciality.Food.Dessert;
import org.mingle.orange.java.speciality.Food.MainCourse;
import org.mingle.orange.java.util.TextFile;

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
	APPETIZER(Food.Appetizer.class), MAINCOURSE(Food.MainCourse.class), DESSERT(
			Food.Dessert.class), COFFEE(Food.Coffee.class);

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
		// BOND: MUNICIPAL
		// BOND: MUNICIPAL
		// STOCK: MARGIN
		// STOCK: MARGIN
		// BOND: JUNK
		// STOCK: SHORT
		// STOCK: LONG
		// STOCK: LONG
		// BOND: MUNICIPAL
		// BOND: JUNK

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
		points.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2,
				AlarmPoints.KITCHEN));
		System.out.println(points); // [STAIR1, STAIR2, BATHROOM, KITCHEN]

		points = EnumSet.allOf(AlarmPoints.class);
		points.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2,
				AlarmPoints.KITCHEN));
		System.out.println(points); // [LOBBY, OFFICE1, OFFICE2, OFFICE3,
									// OFFICE4, BATHROOM, UTILITY]

		points = EnumSet.complementOf(points);
		System.out.println(points); // [STAIR1, STAIR2, KITCHEN]
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

/**
 * 常量相关的方法
 */
enum ConstantSpecificMethod {
	DATE_TIME {
		String getInfo() {
			return DateFormat.getDateInstance().format(new Date());
		}
	},
	CLASSPATH {
		String getInfo() {
			return System.getenv("CLASSPATH");
		}
	},
	VERSION {
		String getInfo() {
			return System.getProperty("java.version");
		}
	};
	abstract String getInfo();

	public static void main(String[] args) {
		for (ConstantSpecificMethod csm : values()) {
			System.out.println(csm.getInfo());
		}
	}
}

/**
 * enum职责链,投递邮件
 */
class Mail {
	// The NO's lower the probability of random selection:
	enum GeneralDelivery {
		YES, NO1, NO2, NO3, NO4, NO5
	}

	enum Scannability {
		UNSCANNABLE, YES1, YES2, YES3, YES4
	}

	enum Readability {
		ILLEGIBLE, YES1, YES2, YES3, YES4
	}

	enum Address {
		INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6
	}

	enum ReturnAddress {
		MISSING, OK1, OK2, OK3, OK4, OK5
	}

	GeneralDelivery generalDelivery;
	Scannability scannability;
	Readability readability;
	Address address;
	ReturnAddress returnAddress;
	static long counter = 0;
	long id = counter++;

	public String toString() {
		return "Mail " + id;
	}

	public String details() {
		return toString() + ", General Delivery: " + generalDelivery
				+ ", Address Scanability: " + scannability
				+ ", Address Readability: " + readability
				+ ", Address Address: " + address + ", Return address: "
				+ returnAddress;
	}

	public static Mail randomMail() {
		Mail m = new Mail();
		m.generalDelivery = Enums.random(GeneralDelivery.class);
		m.scannability = Enums.random(Scannability.class);
		m.readability = Enums.random(Readability.class);
		m.address = Enums.random(Address.class);
		m.returnAddress = Enums.random(ReturnAddress.class);
		return m;
	}

	public static Iterable<Mail> generator(final int count) {
		return new Iterable<Mail>() {
			int n = count;

			@Override
			public Iterator<Mail> iterator() {
				return new Iterator<Mail>() {

					@Override
					public Mail next() {
						return randomMail();
					}

					@Override
					public boolean hasNext() {
						return n-- > 0;
					}
				};
			}
		};
	}
}

class PostOffice {
	enum MailHandler {
		GENERAL_DELIVERY {
			boolean handle(Mail m) {
				switch (m.generalDelivery) {
				case YES:
					System.out.println("Using general delivery for " + m);
					return true;
				default:
					return false;
				}
			}
		},
		MACHINE_SCAN {
			boolean handle(Mail m) {
				switch (m.scannability) {
				case UNSCANNABLE:
					return false;
				default:
					switch (m.address) {
					case INCORRECT:
						return false;
					default:
						System.out
								.println("Delivering " + m + " automatically");
						return true;
					}
				}
			}
		},
		VISUAL_INSPECTION {
			boolean handle(Mail m) {
				switch (m.readability) {
				case ILLEGIBLE:
					return false;
				default:
					switch (m.address) {
					case INCORRECT:
						return false;
					default:
						System.out.println("Delivering " + m + " normally");
						return true;
					}
				}
			}
		},
		RETURN_TO_SENDER {
			boolean handle(Mail m) {
				switch (m.returnAddress) {
				case MISSING:
					return false;
				default:
					System.out.println("Returning " + m + " to sender");
					return true;
				}
			}
		};
		abstract boolean handle(Mail m);
	}

	static void handle(Mail m) {
		for (MailHandler handler : MailHandler.values())
			if (handler.handle(m))
				return;
		System.out.println(m + " is a dead letter");
	}

	public static void main(String[] args) {
		for (Mail mail : Mail.generator(10)) {
			System.out.println(mail.details());
			handle(mail);
			System.out.println("*****");
		}
	}
}

/**
 * enum状态机,自动售货机
 */
enum Input {
	NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100), TOOTHPASTE(200), CHIPS(75), SODA(
			100), SOAP(50), ABORT_TRANSACTION {
		public int amount() { // Disallow
			throw new RuntimeException("ABORT.amount()");
		}
	},
	STOP { // This must be the last instance.
		public int amount() { // Disallow
			throw new RuntimeException("SHUT_DOWN.amount()");
		}
	};
	int value; // In cents

	Input(int value) {
		this.value = value;
	}

	Input() {
	}

	int amount() {
		return value;
	}; // In cents

	static Random rand = new Random(47);

	public static Input randomSelection() {
		// Don't include STOP:
		return values()[rand.nextInt(values().length - 1)];
	}
}

/**
 * 货物类别
 */
enum Category {
	MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR), ITEM_SELECTION(
			Input.TOOTHPASTE, Input.CHIPS, Input.SODA, Input.SOAP), QUIT_TRANSACTION(
			Input.ABORT_TRANSACTION), SHUT_DOWN(Input.STOP);
	private Input[] values;

	Category(Input... types) {
		values = types;
	}

	private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(
			Input.class);
	static {
		for (Category c : Category.class.getEnumConstants())
			for (Input type : c.values)
				categories.put(type, c);
	}

	public static Category categorize(Input input) {
		return categories.get(input);
	}
}

/**
 * 售货机
 */
class VendingMachine {
	private static State state = State.RESTING;
	private static int amount = 0;
	private static Input selection = null;

	enum StateDuration {
		TRANSIENT
	} // Tagging enum

	enum State {
		RESTING {
			void next(Input input) {
				switch (Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
					state = ADDING_MONEY;
					break;
				case SHUT_DOWN:
					state = TERMINAL;
				default:
				}
			}
		},
		ADDING_MONEY {
			void next(Input input) {
				switch (Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
					break;
				case ITEM_SELECTION:
					selection = input;
					if (amount < selection.amount())
						System.out.println("Insufficient money for "
								+ selection);
					else
						state = DISPENSING;
					break;
				case QUIT_TRANSACTION:
					state = GIVING_CHANGE;
					break;
				case SHUT_DOWN:
					state = TERMINAL;
				default:
				}
			}
		},
		DISPENSING(StateDuration.TRANSIENT) {
			void next() {
				System.out.println("here is your " + selection);
				amount -= selection.amount();
				state = GIVING_CHANGE;
			}
		},
		GIVING_CHANGE(StateDuration.TRANSIENT) {
			void next() {
				if (amount > 0) {
					System.out.println("Your change: " + amount);
					amount = 0;
				}
				state = RESTING;
			}
		},
		TERMINAL {
			void output() {
				System.out.println("Halted");
			}
		};
		private boolean isTransient = false;

		State() {
		}

		State(StateDuration trans) {
			isTransient = true;
		}

		void next(Input input) {
			throw new RuntimeException("Only call "
					+ "next(Input input) for non-transient states");
		}

		void next() {
			throw new RuntimeException("Only call next() for "
					+ "StateDuration.TRANSIENT states");
		}

		void output() {
			System.out.println(amount);
		}
	}

	static void run(Generator<Input> gen) {
		while (state != State.TERMINAL) {
			state.next(gen.next());
			while (state.isTransient)
				state.next();
			state.output();
		}
	}

	public static void main(String[] args) {
		Generator<Input> gen = new RandomInputGenerator();
		if (args.length == 1)
			gen = new FileInputGenerator(args[0]);
		run(gen);
	}
}

class RandomInputGenerator implements Generator<Input> {
	public Input next() {
		return Input.randomSelection();
	}
}

// Create Inputs from a file of ';'-separated strings:
class FileInputGenerator implements Generator<Input> {
	private Iterator<String> input;

	public FileInputGenerator(String fileName) {
		input = new TextFile(fileName, ";").iterator();
	}

	public Input next() {
		if (!input.hasNext())
			return null;
		return Enum.valueOf(Input.class, input.next().trim());
	}
}

/**
 * 多路分发,输赢结果
 */
enum Outcome {
	WIN, LOSE, DRAW
}

interface Item {
	Outcome compete(Item it);
	Outcome eval(Paper p);	// 布
	Outcome eval(Scissors s);	// 剪刀
	Outcome eval(Rock r);	// 石头
}

class Paper implements Item {

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#compete(org.mingle.orange.java.speciality.Item)
	 */
	@Override
	public Outcome compete(Item it) {
		return it.eval(this);
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Paper)
	 */
	@Override
	public Outcome eval(Paper p) {
		return Outcome.DRAW;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Scissors)
	 */
	@Override
	public Outcome eval(Scissors s) {
		return Outcome.WIN;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Rock)
	 */
	@Override
	public Outcome eval(Rock r) {
		return Outcome.LOSE;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Paper";
	}
	
}

class Scissors implements Item {

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#compete(org.mingle.orange.java.speciality.Item)
	 */
	@Override
	public Outcome compete(Item it) {
		return it.eval(this);
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Paper)
	 */
	@Override
	public Outcome eval(Paper p) {
		return Outcome.LOSE;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Scissors)
	 */
	@Override
	public Outcome eval(Scissors s) {
		return Outcome.DRAW;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Rock)
	 */
	@Override
	public Outcome eval(Rock r) {
		return Outcome.WIN;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Scissors";
	}
	
}

class Rock implements Item {

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#compete(org.mingle.orange.java.speciality.Item)
	 */
	@Override
	public Outcome compete(Item it) {
		return it.eval(this);
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Paper)
	 */
	@Override
	public Outcome eval(Paper p) {
		return Outcome.WIN;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Scissors)
	 */
	@Override
	public Outcome eval(Scissors s) {
		return Outcome.LOSE;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Item#eval(org.mingle.orange.java.speciality.Rock)
	 */
	@Override
	public Outcome eval(Rock r) {
		return Outcome.DRAW;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rock";
	}
	
}

class RoShamBo {
	public static <T extends Competitor<T>> void match(T a, T b) {
		System.out.println(a + " vs. " + b + ": " + a.compete(b));
	}

	public static <T extends Enum<T> & Competitor<T>> void play(
			Class<T> rsbClass, int size) {
		for (int i = 0; i < size; i++)
			match(Enums.random(rsbClass), Enums.random(rsbClass));
	}
}

/**
 * 石头剪刀布
 */
class RoShamBo1 {
	static final int SIZE = 20;
	private static Random rand = new Random(47);
	
	public static Item newItem() {
		switch (rand.nextInt(3)) {
		case 0:
			return new Scissors();
		case 1:
			return new Paper();
		case 2:
			return new Rock();
		default:
			return null;
		}
	}
	
	public static void match(Item a, Item b) {
		System.out.println(a + " vs. " + b + ": " + a.compete(b));
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < SIZE; i++) {
			match(newItem(), newItem());
		}
	}
}

/**
 * 使用enum分发
 */
interface Competitor<T extends Competitor<T>> {
	Outcome compete(T competitor);
}

enum RoShamBo2 implements Competitor<RoShamBo2> {
	PAPER(Outcome.DRAW, Outcome.LOSE, Outcome.WIN), 
	SCISSORS(Outcome.WIN, Outcome.DRAW, Outcome.LOSE), 
	ROCK(Outcome.LOSE, Outcome.WIN, Outcome.DRAW);
	
	private Outcome vPAPER, vSCISSORS, vROCK;

	/**
	 * @param vPAPER
	 * @param vSCISSORS
	 * @param vROCK
	 */
	private RoShamBo2(Outcome vPAPER, Outcome vSCISSORS, Outcome vROCK) {
		this.vPAPER = vPAPER;
		this.vSCISSORS = vSCISSORS;
		this.vROCK = vROCK;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Competitor#compete(org.mingle.orange.java.speciality.Competitor)
	 */
	@Override
	public Outcome compete(RoShamBo2 it) {
		switch (it) {
		case PAPER:
			return vPAPER;
		case SCISSORS:
			return vSCISSORS;
		case ROCK:
			return vROCK;
		default:
			return null;
		}
	}
	
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo2.class, 20);
	}
}

enum RoShamBo3 implements Competitor<RoShamBo3> {
	PAPER {
		public Outcome compete(RoShamBo3 it) {
			switch (it) {
			default: // To placate the compiler
			case PAPER:
				return Outcome.DRAW;
			case SCISSORS:
				return Outcome.LOSE;
			case ROCK:
				return Outcome.WIN;
			}
		}
	},
	SCISSORS {
		public Outcome compete(RoShamBo3 it) {
			switch (it) {
			default:
			case PAPER:
				return Outcome.WIN;
			case SCISSORS:
				return Outcome.DRAW;
			case ROCK:
				return Outcome.LOSE;
			}
		}
	},
	ROCK {
		public Outcome compete(RoShamBo3 it) {
			switch (it) {
			default:
			case PAPER:
				return Outcome.LOSE;
			case SCISSORS:
				return Outcome.WIN;
			case ROCK:
				return Outcome.DRAW;
			}
		}
	};
	public abstract Outcome compete(RoShamBo3 it);

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo3.class, 20);
	}
}

enum RoShamBo4 implements Competitor<RoShamBo4> {
	ROCK {
		public Outcome compete(RoShamBo4 opponent) {
			return compete(SCISSORS, opponent);
		}
	},
	SCISSORS {
		public Outcome compete(RoShamBo4 opponent) {
			return compete(PAPER, opponent);
		}
	},
	PAPER {
		public Outcome compete(RoShamBo4 opponent) {
			return compete(ROCK, opponent);
		}
	};
	
	Outcome compete(RoShamBo4 loser, RoShamBo4 opponent) {
		return ((opponent == this) ? Outcome.DRAW
				: ((opponent == loser) ? Outcome.WIN : Outcome.LOSE));
	}

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo4.class, 20);
	}
}

enum RoShamBo5 implements Competitor<RoShamBo5> {
	PAPER, SCISSORS, ROCK;
	static EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>> table = new EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>>(
			RoShamBo5.class);
	static {
		for (RoShamBo5 it : RoShamBo5.values())
			table.put(it, new EnumMap<RoShamBo5, Outcome>(RoShamBo5.class));
		initRow(PAPER, Outcome.DRAW, Outcome.LOSE, Outcome.WIN);
		initRow(SCISSORS, Outcome.WIN, Outcome.DRAW, Outcome.LOSE);
		initRow(ROCK, Outcome.LOSE, Outcome.WIN, Outcome.DRAW);
	}

	static void initRow(RoShamBo5 it, Outcome vPAPER, Outcome vSCISSORS,
			Outcome vROCK) {
		EnumMap<RoShamBo5, Outcome> row = RoShamBo5.table.get(it);
		row.put(RoShamBo5.PAPER, vPAPER);
		row.put(RoShamBo5.SCISSORS, vSCISSORS);
		row.put(RoShamBo5.ROCK, vROCK);
	}

	public Outcome compete(RoShamBo5 it) {
		return table.get(this).get(it);
	}

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo5.class, 20);
	}
}

enum RoShamBo6 implements Competitor<RoShamBo6> {
	PAPER, SCISSORS, ROCK;
	private static Outcome[][] table = {
			{ Outcome.DRAW, Outcome.LOSE, Outcome.WIN }, // PAPER
			{ Outcome.WIN, Outcome.DRAW, Outcome.LOSE }, // SCISSORS
			{ Outcome.LOSE, Outcome.WIN, Outcome.DRAW }, // ROCK
	};

	public Outcome compete(RoShamBo6 other) {
		return table[this.ordinal()][other.ordinal()];
	}

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo6.class, 20);
	}
}