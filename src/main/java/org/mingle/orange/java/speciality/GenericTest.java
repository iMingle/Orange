/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * 泛型的测试类
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class GenericTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}

/**
 * 二元组
 */
class TwoTuple<U,V> {
	public final U first;
	public final V second;
	
	/**
	 * @param a
	 * @param b
	 */
	public TwoTuple(U a, V b) {
		super();
		this.first = a;
		this.second = b;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TwoTuple [first=" + first + ", second=" + second + "]";
	}
}

/**
 * 三元组
 */
class ThreeTuple<U,V,W> extends TwoTuple<U,V> {
	public final W third;

	/**
	 * @param a
	 * @param b
	 */
	public ThreeTuple(U a, V b, W c) {
		super(a, b);
		this.third = c;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ThreeTuple [first=" + first + ", second=" + second + " + third=" + third + "]";
	}

}

/**
 * 用链表实现的Stack
 */
class LinkedStack<T> {
	private Node<T> top = new Node<T>();
	
	public void push(T item) {
		top = new Node<T>(item, top);
	}
	
	public T pop() {
		T result = top.item;
		if (!top.end())
			top = top.next;
		return result;
	}
	
	private static class Node<U> {
		U item;
		Node<U> next;
		
		Node() {
			item = null;
			next = null;
		}

		/**
		 * @param item
		 * @param next
		 */
		public Node(U item, Node<U> next) {
			super();
			this.item = item;
			this.next = next;
		}
		
		boolean end() {
			return item == null && next == null;
		}
	}
	
	public static void main(String[] args) {
		LinkedStack<String> lss = new LinkedStack<String>();
		for (String s : "Phasers on stun!".split(" ")) {
			lss.push(s);
		}
		String s = null;
		while ((s = lss.pop()) != null) {
			System.out.println(s);
		}
	}
}

/**
 * 泛型接口
 */
interface Generator<T> {
	T next();
}

class Coffee {
	private static long counter = 0;
	private final long id = counter++;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + id;
	}
}

class Latte extends Coffee {}

class Mocha extends Coffee {}

class Cappuccino extends Coffee {}

class Americano extends Coffee {}

class Breve extends Coffee {}

class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
	private Class<?>[] types = {
			Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class
	};
	private Random rand = new Random(47);
	private int size = 0;
	
	public CoffeeGenerator() {}
	
	public CoffeeGenerator(int sz) {
		this.size = sz;
	}
	
	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Generator#next()
	 */
	@Override
	public Coffee next() {
		try {
			return (Coffee) types[rand.nextInt(types.length)].newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();
	}

	class CoffeeIterator implements Iterator<Coffee> {
		int count = size;

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return count > 0;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}
		
	}
	
	public static void main(String[] args) {
		CoffeeGenerator gen = new CoffeeGenerator();
		for (int i = 0; i < 5; i++) {
			System.out.println(gen.next());
		}
		for (Coffee c : new CoffeeGenerator(5)) {
			System.out.println(c);
		}
	}
}

/**
 * 创建常用的容器对象
 */
class New {
	public static <K,V> Map<K,V> map() {
		return new HashMap<>();
	}
	
	public static <T> List<T> list() {
		return new ArrayList<>();
	}
	
	public static <T> List<T> lList() {
		return new LinkedList<>();
	}
	
	public static <T> Set<T> set() {
		return new HashSet<>();
	}
	
	public static <T> Queue<T> queue() {
		return new LinkedList<>();
	}
}

/**
 * 类型擦除,边界处的动作
 */
class ArrayMaker<T> {
	private Class<T> kind;

	/**
	 * @param kind
	 */
	public ArrayMaker(Class<T> kind) {
		super();
		this.kind = kind;
	}
	
	@SuppressWarnings("unchecked")
	T[] create(int size) {
		return (T[]) Array.newInstance(kind, size);
	}
	
	public static void main(String[] args) {
		ArrayMaker<String> stringMaker = new ArrayMaker<String>(String.class);
		String[] stringArray = stringMaker.create(9);
		System.out.println(Arrays.toString(stringArray));
	}
}

class ListMaker<T> {
	List<T> create() {
		return new ArrayList<T>();
	}
	
	public static void main(String[] args) {
		ListMaker<String> stringMaker = new ListMaker<String>();
		@SuppressWarnings("unused")
		List<String> stringList = stringMaker.create();
	}
}

class FilledListMaker<T> {
	List<T> create(T t, int n) {
		List<T> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			result.add(t);
		}
		return result;
	}
	
	public static void main(String[] args) {
		FilledListMaker<String> stringMaker = new FilledListMaker<String>();
		List<String> list = stringMaker.create("Hello", 4);
		System.out.println(list);
	}
}