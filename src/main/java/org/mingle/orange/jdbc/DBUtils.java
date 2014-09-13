package org.mingle.orange.jdbc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	/**
	 * 从文件中读取相关数据库属性
	 * @param path
	 */
	public static final void getProperties(String path) {
		Properties pro = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
//		InputStream in = DBUtils.class.getResourceAsStream(path);

		try {
			pro.load(in);
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return Connection
	 */
	public static final Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 关闭数据库
	 */
	public static final void closeDatabase(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (st != null) {
				st.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询操作
	 */
	public static final List<Student> sqlQuery(String sql) {
		List<Student> list = new ArrayList<Student>();
		String path = "." + File.separator + "resource" + File.separator + "db.properties";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Student student = null;
		
		getProperties(path);
		
		conn = getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setSex(rs.getString(3));
				student.setAge(rs.getInt(4));
				
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDatabase(conn, st, rs);
		}
		
		return list;
	}
	
	/**
	 * 增删改操作
	 */
	public static final void sqlUpdate(String sql) {
		String path = "." + File.separator + "resource" + File.separator + "db.properties";
		Connection conn = null;
		Statement st = null;
		
		try {
			getProperties(path);
			conn = getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			st.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeDatabase(conn, st, null);
		}
	}
	
	/**
	 * 数据库增删改查操作
	 */
	public static final List<Student> sqlDatabase(String sql) {
		if (sql.toLowerCase().indexOf("select") != -1) {
			return sqlQuery(sql);
		} else {
			sqlUpdate(sql);
			return null;
		}
	}
	
	public static void main(String[] args) {
		String sql = "select * from student";
//		String sqlInsert = "insert into student values(7," + "mingle," + "y," + "30)";
//		String sqlDelete = "delete from student where id = 7";
		
//		DBUtils.sqlUpdate(sqlDelete);
//		DBUtils.sqlDatabase(sqlInsert);
		List<Student> list = DBUtils.sqlQuery(sql);
		
		for (Student student : list) {
			System.out.println(student);
		}
		
	}
}
