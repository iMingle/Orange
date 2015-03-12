package org.mingle.orange.hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationTest implements Serializable {
	private String name;
	private int age;
	
	public SerializationTest(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4945286490090693217L;

	public static void main(String[] args) {
		String filePath = "." + File.separator + "resource" + File.separator + "data.txt";
		
		SerializationTest st = new SerializationTest("Mingle", 26);
		
		File file = new File(filePath);
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			
			out.writeObject(st);
			
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			
			SerializationTest stTemp = (SerializationTest) in.readObject();
			
			System.out.println(stTemp.name);
			System.out.println(stTemp.age);
			
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
