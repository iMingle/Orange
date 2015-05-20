/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.object;

/**
 * hashCode和equals方法如何写
 * 
 * @since 1.8
 * @author Mingle
 */
public class HashCodeEquals {
	private int age;
	private String name;
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashCodeEquals other = (HashCodeEquals) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static void main(String[] args) {
		System.out.println(Integer.valueOf(1) == Integer.valueOf(1));
		System.out.println(Integer.valueOf(1000) == Integer.valueOf(1000));
		
		System.out.println(Class.class.isInstance(new Object()));
		String s = new String("abc").intern();
		System.out.println(s.intern() == "abc");
		System.out.println(new String().intern() == new String().intern());
		System.out.println("a".hashCode());
	}
}
