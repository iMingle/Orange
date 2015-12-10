/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.factory;

/**
 * 用匿名内部类实现的工厂方法
 * 
 * @since 1.8
 * @author Mingle
 */
public class InnerClassFactory {
	public static void serviceConsumer(ServiceFactory factory) {
		Service service = factory.getService();
		service.service();
	}
	
	public static void main(String[] args) {
		InnerClassFactory.serviceConsumer(Implementation1.factory);
		InnerClassFactory.serviceConsumer(Implementation2.factory);
	}
}

interface Service {
	void service();
}

interface ServiceFactory {
	Service getService();
}

class Implementation1 implements Service {
	private Implementation1() {}
	public static ServiceFactory factory = new ServiceFactory() {
		
		@Override
		public Service getService() {
			return new Implementation1();
		}
	};

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Service#service()
	 */
	@Override
	public void service() {
		System.out.println("Implementation1 service you");
	}
	
}

class Implementation2 implements Service {
	private Implementation2() {}
	public static ServiceFactory factory = new ServiceFactory() {
		
		@Override
		public Service getService() {
			return new Implementation2();
		}
	};
	
	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Service#service()
	 */
	@Override
	public void service() {
		System.out.println("Implementation2 service you");
	}
	
}