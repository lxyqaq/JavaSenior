package com.company.impl;

import com.company.Bean.Laboratory;
import com.company.DB.DB;
import com.company.Dao.LaboratoryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryImpl implements LaboratoryDao {

    public LaboratoryImpl() {
    }

    @Override
    public int addLaboratoryDao(Laboratory laboratory) {
        Connection connection = DB.connect();
        String sql = "insert  into laboratory(laboratoryName,freeTime,function) value (?,?,?)";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, laboratory.getLaboratoryName());
            pre.setString(2, laboratory.getFreeTime());
            pre.setString(3, laboratory.getFunction());
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
    public List<Laboratory> QueryLaboratory(String laboratoryName) {
        Connection connection = DB.connect();
        List<Laboratory> laboratories = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from laboratory");
        if (laboratoryName != null && !"".equals(laboratoryName)) {
            sql.append(" where laboratoryName like ?");
        }
        PreparedStatement pre = null;
        ResultSet i = null;
        try {
            pre = connection.prepareStatement(sql.toString());
            if (laboratoryName != null && !"".equals(laboratoryName)) {
                pre.setString(1, "%" + laboratoryName + "%");
            }
            i = pre.executeQuery();
            while (i.next()) {
                Laboratory laboratory = new Laboratory(Integer.valueOf(i.getString("ID")), i.getString("laboratoryName"), i.getString("freeTime"), i.getString("function"), i.getString("states"));
                laboratories.add(laboratory);
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
        return laboratories;
    }

    @Override
    public int UpdateLaboratory(Laboratory laboratory) {
        Connection connection = DB.connect();
        String sql = "update laboratory set laboratoryName=?,freeTime=?,function=?,states=? where ID=?";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, laboratory.getLaboratoryName());
            pre.setString(2, laboratory.getFreeTime());
            pre.setString(3, laboratory.getFunction());
            pre.setString(4, laboratory.getStates());
            pre.setInt(5, laboratory.getID());
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
    public int UpdateLaboratoryStates(String laboratoryName) {
        Connection connection = DB.connect();
        String s = "ռ��";
        System.out.println(laboratoryName);
        String sql = "update laboratory set states='" + s + "' where laboratoryName=?";
        PreparedStatement pre = null;
        int i = 0;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, laboratoryName);
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
    public int DeleteLaboratory(String ID) {
        Connection connection = DB.connect();
        String sql = "delete from laboratory where ID=?";
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
