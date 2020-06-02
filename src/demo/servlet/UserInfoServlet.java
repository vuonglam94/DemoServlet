package demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.model.User;

@WebServlet(urlPatterns = "/user-info")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();

		// Lấy ra đối tượng HttpSession
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if (user == null) {
			// ==> /DemoServlet/session
			response.sendRedirect(this.getServletContext().getContextPath() + "/session-login");
			return;
		}

		out.println("<html>");
		out.println("<head><title>Session example</title></head>");
		out.println("<body>");
		out.println("<h3>User Info:</h3>");
		out.println("<p>Name:" + user.getName() + "</p>");
		out.println("<p>Email:" + user.getEmail() + "</p>");
		out.println("</body>");
		out.println("<html>");
	}
}
