package manager.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Usuario;

@WebFilter(urlPatterns = "/*")
public class AuditFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String user = getUsuario(req, (HttpServletResponse) response);

		System.out.println(user + " accessing: " + uri);
		chain.doFilter(request, response); // Continue doing whatever you was doing.
	}

	private String getUsuario(HttpServletRequest request, HttpServletResponse response) {

		Usuario user = (Usuario) request.getSession().getAttribute("userLoggedin");
		if (user == null) {
			return "userNotLoggedin>";
		} else {

			return user.getEmail();
		}
	}
}
