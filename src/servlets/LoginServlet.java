package servlets;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.DAO;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = null;
		String uname = request.getParameter("username");
		String password = request.getParameter("password");
		DAO dao = new DAO();
		try {
			user = dao.getUser(uname, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (user != null) {
			System.out.println("Sucess");
			HttpSession session = request.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("username", user);
			response.sendRedirect("http://localhost:8080");
		} else {
			System.out.println("Fail");
			HttpSession session = request.getSession();
			session.setAttribute("error", "invalid credentials");
			doGet(request, response);
		}

	}
}