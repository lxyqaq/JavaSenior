package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import entity.SellHistory;

/**
 * @ClassName SellHistoryDao
 * @Description SellHistoryDao method
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 17:19
 * @Version 1.0
 */
public class SellHistoryDao {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    ProductDao pdao = new ProductDao();

    /**
     * @return java.util.Vector<entity.SellHistory>
     * @throws
     * @description findAllHistory method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:12
     */
    public Vector<SellHistory> findAllHistory() {
        Vector<SellHistory> ret = new Vector<SellHistory>();

        Connection conn = DataBaseUtil.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from sell_history order by sell_date desc");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SellHistory sh = new SellHistory();
                sh.setShId(rs.getInt("sh_id"));
                sh.setProductId(rs.getInt("product_id"));
                sh.setProduct(pdao.findProduct(sh.getProductId()));
                sh.setSellDate(sdf.parse(rs.getString("sell_date")));
                sh.setQuantity(rs.getInt("quantity"));
                ret.add(sh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * @param sh
     * @return void
     * @throws
     * @description saveSellHistory method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 14:18
     */
    public void saveSellHistory(SellHistory sh) {
        Connection conn = DataBaseUtil.getConnection();
        String sql = "insert into sell_history(product_id, sell_date ,quantity) values(?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sh.getProductId());
            pstmt.setString(2, sdf.format(new Date()));
            pstmt.setInt(3, sh.getQuantity());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
