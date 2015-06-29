/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 序列化
 * 
 * @since 1.8
 * @author Mingle
 */
public class Serializable {

	public static void main(String[] args) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			Foo foo = new Foo(5, 5);
			oos.writeObject(foo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		try {
			ObjectInputStream ois = new ObjectInputStream(bais);
			Foo f = (Foo) ois.readObject();
			System.out.println(f.getX());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

abstract class AbstractFoo {
	private int x;
	private int y;
	
	// 跟踪初始化信息
	private enum State {
		NEW, INITIALIZING, INITIALIZED
	}
	private final AtomicReference<State> init = 
			new AtomicReference<AbstractFoo.State>(State.NEW);
	
	public AbstractFoo(int x, int y) {
		initialize(x, y);
	}
	
	// 此构造方法和下面的方法允许父类的readObject方法初始化自己的状态
	protected AbstractFoo() {}
	protected final void initialize(int x, int y) {
		if (!init.compareAndSet(State.NEW, State.INITIALIZING))
			throw new IllegalStateException("Already initialized");
		this.x = x;
		this.y = y;
		init.set(State.INITIALIZED);
	}
	
	// 提供访问内部状态
	protected final int getX() {
		checkInit();
		return x;
	}
	
	protected final int getY() {
		checkInit();
		return y;
	}
	
	// 所有的public或protected方法必须调用
	private void checkInit() {
		if (init.get() != State.INITIALIZED)
			throw new IllegalStateException("Uninitialized");
	}
}

class Foo extends AbstractFoo implements java.io.Serializable {
	private static final long serialVersionUID = 3968193554302599953L;
	
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		// 手动反序列化并初始化父类的状态
		int x = in.readInt();
		int y = in.readInt();
		initialize(x, y);
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		// 手动序列化父类的状态
		out.writeInt(getX());
		out.writeInt(getY());
	}
	
	public Foo(int x, int y) {
		super(x, y);
	}
}