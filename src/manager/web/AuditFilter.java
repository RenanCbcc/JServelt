package manager.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/*")
public class AuditFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		Cookie cookie = getUsuario(req);
		String user = "usernot.loggedin>";

		if (cookie != null)
			user = cookie.getValue();

		System.out.println(user + " accessing: " + uri);
		chain.doFilter(request, response); // Continue doing whatever you was doing.
	}

	private Cookie getUsuario(HttpServletRequest req) {

		Cookie[] cookies = req.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("usuario.logado")) {
				return cookie;
			}
		}

		return null;
	}

}
