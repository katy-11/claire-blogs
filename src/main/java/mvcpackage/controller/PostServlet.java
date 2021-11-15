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
import javax.servlet.http.HttpSession;

import mvcpackage.model.dao.CategoryDAO;
import mvcpackage.model.dao.PostDAO;
import mvcpackage.model.bean.Category;
import mvcpackage.model.bean.Post;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet({ "/post", "/home" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO pDAO; // Define as instance variable
	private CategoryDAO cDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		pDAO = new PostDAO();
		cDAO = new CategoryDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		try {
			if (id != null) {
				selectPost(request, response);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		if (action == null) {
			action = "No action";
		}
		RequestDispatcher dispatcher;
		try {
			switch (action) {
			case "html":
				listCategoryPost(request, response);
			case "php":
				listCategoryPost(request, response);
			case "java":
				listCategoryPost(request, response);
			case "listRecentPost":
				listRecentPost(request, response);
			case "listPost":
				listPost(request, response);
			default:
				listRecentPost(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}// End of doPost method

	private void listPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Post> listPost = pDAO.selectAllPosts();
		request.setAttribute("listPost", listPost);
		request.setAttribute("listCategory", "All Posts");
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

	private void listRecentPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Post> listPost = pDAO.selectRecentPosts();
		List<Category> countCategory = cDAO.selectAllCategories();

		HttpSession session = request.getSession();
		session.setAttribute("countCategory", countCategory);
		request.setAttribute("listPost", listPost);
		request.setAttribute("listCategory", "3 Recent Posts");
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

	private void listCategoryPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String category = request.getParameter("action");
		List<Post> listPost = pDAO.selectCategoryPosts(category);
		request.setAttribute("listPost", listPost);
		request.setAttribute("listCategory", "All " + category.toUpperCase() + " Posts");
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

	private void selectPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int Pid = Integer.parseInt(request.getParameter("id"));
		Post selectedPost = pDAO.selectPost(Pid);
		request.setAttribute("selectedPost", selectedPost);
		RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
		dispatcher.forward(request, response);
	}

}
