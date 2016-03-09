/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @since 1.0
 * @author Mingle
 */
public class RandomFileTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Employee2[] staff = new Employee2[3];

        staff[0] = new Employee2("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee2("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee2("Tony Tester", 40000, 1990, 3, 15);

        
        try {
            // save all employee records to the file out_employee.txt
            DataOutputStream out = new DataOutputStream(new FileOutputStream(
                    new File(TextFileTest.class.getResource(
                            "/documents/out_employee.txt").toURI())));
            for (Employee2 e : staff)
                e.writeData(out);
            out.close();

            // retrieve all records into a new array
            RandomAccessFile in = new RandomAccessFile(new File(
                    TextFileTest.class.getResource(
                            "/documents/out_employee.txt").toURI()), "r");
            // compute the array size
            int n = (int) (in.length() / Employee2.RECORD_SIZE);
            Employee2[] newStaff = new Employee2[n];

            // read employees in reverse order
            for (int i = n - 1; i >= 0; i--) {
                newStaff[i] = new Employee2();
                in.seek(i * Employee2.RECORD_SIZE);
                newStaff[i].readData(in);
            }
            in.close();

            // print the newly read employee records
            for (Employee2 e : newStaff)
                System.out.println(e);
        } catch (URISyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class Employee2 {
    public static final int NAME_SIZE = 40;
    public static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;

    private String name;
    private double salary;
    private Date hireDay;

    public Employee2() {
    }

    public Employee2(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        hireDay = calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    /**
     * Raises the salary of this employee.
     * 
     * @byPercent the percentage of the raise
     */
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary
                + ",hireDay=" + hireDay + "]";
    }

    /**
     * Writes employee data to a data output
     * 
     * @param out the data output
     */
    public void writeData(DataOutput out) throws IOException {
        DataIO.writeFixedString(name, NAME_SIZE, out);
        out.writeDouble(salary);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(hireDay);
        out.writeInt(calendar.get(Calendar.YEAR));
        out.writeInt(calendar.get(Calendar.MONTH) + 1);
        out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Reads employee data from a data input
     * 
     * @param in the data input
     */
    public void readData(DataInput in) throws IOException {
        name = DataIO.readFixedString(NAME_SIZE, in);
        salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
        hireDay = calendar.getTime();
    }

}

class DataIO {
    public static String readFixedString(int size, DataInput in)
            throws IOException {
        StringBuilder b = new StringBuilder(size);
        int i = 0;
        boolean more = true;
        while (more && i < size) {
            char ch = in.readChar();
            i++;
            if (ch == 0)
                more = false;
            else
                b.append(ch);
        }
        in.skipBytes(2 * (size - i));
        return b.toString();
    }

    public static void writeFixedString(String s, int size, DataOutput out)
            throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length())
                ch = s.charAt(i);
            out.writeChar(ch);
        }
    }
}