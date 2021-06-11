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
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ImagesDAO daoImpl;

	@Override
	public void init() throws ServletException {
		super.init();
		daoImpl = new ImagesDAOImpl();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("invalid", false);
		request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User inputUser = new User();
		inputUser.setEmail(request.getParameter("email"));
		inputUser.setPassword(request.getParameter("password"));
		User user = ValidateUser(request, response, inputUser);
		if (user != null) {
			request.setAttribute("invalid", false);
			request.getSession().setAttribute("auth", "Authenticated");
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			request.setAttribute("invalid", true);
			request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
		}

	}

	protected User ValidateUser(HttpServletRequest request, HttpServletResponse response, User inputUser)
			throws ServletException, IOException {
		User user = daoImpl.getUserByEmail(inputUser.getEmail());
		String password = inputUser.getPassword();
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;

	}

}
