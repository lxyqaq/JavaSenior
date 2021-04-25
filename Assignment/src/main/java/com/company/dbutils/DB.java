package com.company.dbutils;

import com.company.pojo.Admin;
import com.company.pojo.Student;
import com.company.pojo.Teacher;

import java.sql.*;
import java.util.ResourceBundle;

public class DB {

    public static String jdbcDriver;
    public static String jdbcUrl;
    public static String jdbcUser;
    public static String jdbcPwd;
    public static Connection conn;

    // 连接数据库
    public static Connection connect() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
            jdbcDriver = bundle.getString("driverClass");
            jdbcUrl = bundle.getString("url");
            jdbcUser = bundle.getString("username");
            jdbcPwd = bundle.getString("password");
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 登录管理员账号
    public static boolean Login(Admin admin) {
        Connection connection = connect();
        String sql = "select * from user where account=? and password=?";
        PreparedStatement pre = null;
        boolean login = false;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, admin.getAccount());
            pre.setString(2, admin.getPassword());
            ResultSet set = pre.executeQuery();
            if (set.next()) {
                login = true;
            } else {
                login = false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (pre != null)
                    pre.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return login;
    }

    // 登录教师账号
    public static boolean teacherLogin(Teacher teacher) {
        Connection connection = connect();
        String sql = "select * from teacher where TeacherAccount=? and password=?";
        PreparedStatement pre = null;
        boolean login = false;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, teacher.getTeacherAccount());
            pre.setString(2, teacher.getPassword());
            ResultSet set = pre.executeQuery();
            if (set.next()) {
                login = true;
            } else {
                login = false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (pre != null)
                    pre.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return login;
    }

    // 登录学生账号
    public static boolean studentLogin(Student student) {
        Connection connection = connect();
        String sql = "select * from student where StudentAccount=? and password=?";
        PreparedStatement pre = null;
        boolean login = false;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, student.getStudentAccount());
            pre.setString(2, student.getPassword());
            ResultSet set = pre.executeQuery();
            if (set.next()) {
                login = true;
                student = new Student(Integer.valueOf(set.getString("ID")), set.getString("StudentAccount"),
                        set.getString("password"), set.getString("StudentName"), set.getString("StudentSex"));
            } else {
                login = false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (pre != null)
                    pre.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return login;
    }
}
