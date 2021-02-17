package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库工具类
 *
 * @author xujinnan
 */
public class DataBaseUtil {

    //测试方法
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from product;");// 查询数据
            while (rs.next()) {// 将查询到的数据打印出来
                System.out.print("name = " + rs.getString("name") + " ");// 列属性一
                System.out.println("productNo = " + rs.getString("product_no"));// 列属性二
            }
            rs.close();
            conn.close();// 结束数据库的连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Connection的工具方法
     *
     * @return
     */
    public static Connection getConnection()  {

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        try {
            pros.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return conn;

    }
}
