package com.company.impl;

import com.company.Bean.Laboratory;
import com.company.Bean.Teacher;
import com.company.DB.DB;
import com.company.Dao.TeacherDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherImpl implements TeacherDao {

    @Override
    public int addTeacher(Teacher teacher) {
        Connection connection = DB.connect();
        String sql = "insert  into teacher(TeacherAccount,password,TeacherName,TeacherSex) value (?,?,?,?)";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, teacher.getTeacherAccount());
            pre.setString(2, teacher.getPassword());
            pre.setString(3, teacher.getTeacherName());
            pre.setString(4, teacher.getTeacherSex());
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
    public List<Teacher> QueryTeacher(String teaname) {
        Connection connection = DB.connect();
        List<Teacher> teachers = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select  * from teacher");
        if (teaname != null && !"".equals(teaname)) {
            sql.append(" where TeacherName like ?");
        }
        PreparedStatement pre = null;
        ResultSet i = null;
        try {
            pre = connection.prepareStatement(sql.toString());
            if (teaname != null && !"".equals(teaname)) {
                pre.setString(1, "%" + teaname + "%");
            }
            i = pre.executeQuery();
            while (i.next()) {
                Teacher teacher = new Teacher(Integer.valueOf(i.getString("ID")), i.getString("TeacherAccount"), i.getString("password"), i.getString("TeacherName"), i.getString("TeacherSex"));
                teachers.add(teacher);
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
        return teachers;
    }

    @Override
    public int UpdateTeacher(Teacher teacher) {
        Connection connection = DB.connect();
        String sql = "update teacher set TeacherAccount=?,TeacherName=?,TeacherSex=? where ID=?";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, teacher.getTeacherAccount());
            pre.setString(2, teacher.getTeacherName());
            pre.setString(3, teacher.getTeacherSex());
            pre.setInt(4, teacher.getID());
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
    public int DeleteTeacher(String ID) {
        Connection connection = DB.connect();
        String sql = "delete from teacher where ID=?";
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
}
