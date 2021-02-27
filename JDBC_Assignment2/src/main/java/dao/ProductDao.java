package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import entity.Product;

/**
 * @ClassName ProductDao
 * @Description ProductDao method
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/9 17:19
 * @Version 1.0
 */
public class ProductDao {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @return java.util.Vector<entity.Product>
     * @throws
     * @description findAllProduct method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 13:51
     */
    public Vector<Product> findAllProduct() {
        Vector<Product> ret = new Vector<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("select * from product");
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
     * @param cid
     * @return java.util.Vector<entity.Product>
     * @throws
     * @description findProductByCategory method by id
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 13:54
     */
    public Vector<Product> findProductByCategory(int cid) {
        Vector<Product> ret = new Vector<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("select * from product where catogery=?");
            pstmt.setInt(1, cid);
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
     * @param cid
     * @param order
     * @return java.util.Vector<entity.Product>
     * @throws
     * @description findProductByCategory method by order
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 13:55
     */
    public Vector<Product> findProductByCategory(int cid, String order) {
        Vector<Product> ret = new Vector<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("select * from product where catogery=? order by storage " + order);
            pstmt.setInt(1, cid);
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
     * @param no
     * @return entity.Product
     * @throws
     * @description findProductByNo method by product_no
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:00
     */
    public Product findProductByNo(String no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("select * from product where product_no=?");
            Product p = null;
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                p = new Product();
                p.setCatogery(rs.getInt("catogery"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setProductId(rs.getInt("product_id"));
                p.setProductNo(rs.getString("product_no"));
                p.setPurPrice(rs.getDouble("pur_price"));
                p.setStockDate(sdf.parse(rs.getString("stock_date")));
                p.setStorage(rs.getInt("storage"));
                p.setAlarmStorage(rs.getInt("alarm_storage"));
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DataBaseUtil.closeResource(conn, pstmt, rs);
        }
    }

    /**
     * @param order
     * @return java.util.Vector<entity.Product>
     * @throws
     * @description findAllProduct method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:01
     */
    public Vector<Product> findAllProduct(String order) {
        Vector<Product> ret = new Vector<Product>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("select * from product order by storage " + order);
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
     * @param product
     * @return void
     * @throws
     * @description saveProduct method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:02
     */
    public void saveProduct(Product product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into product(product_no, name ,catogery,price, pur_price, stock_date, storage, alarm_storage) values(?,?,?,?,?,?,?,?)";
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductNo());
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getCatogery());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setDouble(5, product.getPurPrice());
            pstmt.setString(6, sdf.format(new Date()));
            pstmt.setInt(7, product.getStorage());
            pstmt.setInt(8, product.getAlarmStorage());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt);
        }
    }

    /**
     * @param pid
     * @return entity.Product
     * @throws
     * @description saveProduct method by id
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:02
     */
    public Product findProduct(int pid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("select * from product where product_id=?");
            pstmt.setInt(1, pid);
            rs = pstmt.executeQuery();
            Product p = new Product();
            while (rs.next()) {
                p.setCatogery(rs.getInt("catogery"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setProductId(rs.getInt("product_id"));
                p.setProductNo(rs.getString("product_no"));
                p.setPurPrice(rs.getDouble("pur_price"));
                p.setStockDate(sdf.parse(rs.getString("stock_date")));
                p.setStorage(rs.getInt("storage"));
                p.setAlarmStorage(rs.getInt("alarm_storage"));
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt, rs);
        }
        return null;
    }

    /**
     * @param pid
     * @return void
     * @throws
     * @description deleteProduct method by id
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:04
     */
    public void deleteProduct(int pid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("delete from product where product_id=?");
            pstmt.setInt(1, pid);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt);
        }
    }

    /**
     * @param productId
     * @param storage
     * @return void
     * @throws
     * @description updateStorage method by id
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:09
     */
    public void updateStorage(int productId, int storage) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Product p = this.findProduct(productId);
        int ns = p.getStorage() + storage;
        String sql = "update product set storage=? where product_id=?";
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ns);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt);
        }
    }

    /**
     * @param product
     * @return void
     * @throws
     * @description updateProduct method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:09
     */
    public void updateProduct(Product product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update product set product_no=?, name=? ,catogery=?, price=?, pur_price=?, stock_date=?, storage=?, alarm_storage=? where product_id=?";
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductNo());
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getCatogery());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setDouble(5, product.getPurPrice());
            pstmt.setString(6, sdf.format(new Date()));
            pstmt.setInt(7, product.getStorage());
            pstmt.setInt(8, product.getAlarmStorage());
            pstmt.setInt(9, product.getProductId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt);
        }
    }

}
