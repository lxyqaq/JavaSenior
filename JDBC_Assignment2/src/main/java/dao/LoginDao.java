package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName LoginDao
 * @Description LoginDao method
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/9 17:19
 * @Version 1.0
 */
public class LoginDao {

    /**
     * @return java.lang.String
     * @throws
     * @description get Password
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 13:48
     */
    public static String getPwd() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("select * from password");
            rs = pstmt.executeQuery();
            String pwd = null;
            while (rs.next()) {
                pwd = rs.getString("pwd");
            }
            return pwd;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            DataBaseUtil.closeResource(conn, pstmt, rs);
        }
    }

    /**
     * @param newPwd
     * @return void
     * @throws
     * @description update Password
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/18 13:49
     */
    public static void updatePwd(String newPwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement("update password set pwd=?");
            pstmt.setString(1, newPwd);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeResource(conn, pstmt);
        }
    }

}
