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

@WebServlet(urlPatterns = "/session-login")
public class SessionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletOutputStream out = response.getOutputStream();

		// Lấy ra đối tượng HttpSession
		HttpSession session = request.getSession();

		// Giả sử người dùng đã login thành công.
		User user = new User("Lam", "lam@gmail.com");

		// Lưu trữ thông tin người dùng vào 1 thuộc tính (attribute) của Session.
		session.setAttribute("userInfo", user);

		out.println("<html>");
		out.println("<head><title>Session example</title></head>");

		out.println("<body>");
		out.println("<h3>You are logined! Info stored in session</h3>");

		out.println("<a href='user-info'>View User Info</a>");
		out.println("</body>");
		out.println("<html>");
	}
}
