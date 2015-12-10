/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 空对象处理
 *
 * @since 1.8
 * @author Mingle
 */
public class NullObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * 雇员
 */
class Staff extends ArrayList<Position> {
	private static final long serialVersionUID = 8160350862865412626L;

	public void add(String title, NPerson person) {
		add(new Position(title, person));
	}
	
	public void add(String... titles) {
		for (String title : titles)
			add(new Position(title));
	}
	
	public Staff(String... titles) {
		add(titles);
	}
	
	public boolean positionAvailable(String title) {
		for (Position position : this) {
			if (position.getTitle().equals(title) && position.getPerson() == NPerson.NULL)
				return true;
		}
		return false;
	}
	
	public void fillPosition(String title, NPerson hire) {
		for (Position position : this) {
			if (position.getTitle().equals(title) && position.getPerson() == NPerson.NULL) {
				position.setPerson(hire);
				return;
			}
		}
		throw new RuntimeException("Position " + title + " not available");
	}
	
	public static void main(String[] args) {
		Staff staff = new Staff("President", "CTO", "Marketing Manager",
				"Product Manager", "Project Lead", "Software Engineer",
				"Software Engineer", "Software Engineer", "Software Engineer",
				"Test Engineer", "Technical Writer");
		staff.fillPosition("President", new NPerson("Me", "Last",
				"The Top, Lonely At"));
		staff.fillPosition("Project Lead", new NPerson("Janet", "Planner",
				"The Burbs"));
		if (staff.positionAvailable("Software Engineer"))
			staff.fillPosition("Software Engineer", new NPerson("Bob", "Coder",
					"Bright Light City"));
		System.out.println(staff);
	}
}

/**
 * 职位
 */
class Position {
	private String title;
	private NPerson person;
	
	/**
	 * @param title
	 */
	public Position(String title) {
		super();
		this.title = title;
		this.person = NPerson.NULL;
	}

	/**
	 * @param title
	 * @param person
	 */
	@SuppressWarnings("static-access")
	public Position(String jobTitle, NPerson employee) {
		super();
		this.title = jobTitle;
		this.person = employee;
		if (person == null)
			person = person.NULL;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param newTtitle the title to set
	 */
	public void setTitle(String newTtitle) {
		this.title = newTtitle;
	}

	/**
	 * @return the person
	 */
	public NPerson getPerson() {
		return person;
	}

	/**
	 * @param newPerson the person to set
	 */
	public void setPerson(NPerson newPerson) {
		this.person = newPerson;
		if (person == null)
			person = NPerson.NULL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Position [title=" + title + ", person=" + person + "]";
	}
}

/**
 * 标记空对象的接口
 */
interface Null {}

/**
 * A class with a NULL object,人员
 */
class NPerson {
	public final String first;
	public final String last;
	public final String address;
	public static final NPerson NULL = new NullPerson();
	
	/**
	 * @param first
	 * @param last
	 * @param address
	 */
	public NPerson(String first, String last, String address) {
		super();
		this.first = first;
		this.last = last;
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [first=" + first + ", last=" + last + ", address="
				+ address + "]";
	}
	
	public static class NullPerson extends NPerson implements Null {

		/**
		 * @param first
		 * @param last
		 * @param address
		 */
		private NullPerson() {
			super("None", "None", "None");
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "NullPerson";
		}
		
	}
}

/**
 * 用接口取代具体类,那么可以使用DynamicProxy来自动创建空对象
 */
/**
 * 机器人的操作类
 */
interface Operation {
	/**
	 * 描述
	 * @return
	 */
	String description();
	/**
	 * 命令
	 */
	void command();
}

/**
 * 命令模式
 */
interface Robot {
	String name();
	String model();
	List<Operation> operations();
	
	class Test {
		public static void test(Robot r) {
			if (r instanceof Null) {
				System.out.println("[Null Robot]");
			}
			System.out.println("Robot name: " + r.name());
			System.out.println("Robot model: " + r.model());
			for (Operation operation : r.operations()) {
				System.out.println(operation.description());
				operation.command();
			}
		}
	}
}

/**
 * 清理雪机器人
 */
class SnowRemovalRobot implements Robot {
	private String name;

	/**
	 * @param name
	 */
	public SnowRemovalRobot(String name) {
		super();
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Robot#name()
	 */
	@Override
	public String name() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Robot#model()
	 */
	@Override
	public String model() {
		return "SnowBot Series l1";
	}

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.Robot#operations()
	 */
	@Override
	public List<Operation> operations() {
		return Arrays.asList(
				new Operation() {

					@Override
					public String description() {
						return name + " can shovel snow";
					}

					@Override
					public void command() {
						System.out.println(name + " shoveling snow");
					}
					
				},
				new Operation() {

					@Override
					public String description() {
						return name + " can chip ice";
					}

					@Override
					public void command() {
						System.out.println(name + " chipping ice");
					}
					
				},
				new Operation() {

					@Override
					public String description() {
						return name + " can clear the roof";
					}

					@Override
					public void command() {
						System.out.println(name + " clearing roof");
					}
					
				}
		);
	}
	
	public static void main(String[] args) {
		Robot.Test.test(new SnowRemovalRobot("Slusher"));
	}
}

class NullRobotProxyHandler implements InvocationHandler {
	private String nullName;
	private Robot proxied = new NRobot();
	
	public NullRobotProxyHandler(Class<? extends Robot> type) {
		nullName = type.getSimpleName() + " NullRobot";
	}

	private class NRobot implements Null, Robot {

		/* (non-Javadoc)
		 * @see org.mingle.orange.java.speciality.Robot#name()
		 */
		@Override
		public String name() {
			return nullName;
		}

		/* (non-Javadoc)
		 * @see org.mingle.orange.java.speciality.Robot#model()
		 */
		@Override
		public String model() {
			return nullName;
		}

		/* (non-Javadoc)
		 * @see org.mingle.orange.java.speciality.Robot#operations()
		 */
		@Override
		public List<Operation> operations() {
			return Collections.emptyList();
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return method.invoke(proxied, args);
	}
	
}

class NullRobot {
	public static Robot newNullRobot(Class<? extends Robot> type) {
		return (Robot) Proxy.newProxyInstance(
				NullRobot.class.getClassLoader(), 
				new Class[] { Null.class, Robot.class }, 
				new NullRobotProxyHandler(type));
	}
	
	public static void main(String[] args) {
		Robot[] bots = {
				new SnowRemovalRobot("SnowBee"),
				newNullRobot(SnowRemovalRobot.class)
		};
		
		for (Robot bot : bots)
			Robot.Test.test(bot);
	}
}