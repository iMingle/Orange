package org.mingle.orange.util.database.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    static {
        Properties properties = new Properties();
        try {
            properties.load(DBUtils.class.getResourceAsStream("/db.properties"));
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void createTable() {
        Connection conn = null;
        PreparedStatement ps = null;
        
        conn = getConnection();
        String sql = "CREATE TABLE student(id INTEGER, name VARCHAR2(20), sex VARCHAR2(2), age INTEGER);";
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabase(conn, ps, null);
        }
    }
    
    /**
     * 获取数据库连接
     * 
     * @return Connection
     */
    public static Connection getConnection() {
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
    public static void closeDatabase(Connection conn, Statement st, ResultSet rs) {
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
    public static List<Student> sqlQuery(String sql) {
        List<Student> list = new ArrayList<Student>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;
        
        conn = getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

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
            closeDatabase(conn, ps, rs);
        }
        
        return list;
    }
    
    /**
     * 增删改操作
     */
    public static void sqlUpdate(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            closeDatabase(conn, ps, null);
        }
    }
    
    /**
     * 数据库增删改查操作
     */
    public static List<Student> sqlDatabase(String sql) {
        if (sql.toLowerCase().indexOf("select") != -1) {
            return sqlQuery(sql);
        } else {
            sqlUpdate(sql);
            return null;
        }
    }
    
    public static void main(String[] args) {
        createTable();
        String sql = "select * from student";
        String sqlInsert = "insert into student values(1, 'mingle', 'y', 20)";
        String sqlInsert1 = "insert into student values(2, 'ooo', 'x', 18)";
        String sqlDelete = "delete from student where id = 2";
        
        DBUtils.sqlUpdate(sqlInsert);
        DBUtils.sqlDatabase(sqlInsert1);
        
        List<Student> list = DBUtils.sqlQuery(sql);
        
        for (Student student : list) {
            System.out.println(student);
        }
        
        DBUtils.sqlUpdate(sqlDelete);
        
        list = DBUtils.sqlQuery(sql);
        
        for (Student student : list) {
            System.out.println(student);
        }
    }
}
