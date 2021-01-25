package day01;

import bean.Customer;
import org.junit.Test;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @ClassName CustomerForQuery
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/25 23:36
 * @Version 1.0
 */
public class CustomerForQuery {

    @Test
    public void test1() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 1);

            //执行，返回结果集
            resultSet = ps.executeQuery();

            //处理结果集
            if (resultSet.next()) {

                //获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, resultSet);
        }

    }

}
