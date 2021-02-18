package dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName DataBaseUtil
 * @Description Database tools
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/8 17:19
 * @Version 1.0
 */
public class DataBaseUtil {

    /**
     * @description Use Druid database connection pool technology
     * @param null
     * @return null
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/8 01:30
     */
    private static DataSource source;

    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = source.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

}
