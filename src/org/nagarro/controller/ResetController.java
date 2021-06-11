package org.nagarro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nagarro.DAO.ImagesDAO;
import org.nagarro.DAO.ImagesDAOImpl;
import org.nagarro.model.User;

/**
 * Servlet implementation class ResetController
 */
@WebServlet("/reset")
public class ResetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private ImagesDAO daoImpl;

	@Override
	public void init() throws ServletException {
		super.init();
		daoImpl = new ImagesDAOImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("name", "reset");
		request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = null;
		user = daoImpl.getUserByEmail(request.getParameter("email"));
		if (user == null) {
			request.setAttribute("userExist", true);
			doGet(request, response);
			return;
		}
		user.setPassword(request.getParameter("password"));
		request.getSession().setAttribute("user", user);
		request.setAttribute("userExist", false);
		response.sendRedirect(request.getContextPath() + "/verify");
	}

}
