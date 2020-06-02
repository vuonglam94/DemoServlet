package demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Request:
	// http://localhost:8088/DemoServlet/redirect?redirect=true
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Lấy ra giá trị của tham số (parameter) trên URL.
		String redirect = request.getParameter("redirect");

		if ("true".equals(redirect)) {
			System.out.println("Redirect to ShowServlet");

			// contextPath: Là một String rỗng "" hoặc khác rỗng.
			// Nếu khác rỗng, nó luôn bắt đầu bởi /
			// và không kết thúc bởi /
			String contextPath = request.getContextPath();

			// ==> /DemoServlet/show
			response.sendRedirect(contextPath + "/show");
			return;
		}

		ServletOutputStream out = response.getOutputStream();
		out.println("<h1>Text of RedirectServlet</h1>");
		out.println("- servletPath=" + request.getServletPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
