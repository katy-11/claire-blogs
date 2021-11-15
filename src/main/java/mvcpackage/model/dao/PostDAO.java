package mvcpackage.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvcpackage.model.bean.Post;

public class PostDAO {
	private String DBURL = "jdbc:mysql://localhost:3306/claireblogs?serverTimezone=Australia/Melbourne";
	private String DBUsername = "root";
	private String DBPassword = "Minhkhang123";
	private String SELECTALLPOSTS = "select * from POSTS order by DateIssue desc";
	private String SELECTRECENTPOSTS = "select * from POSTS order by DateIssue desc limit 3";
	private String SELECTCATEGORYPOSTS = "select * from POSTS where Category = ? ;";
	private String SELECTSEARCHPOSTS = "SELECT * FROM POSTS where ( title LIKE ? OR preview LIKE ?);";
	private String SELECTPOST = "select * from POSTS WHERE Pid = ?";
	private String INSERTPOST = "INSERT INTO posts (Title, ImageUrl, Category, DateIssue, Preview, Content) VALUES"
			+ "(?, ?, ?, ?, ?, ?)";
	private String DELETEPOST = "DELETE from POSTS where Pid = ?";

	// constructor
	public PostDAO() {
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

	public List<Post> selectAllPosts() {
		List<Post> p = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECTALLPOSTS);
			System.out.println(preparedStatement);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int Pid = rs.getInt("Pid");
				String Category = rs.getString("Category");
				String Title = rs.getString("Title");
				String Preview = rs.getString("Preview");
				String ImageUrl = rs.getString("ImageUrl");
				String DateIssue = rs.getString("DateIssue");

				p.add(new Post(Pid, Title, ImageUrl, Category, DateIssue, Preview));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finallySQLException(connection, preparedStatement, rs);
		}
		return p;
	}

	public List<Post> selectRecentPosts() {
		List<Post> p = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECTRECENTPOSTS);
			System.out.println(preparedStatement);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int Pid = rs.getInt("Pid");
				String Category = rs.getString("Category");
				String Title = rs.getString("Title");
				String Preview = rs.getString("Preview");
				String ImageUrl = rs.getString("ImageUrl");
				String DateIssue = rs.getString("DateIssue");

				p.add(new Post(Pid, Title, ImageUrl, Category, DateIssue, Preview));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finallySQLException(connection, preparedStatement, rs);
		}
		return p;
	}

	public List<Post> selectCategoryPosts(String category) {
		List<Post> p = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECTCATEGORYPOSTS);
			preparedStatement.setString(1, category);
			System.out.println(preparedStatement);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int Pid = rs.getInt("Pid");
				String Category = rs.getString("Category");
				String Title = rs.getString("Title");
				String Preview = rs.getString("Preview");
				String ImageUrl = rs.getString("ImageUrl");
				String DateIssue = rs.getString("DateIssue");

				p.add(new Post(Pid, Title, ImageUrl, Category, DateIssue, Preview));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finallySQLException(connection, preparedStatement, rs);
		}
		return p;
	}

	public List<Post> selectSearchPosts(String searchKey) {
		List<Post> p = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECTSEARCHPOSTS);
			preparedStatement.setString(1, "%" + searchKey + "%");
			preparedStatement.setString(2, "%" + searchKey + "%");

			System.out.println(preparedStatement);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int Pid = rs.getInt("Pid");
				String Category = rs.getString("Category");
				String Title = rs.getString("Title");
				String Preview = rs.getString("Preview");
				String ImageUrl = rs.getString("ImageUrl");
				String DateIssue = rs.getString("DateIssue");

				p.add(new Post(Pid, Title, ImageUrl, Category, DateIssue, Preview));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finallySQLException(connection, preparedStatement, rs);
		}
		return p;
	}

	public Post selectPost(int Pid) {
		Post p = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECTPOST);
			preparedStatement.setInt(1, Pid);

			System.out.println(preparedStatement);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String Category = rs.getString("Category");
				String Title = rs.getString("Title");
				String Preview = rs.getString("Preview");
				String ImageUrl = rs.getString("ImageUrl");
				String DateIssue = rs.getString("DateIssue");
				String Content = rs.getString("Content");

				p = new Post(Pid, Title, ImageUrl, Category, DateIssue, Preview, Content);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finallySQLException(connection, preparedStatement, rs);
		}
		return p;
	}

//	CRUD post
	public void insertPost(Post p) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// try-with-resource statement will auto close the connection.
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERTPOST);
			preparedStatement.setString(1, p.getTitle());
			preparedStatement.setString(2, p.getImageUrl());
			preparedStatement.setString(3, p.getCategory());
			preparedStatement.setString(4, p.getDateIssue());
			preparedStatement.setString(5, p.getPreview());
			preparedStatement.setString(6, p.getContent());
			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			finallySQLException(connection, preparedStatement, null);
		}
	}

	public boolean deletePost(int Pid) throws SQLException {
		boolean pDeleted = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(DELETEPOST);
			preparedStatement.setInt(1, Pid);
			pDeleted = preparedStatement.executeUpdate() > 0 ? true : false;
			System.out.println("deleted");
		} finally {
			finallySQLException(connection, preparedStatement, null);
		}
		return pDeleted;
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