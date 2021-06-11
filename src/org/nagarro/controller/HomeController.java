package org.nagarro.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.nagarro.DAO.ImagesDAO;
import org.nagarro.DAO.ImagesDAOImpl;
import org.nagarro.model.ImageFile;
import org.nagarro.model.User;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
@MultipartConfig
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ImagesDAO daoImpl;

	@Override
	public void init() throws ServletException {
		super.init();
		daoImpl = new ImagesDAOImpl();
	}

	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			long usedCap = getUsedCapacity(user);
			List<ImageFile> images = getImages(user);
			request.setAttribute("capacity", usedCap);
			request.setAttribute("images", images);
			request.getRequestDispatcher("WEB-INF/Home.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		switch (action) {
		case "upload":
			upload(request, response);
			break;

		case "delete":
			int imageId = Integer.parseInt(request.getParameter("image"));
			delete(imageId);
			response.sendRedirect(request.getContextPath() + "/home");
			break;
		case "update":
			int currentImageId = Integer.parseInt(request.getParameter("image"));
			ImageFile currentImage = getImageFileById(currentImageId);
			request.setAttribute("image", currentImage);
			request.getRequestDispatcher("WEB-INF/Update.jsp").forward(request, response);
			break;
		case "save":
			User user = (User) request.getSession().getAttribute("user");
			int orignalImageId = Integer.parseInt(request.getParameter("imageId"));
			ImageFile orignalImage = getImageFileById(orignalImageId);
			orignalImage.setFileName(request.getParameter("name"));
			Part filePart = request.getPart("image");
			;
			if (filePart.getSize() > 100) {
				String message = null;
				long size = filePart.getSize() / 1024;
				String[] content = filePart.getContentType().split("/");
				long usedCap = getUsedCapacity(user) - orignalImage.getSize();
				if (size > 1024) {
					request.setAttribute("image", orignalImage);
					message = "Maximum of 1MB size Image is Allowed";
					request.setAttribute("message", message);
					request.getRequestDispatcher("WEB-INF/Update.jsp").forward(request, response);
					return;
				}
				if (size + usedCap > 10240) {
					request.setAttribute("image", orignalImage);
					message = "Maximum Limit of 10MB is used";
					request.setAttribute("message", message);
					request.getRequestDispatcher("WEB-INF/Update.jsp").forward(request, response);
					return;
				}
				if (content.length > 0 && content[0].equals("image")) {
					InputStream inputStream = filePart.getInputStream();
					byte[] inImge = IOUtils.toByteArray(inputStream);
					orignalImage.setImage(inImge);
					orignalImage.setSize(size);
				} else {
					request.setAttribute("image", orignalImage);
					message = "Only Images are allowed";
					request.setAttribute("message", message);
					request.getRequestDispatcher("WEB-INF/Update.jsp").forward(request, response);
					return;
				}
			}
			uploadImageFile(orignalImage);
			response.sendRedirect(request.getContextPath() + "/home");
			break;

		default:
			break;
		}

	}

	private ImageFile getImageFileById(int currentImageId) {
		// TODO Auto-generated method stub
		return daoImpl.getImageFileById(currentImageId);
	}

	private void delete(int imageId) {
		daoImpl.deleteImg(imageId);

	}

	protected void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;
		User user = (User) request.getSession().getAttribute("user");
		InputStream inputStream = null;
		try {
			Part filePart = request.getPart("image");
			if (filePart != null) {
				String name = request.getParameter("name");
				long size = filePart.getSize() / 1024;
				String[] content = filePart.getContentType().split("/");
				long usedCap = getUsedCapacity(user);
				if (size > 1024) {
					message = "Maximum of 1MB size Image is Allowed";
					request.setAttribute("message", message);
					doGet(request, response);
					return;
				}
				if (size + usedCap > 10240) {
					message = "Maximum Limit of 10MB is used";
					request.setAttribute("message", message);
					doGet(request, response);
					return;
				}
				if (content.length > 0 && content[0].equals("image")) {
					inputStream = filePart.getInputStream();
					byte[] inImge = IOUtils.toByteArray(inputStream);
					ImageFile image = new ImageFile();
					image.setImage(inImge);
					image.setSize(size);
					image.setFileName(name);
					image.setUser(user);
					uploadImageFile(image);
					response.sendRedirect(request.getContextPath() + "/home");
					return;
				} else {
					message = "Only Images are allowed";
					request.setAttribute("message", message);
					doGet(request, response);
					return;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private long getUsedCapacity(User user) {

		return daoImpl.getUsedCap(user);
	}

	private void uploadImageFile(ImageFile image) {
		daoImpl.saveImageFile(image);

	}

	private List<ImageFile> getImages(User user) {
		return daoImpl.getImages(user);
	}

}
