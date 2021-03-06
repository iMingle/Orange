/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.orange.java.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author mingle
 */
public class TextFileTest {

    public static void main(String[] args) {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
        
        try {
            // save all employee records to the file out_employee.txt
            PrintWriter out = new PrintWriter(new File(
                    TextFileTest.class.getResource(
                    "/documents/out_employee.txt").toURI()));
            TextFileTest.writeData(staff, out);
            out.close();
            
            // retrieve all records into a new array
            Scanner in = new Scanner(new File(
                    TextFileTest.class.getResource(
                    "/documents/out_employee.txt").toURI()));
            Employee[] newStaff = TextFileTest.readData(in);
            
            // print the newly read employee records
            for (Employee e : newStaff)
                System.out.println(e);
        } catch (FileNotFoundException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Writes all employees in an array to a print writer
     * @param employees an array of employees
     * @param out a print writer
     */
    private static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);
        
        for (Employee e : employees)
            e.writeData(out);
    }

    /**
     * Reads an array of employees from a scanner
     * @param in the scanner
     * @return the array of employees
     */
    private static Employee[] readData(Scanner in) {
        // retrieve the array size
        int n = in.nextInt();
        in.nextLine();
        
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = new Employee();
            employees[i].readData(in);
        }
        
        return employees;
    }
}

class Employee {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String n, double s, int year, int month, int day) {
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

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary
                + ",hireDay=" + hireDay + "]";
    }

    /**
     * Writes employee data to a print writer
     * 
     * @param out the print writer
     */
    public void writeData(PrintWriter out) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(hireDay);
        out.println(name + "|" + salary + "|" + calendar.get(Calendar.YEAR)
                + "|" + (calendar.get(Calendar.MONTH) + 1) + "|"
                + calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Reads employee data from a buffered reader
     * 
     * @param in the scanner
     */
    public void readData(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        name = tokens[0];
        salary = Double.parseDouble(tokens[1]);
        int y = Integer.parseInt(tokens[2]);
        int m = Integer.parseInt(tokens[3]);
        int d = Integer.parseInt(tokens[4]);
        GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
        hireDay = calendar.getTime();
    }

}
