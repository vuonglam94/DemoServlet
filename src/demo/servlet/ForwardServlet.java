package demo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/forward")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Lấy giá trị tham số (parameter) trên URL.
		String forward = request.getParameter("forward");

		if ("true".equals(forward)) {
			System.out.println("Forward to ShowServlet");

			// Sét dữ liệu vào thuộc tính (attribute) của request.
			request.setAttribute("show", //
					"Hi, This is Show Page after Servlet Forward !");

			RequestDispatcher dispatcher //
					= request.getServletContext().getRequestDispatcher("/show");
			dispatcher.forward(request, response);

			return;
		}
		ServletOutputStream out = response.getOutputStream();
		out.println("<h1>Text of ForwardServlet</h1>");
		out.println("<p>- servletPath=" + request.getServletPath() + "</p>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
