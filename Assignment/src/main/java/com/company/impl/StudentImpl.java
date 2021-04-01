package com.company.impl;

import com.company.pojo.Student;
import com.company.dbutils.DB;
import com.company.dao.StudentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentImpl implements StudentDao {

    private Student student;

    @Override
    public int addStudent(Student student) {
        Connection connection = DB.connect();
        String sql = "insert  into student(StudentAccount,password,StudentName,StudentSex) value (?,?,?,?)";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, student.getStudentAccount());
            pre.setString(2, student.getPassword());
            pre.setString(3, student.getStudentName());
            pre.setString(4, student.getStudentSex());
            i = pre.executeUpdate();
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
        return i;
    }

    @Override
    public List<Student> QueryStudent(String stuname) {
        Connection connection = DB.connect();
        List<Student> students = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select  * from student");
        if (stuname != null && !"".equals(stuname)) {
            sql.append(" where StudentName like ?");
        }
        PreparedStatement pre = null;
        ResultSet i = null;
        try {
            pre = connection.prepareStatement(sql.toString());
            if (stuname != null && !"".equals(stuname)) {
                pre.setString(1, "%" + stuname + "%");
            }
            i = pre.executeQuery();
            while (i.next()) {
                student = new Student(Integer.valueOf(i.getString("ID")), i.getString("StudentAccount"), i.getString("password"), i.getString("StudentName"), i.getString("StudentSex"));
                students.add(student);
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
        return students;
    }

    @Override
    public int UpdateStudent(Student student) {
        Connection connection = DB.connect();
        String sql = "update student set StudentAccount=?,StudentName=?,StudentSex=? where ID=?";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, student.getStudentAccount());
            pre.setString(2, student.getStudentName());
            pre.setString(3, student.getStudentSex());
            pre.setInt(4, student.getID());
            i = pre.executeUpdate();
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
        return i;
    }

    @Override
    public int DeleteStudent(String ID) {
        Connection connection = DB.connect();
        String sql = "delete from student where ID=?";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, Integer.valueOf(ID));
            i = pre.executeUpdate();
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
        return i;
    }

    @Override
    public Student selectStudent(String account) {
        // TODO Auto-generated method stub
        Connection connection = DB.connect();
        Student student = null;
        String sql = "select  * from student where StudentAccount=?";
        PreparedStatement pre = null;
        ResultSet i = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, account);
            i = pre.executeQuery();
            while (i.next()) {
                student = new Student(Integer.valueOf(i.getString("ID")), i.getString("StudentAccount"), i.getString("password"), i.getString("StudentName"), i.getString("StudentSex"));
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
        return student;
    }
}
