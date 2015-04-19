/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试类
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class DynamicProxyTest {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("bonobo");
	}
	
	public static void main(String[] args) {
//		consumer(new RealObject());
//		consumer(new SimpleProxy(new RealObject()));
		RealObject real = new RealObject();
		consumer(real);
		// Insert a proxy and call again:
		Interface proxy = (Interface) Proxy.newProxyInstance(
				Interface.class.getClassLoader(), 
				new Class[] { Interface.class }, 
				new DynamicProxyHandler(real));
		consumer(proxy);
	}
}

class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;

	/**
	 * @param proxied
	 */
	public DynamicProxyHandler(Object proxied) {
		super();
		this.proxied = proxied;
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args) ;
		if (args != null) {
			for (Object arg : args) {
				System.out.println(" " + arg);
			}
		}
		return method.invoke(proxied, args);
	}
	
}

/**
 * 方法选择
 */
class MethodSelector implements InvocationHandler {
	private Object proxied;

	/**
	 * @param proxied
	 */
	public MethodSelector(Object proxied) {
		super();
		this.proxied = proxied;
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if (method.getName().equals("interesting")) {
			System.out.println("Proxy detected the interesting method");
		}
		return method.invoke(proxied, args);
	}
	
}

interface SomeMethods {
	void boring1();
	void boring2();
	void interesting(String arg);
	void boring3();
}

class Implementation implements SomeMethods {

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.SomeMethods#boring1()
	 */
	@Override
	public void boring1() {
		System.out.println("boring1");
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.SomeMethods#boring2()
	 */
	@Override
	public void boring2() {
		System.out.println("boring2");
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.SomeMethods#interesting(java.lang.String)
	 */
	@Override
	public void interesting(String arg) {
		System.out.println("interesting " + arg);
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.SomeMethods#boring3()
	 */
	@Override
	public void boring3() {
		System.out.println("boring3");
	}
	
}

/**
 * 方法选择测试
 */
class SelectingMethods {
	public static void main(String[] args) {
		SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(
				SomeMethods.class.getClassLoader(), 
				new Class[] { SomeMethods.class }, 
				new MethodSelector(new Implementation()));
		proxy.boring1();
		proxy.boring2();
		proxy.interesting("bonobo");
		proxy.boring3();
	}
}

interface Interface {
	void doSomething();
	void somethingElse(String arg);
}

class RealObject implements Interface {

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Interface#doSomething()
	 */
	@Override
	public void doSomething() {
		System.out.println("doSomething");
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Interface#somethingElse(java.lang.String)
	 */
	@Override
	public void somethingElse(String arg) {
		System.out.println("somethingElse " + arg);
	}
	
}

/**
 * 简单代理
 */
class SimpleProxy implements Interface {
	private Interface proxied;

	/**
	 * @param proxied
	 */
	public SimpleProxy(Interface proxied) {
		super();
		this.proxied = proxied;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Interface#doSomething()
	 */
	@Override
	public void doSomething() {
		System.out.println("SimpleProxy doSomething");
		proxied.doSomething();
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Interface#somethingElse(java.lang.String)
	 */
	@Override
	public void somethingElse(String arg) {
		System.out.println("SimpleProxy somethingElse " + arg);
		proxied.somethingElse(arg);
	}
	
}
