package demo.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "*.png", "*.jpg", "*.gif" }, initParams = {
		@WebInitParam(name = "notFoundImage", value = "/images/image-not-found.png") })
public class ImageFilter implements Filter {

	private String notFoundImage;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		notFoundImage = filterConfig.getInitParameter("notFoundImage");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		// ==> /images/path/my-image.png
		// ==> /path1/path2/image.pngs
		String servletPath = req.getServletPath();

		// Đường dẫn tuyệt đối của thư mục gốc của WebApp (WebContent).
		String realRootPath = request.getServletContext().getRealPath("");

		// Đường dẫn tuyệt đối tới file ảnh.
		String imageRealPath = realRootPath + servletPath;
		System.out.println("imageRealPath = " + imageRealPath);

		File file = new File(imageRealPath);
		// Kiểm tra xem ảnh có tồn tại không.
		if (file.exists()) {
			// Cho phép request được đi tiếp. (Vượt qua Filter này).
			// (Để đi tiếp tới file ảnh yêu cầu).
			chain.doFilter(request, response);
		} else if (!servletPath.equals(this.notFoundImage)) {
			// Redirect (Chuyển hướng) tới file ảnh 'image not found'.
			HttpServletResponse resp = (HttpServletResponse) response;
			// ==> /DemoServlet + /images/image-not-found.png
			resp.sendRedirect(req.getContextPath() + this.notFoundImage);
		}
	}

	@Override
	public void destroy() {

	}

}
