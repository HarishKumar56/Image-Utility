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

@WebServlet("/register")
public class ResgisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ImagesDAO daoImpl;

	@Override
	public void init() throws ServletException {
		super.init();
		daoImpl = new ImagesDAOImpl();
	}

	public ResgisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("name", "register");
		request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = null;
		user = daoImpl.getUserByEmail(request.getParameter("email"));
		if (user != null) {
			request.setAttribute("userNotExist", true);
			doGet(request, response);
			return;
		}
		user = new User();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/verify");
	}

}
