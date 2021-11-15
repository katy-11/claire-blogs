package mvcpackage.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mvcpackage.model.bean.Login;

public class LoginDAO {
	public static boolean validate(Login loginBean) throws ClassNotFoundException {
		boolean status = false;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/claireblogs?serverTimezone=Australia/Melbourne", "root",
					"Minhkhang123");
			// Step 2:Create a statement using connection object
			ps = conn.prepareStatement("select * from userdb where uname = ? and upassword = ? ");
			ps.setString(1, loginBean.getUname());
			ps.setString(2, loginBean.getUpassword());
			// System.out.println(ps);
			rs = ps.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println("Message: " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					conn = null;
				}
			}
		}
		return status;
	}

}
