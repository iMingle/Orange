package org.mingle.orange.hello;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class IOTest {

	public static void main(String[] args) {
		String file = "." + File.separator + "resource" + File.separator + "data.txt";

		FileOutputStream outFile = null;
		FileInputStream inFile = null;
		try {
			outFile = new FileOutputStream(file);
			
			outFile.write(97);
			outFile.write(98);
			outFile.write(99);
			
//			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			inFile = new FileInputStream(file);
			
			System.out.println(inFile.read());
			System.out.println(inFile.read());
			System.out.println(inFile.read());
	System.out.println("@@@@@@@@@@[FileInputStream]");
//			inFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedOutputStream outBuffer = new BufferedOutputStream(outFile);
		
		try {
			outBuffer.write(100);
			outBuffer.write(101);
			outBuffer.write(102);
			
			outBuffer.flush();
//			outBuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedInputStream inBuffer = null;
		
		inBuffer = new BufferedInputStream(inFile);
		try {
			System.out.println(inBuffer.read());
			System.out.println(inBuffer.read());
			System.out.println(inBuffer.read());
			System.out.println("@@@@@@@@@@[BufferedInputStream]");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			
		
		DataOutputStream outData = new DataOutputStream(outBuffer);
		
		try {
			outData.writeInt(100);
			outData.writeInt(101);
			outData.writeInt(102);
			outData.writeUTF("Hello");
			outData.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DataInputStream inData = new DataInputStream(inBuffer);
		
		try {
			System.out.println(inData.readInt());
			System.out.println(inData.readInt());
			System.out.println(inData.readInt());
			System.out.println(inData.readUTF());
			System.out.println("@@@@@@@@@@[DataInputStream]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		OutputStreamWriter outWriter = null;
		try {
			outWriter = new OutputStreamWriter(outFile, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		try {
			outWriter.write("Jin");
			outWriter.write("Ming");
			outWriter.write("Lei");
			outWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStreamReader inReader = null;
		try {
			inReader = new InputStreamReader(inFile, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		try {
			int ch = -1;
			while ((ch = inReader.read()) != -1) {
				System.out.print((char)ch);
			}
			System.out.println();

			System.out.println("@@@@@@@@@@[InputStreamReader]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedWriter bufferWriter = new BufferedWriter(outWriter);
		
		try {
			bufferWriter.write("WangJianzong");
			bufferWriter.newLine();
			bufferWriter.write("YangJingang");
			
			bufferWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader bufferReader = new BufferedReader(inReader);

		try {
			String str = null;
			
			System.out.println(bufferReader.readLine());
			
			while ((str = bufferReader.readLine()) != null) {
				System.out.println(str);
			}
			
			System.out.println("@@@@@@@@@@[BufferedReader]");
		} catch (IOException e) {
			e.printStackTrace();
		}
/*		
		try {
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write("FileWriter");
			fileWriter.flush();
			
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			int ch = -1;
			FileReader fileReader = new FileReader(file);
			
			while ((ch = fileReader.read()) != -1) {
				System.out.print((char)ch);
			}
			System.out.println();
			System.out.println("@@@@@@@@@@[FileReader]");
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
/*		
		try {
			PrintWriter printWriter = new PrintWriter(file);
			
			printWriter.println("PrintWriter");
			printWriter.println("PrintWriter2");
			
			printWriter.flush();
			
			PrintStream printStream = System.out;
			
			PrintStream prin = new PrintStream(new FileOutputStream(file));
			
			System.setOut(prin);
			
			System.out.println("Hello World!");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	
	}
}
