package org.nagarro.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nagarro.DAO.ImagesDAO;
import org.nagarro.DAO.ImagesDAOImpl;
import org.nagarro.model.User;

@WebServlet("/verify")
public class VerifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ImagesDAO daoImpl;

	@Override
	public void init() throws ServletException {
		super.init();
		daoImpl = new ImagesDAOImpl();
	}

	public VerifyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("invalid", false);
		if (user != null) {
			long otp = generateOTP(user, request, response);
			request.getSession().setAttribute("genOtp", otp);
			request.getRequestDispatcher("WEB-INF/Verify.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long inOtp = Long.parseLong(request.getParameter("inOtp"));
		long genOtp = (long) request.getSession().getAttribute("genOtp");
		if (inOtp == genOtp) {
			request.setAttribute("invalid", false);
			User user = (User) request.getSession().getAttribute("user");
			request.getSession().invalidate();
			saveOrUpdateUser(user);
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			request.setAttribute("invalid", true);
			request.getRequestDispatcher("WEB-INF/Verify.jsp").forward(request, response);
		}

	}

	private void saveOrUpdateUser(User user) {

		daoImpl.saveUser(user);
		System.out.println(user.getEmail());

	}

	private long generateOTP(User user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long otp = new Random().nextInt(900000) + 100000;
		String to = user.getEmail();
		String from = "honeyjindal0056@gmail.com";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("honeyjindal0056@gmail.com", "Harish1234");

			}

		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("OTP for Image Utility");
			message.setText("One Time Password is " + otp);
			Transport.send(message);

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		return otp;

	}

}
