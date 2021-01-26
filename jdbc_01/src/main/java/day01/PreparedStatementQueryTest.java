package day01;

import bean.Customer;
import org.junit.Test;
import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @ClassName PreparedStatementQueryTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/26 21:33
 * @Version 1.0
 */
public class PreparedStatementQueryTest {

    /**
     * @param clazz
     * @param sql
     * @param args
     * @return T
     * @throws
     * @description 通用的针对于不同表的查询:返回一个对象 (version 1.0)
     * @author lxyqaq @email A00279565@student.ait.ie
     * @date 2021/1/26 21:37
     */
    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.获取数据库连接
            conn = JDBCUtils.getConnection();

            // 2.预编译sql语句，得到PreparedStatement对象
            ps = conn.prepareStatement(sql);

            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 4.执行executeQuery(),得到结果集：ResultSet
            rs = ps.executeQuery();

            // 5.得到结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();

            // 6.1通过ResultSetMetaData得到columnCount,columnLabel；通过ResultSet得到列值
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {// 遍历每一个列

                    // 获取列值
                    Object columnVal = rs.getObject(i + 1);
                    // 获取列的别名:列的别名，使用类的属性名充当
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 6.2使用反射，给对象的相应属性赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnVal);

                }
                return t;
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            // 7.关闭资源
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;

    }

    @Test
    public void test1() {

        String sql = "select id, name, email from customers where id = ?";
        Customer customer = getInstance(Customer.class, sql, 12);
        System.out.println(customer);

    }


    /**
     * @param sql
     * @param args
     * @return void
     * @throws
     * @description 通用的增、删、改操作(体现一：增、删、改 ； 体现二：针对于不同的表)
     * @author lxyqaq @email A00279565@student.ait.ie
     * @date 2021/1/26 21:41
     */
    public void update(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2.获取PreparedStatement的实例 (或：预编译sql语句)
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //4.执行sql语句
            ps.execute();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            //5.关闭资源
            JDBCUtils.closeResource(conn, ps);

        }
    }

}
