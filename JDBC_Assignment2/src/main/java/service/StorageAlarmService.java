package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import dao.DataBaseUtil;
import dao.ProductDao;
import entity.Product;

/**
 * @ClassName StorageAlarmService
 * @Description StorageAlarmService method
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 22:19
 * @Version 1.0
 */
public class StorageAlarmService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @return boolean
     * @throws
     * @description Check if the stock of the product is below the warning value
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:34
     */
    public boolean checkStorage() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select count(*) from product p where p.storage<p.alarm_storage";
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            boolean ret = false;
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    ret = true;
                }
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DataBaseUtil.closeResource(conn, pstmt, rs);
        }
    }

    /**
     * @return java.util.Vector<entity.Product>
     * @throws
     * @description Find all out of stock items
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:34
     */
    public Vector<Product> findAlarmProduct() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from product p0 where p0.product_id in(select product_id from product p where p.storage<p.alarm_storage)";
        Vector<Product> ret = new Vector<>();
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setCatogery(rs.getInt("catogery"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setProductId(rs.getInt("product_id"));
                p.setProductNo(rs.getString("product_no"));
                p.setPurPrice(rs.getDouble("pur_price"));
                p.setStockDate(sdf.parse(rs.getString("stock_date")));
                p.setStorage(rs.getInt("storage"));
                p.setAlarmStorage(rs.getInt("alarm_storage"));
                ret.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt, rs);
        }
        return ret;
    }

    /**
     * @param pid
     * @return boolean
     * @throws
     * @description After the merchandise is sold, check whether its inventory has fallen below the threshold in time, and give a reminder if so
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:34
     */
    public boolean checkProductStorage(int pid) {
        ProductDao dao = new ProductDao();
        Product p = dao.findProduct(pid);
        if (p.getStorage() <= p.getAlarmStorage()) {
            return true;
        } else {
            return false;
        }
    }

}
