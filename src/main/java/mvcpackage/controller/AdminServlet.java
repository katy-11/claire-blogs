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

import mvcpackage.model.bean.Category;
import mvcpackage.model.bean.Post;
import mvcpackage.model.dao.CategoryDAO;
import mvcpackage.model.dao.PostDAO;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO pDAO; // Define as instance variable
	private CategoryDAO cDAO;

	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		pDAO = new PostDAO();
		cDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher;
		String textareaId = request.getParameter("textareaId");
		String action = request.getParameter("action");

		try {
			if (textareaId != null) {
				addNewPost(request, response);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		if (action == null) {
			action = "No action";
		}

		try {
			switch (action) {
			case "showAllPosts":
				showAllPosts(request, response);
				break;
			case "createNewPost":
				createNewPost(request, response);
				break;
			case "addNewPost":
				addNewPost(request, response);
				break;
			case "deletePost":
				deletePost(request, response);
				break;
			case "editPost":
				editPost(request, response);
			case "editAboutPage":
				editAboutPage(request, response);
				break;
			case "edittedAboutPage":
				edittedAboutPage(request, response);
				break;
			default:
				showAllPosts(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}// End of doGet method

	private void showAllPosts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Post> listPost = pDAO.selectAllPosts();
		List<Category> countCategory = cDAO.selectAllCategories();

		HttpSession session = request.getSession();
		session.setAttribute("countCategory", countCategory);
		request.setAttribute("listPost", listPost);
		request.setAttribute("listCategory", "All Posts");
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		dispatcher.forward(request, response);
	}

	private void createNewPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setAttribute("listCategory", "Create new post");
		RequestDispatcher dispatcher = request.getRequestDispatcher("editor.jsp");
		dispatcher.forward(request, response);
	}

	private void addNewPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String imageUrl = request.getParameter("imageUrl");
		String preview = request.getParameter("preview");
		String dateIssue = request.getParameter("dateIssue");
		String content = request.getParameter("textareaId");

		Post p = new Post(title, imageUrl, category, dateIssue, preview, content);
		pDAO.insertPost(p);
		List<Post> listPost = pDAO.selectAllPosts();
		request.setAttribute("listPost", listPost);
		request.setAttribute("listCategory", "All Posts");
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		dispatcher.forward(request, response);
	}

	private void editPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int Pid = Integer.parseInt(request.getParameter("id"));
		Post selectedPost = pDAO.selectPost(Pid);
		request.setAttribute("selectedPost", selectedPost);

		RequestDispatcher dispatcher = request.getRequestDispatcher("editor.jsp");
		dispatcher.forward(request, response);
	}

	private void deletePost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int Pid = Integer.parseInt(request.getParameter("id"));
		pDAO.deletePost(Pid);
		System.out.println("adminServlet");
		response.sendRedirect("admin");
	}

	private void editAboutPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setAttribute("listCategory", "Edit about page");
		RequestDispatcher dispatcher = request.getRequestDispatcher("aboutPageEditor.jsp");
		dispatcher.forward(request, response);
	}

	private void edittedAboutPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String content = request.getParameter("textareaId");
		HttpSession session = request.getSession();
		session.setAttribute("content", content);
		RequestDispatcher dispatcher = request.getRequestDispatcher("about.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
