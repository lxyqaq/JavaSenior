package com.company.impl;

import com.company.pojo.Reserve;
import com.company.dbutils.DB;
import com.company.dao.ReserveDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveImpl implements ReserveDao {

    @Override
    public int addReserve(Reserve reserve) {
        Connection connection = DB.connect();
        String sql = "insert  into reserve(laboratoryName,StudentName) value (?,?)";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, reserve.getLaboratoryName());
            pre.setString(2, reserve.getStudentName());
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
    public List<Reserve> QueryReserve(String labname) {
        Connection connection = DB.connect();
        List<Reserve> reserves = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from reserve");
        if (labname != null && !"".equals(labname)) {
            sql.append(" where laboratoryName like ?");
        }
        PreparedStatement pre = null;
        ResultSet i = null;
        try {
            pre = connection.prepareStatement(sql.toString());
            if (labname != null && !"".equals(labname)) {
                pre.setString(1, "%" + labname + "%");
            }
            i = pre.executeQuery();
            while (i.next()) {
                Reserve reserve = new Reserve(Integer.valueOf(i.getString("ID")), i.getString("laboratoryName"), i.getString("StudentName"), i.getString("States"), i.getString("result"));
                reserves.add(reserve);
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
        return reserves;
    }

    @Override
    public int UpdateReserve(String ID, String result) {
        Connection connection = DB.connect();
        String s = "Approved";
        String sql = "update reserve set States='" + s + "',result=?  where ID=?";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, result);
            pre.setInt(2, Integer.valueOf(ID));
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
    public int DeleteReserveByStudent(String student) {
        Connection connection = DB.connect();
        String sql = "delete from reserve where StudentName=?";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, student);
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
    public int DeleteReserveByLa(String laName) {
        Connection connection = DB.connect();
        String sql = "delete from reserve where laboratoryName=?";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, laName);
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
