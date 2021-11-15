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
import mvcpackage.model.dao.CategoryDAO;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO cDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		cDAO = new CategoryDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
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
		if (action == null) {
			action = "No category";
		}
		RequestDispatcher dispatcher;
		try {
			switch (action) {
			case "createNewCategory":
				createNewCategory(request, response);
				break;
			case "newCategory":
				addNewCategory(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}// End of doPost method

	private void createNewCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setAttribute("listCategory", "Create new category");
		RequestDispatcher dispatcher = request.getRequestDispatcher("newCategory.jsp");
		dispatcher.forward(request, response);
	}

	private void addNewCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String newCategory = request.getParameter("newCategory");

		Category c = new Category(newCategory);
		cDAO.insertCategory(c);
		response.sendRedirect("admin");
	}
}
