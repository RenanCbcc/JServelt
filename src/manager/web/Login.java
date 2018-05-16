package manager.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.Usuario;
import manager.dao.UsuarioDAO;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 57734454038431397L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("passwd");

		Usuario user = new UsuarioDAO().buscaPorEmailESenha(email, password);

		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<body>");

		if (user == null) {
			writer.println("<h1>There is no such user. Try again:</h1><br/>");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user.loggedin", user);
			writer.println("<h1>Wellcome, " + email + "</h1><br/>");
		}

		writer.println("</body>");
		writer.println("</html>");

	}

}
