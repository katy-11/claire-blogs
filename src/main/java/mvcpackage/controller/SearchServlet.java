package mvcpackage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcpackage.model.bean.Post;
import mvcpackage.model.dao.PostDAO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO pDAO; // Define as instance variable

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		pDAO = new PostDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			listSearch(request, response);

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}// End of doPost method

	RequestDispatcher dispatcher;

	private void listSearch(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String searchKey = request.getParameter("q");
		List<Post> listPost = pDAO.selectSearchPosts(searchKey);
		request.setAttribute("listPost", listPost);
		request.setAttribute("listCategory", "Result search for " + searchKey);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

}
