package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import entity.StockHistory;

/**
 * @ClassName StockHistoryDao
 * @Description StockHistoryDao method
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 17:19
 * @Version 1.0
 */
public class StockHistoryDao {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ProductDao pdao = new ProductDao();

    /**
     * @return java.util.Vector<entity.StockHistory>
     * @throws
     * @description findAllHistory methood
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:19
     */
    public Vector<StockHistory> findAllHistory() {
        Vector<StockHistory> ret = new Vector<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("select * from stock_history order by stock_date desc");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StockHistory sh = new StockHistory();
                sh.setSid(rs.getInt("sh_id"));
                sh.setProductId(rs.getInt("product_id"));
                sh.setProduct(pdao.findProduct(sh.getProductId()));
                sh.setStockDate(sdf.parse(rs.getString("stock_date")));
                sh.setQuantity(rs.getInt("quantity"));
                ret.add(sh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt, rs);
        }
        return ret;
    }

    /**
     * @param sh
     * @return void
     * @throws
     * @description saveStockHistory method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:20
     */
    public void saveStockHistory(StockHistory sh) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into stock_history(product_id, stock_date ,quantity) values(?,?,?)";
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sh.getProductId());
            pstmt.setString(2, sdf.format(new Date()));
            pstmt.setInt(3, sh.getQuantity());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt);
        }
    }

}
