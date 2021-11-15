package mvcpackage.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvcpackage.model.bean.Category;
import mvcpackage.model.bean.Post;

public class CategoryDAO {
	private String DBURL = "jdbc:mysql://localhost:3306/claireblogs?serverTimezone=Australia/Melbourne";
	private String DBUsername = "root";
	private String DBPassword = "Minhkhang123";
	private String SELECTALLCATEGORIES = "select * from CATEGORIES";
	private String INSERTCATEGORY = "INSERT INTO categories (CategoryName) VALUES (" + "?" + ")";

	// constructor
	public CategoryDAO() {
	}

	// connection
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public List<Category> selectAllCategories() {
		List<Category> c = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECTALLCATEGORIES);
			System.out.println(preparedStatement);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int CategoryId = rs.getInt("CategoryId");
				String CategoryName = rs.getString("CategoryName");

				c.add(new Category(CategoryName));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finallySQLException(connection, preparedStatement, rs);
		}
		return c;
	}

	public void insertCategory(Category c) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// try-with-resource statement will auto close the connection.
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERTCATEGORY);
			preparedStatement.setString(1, c.getCategoryName());
			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			finallySQLException(connection, preparedStatement, null);
		}
	}

	// SQL Exception
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	private void finallySQLException(Connection c, PreparedStatement p, ResultSet r) {
		if (r != null) {
			try {
				r.close();
			} catch (Exception e) {
			}
			r = null;
		}
		if (p != null) {
			try {
				p.close();
			} catch (Exception e) {
			}
			p = null;
		}
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				c = null;
			}
		}
	}
}
