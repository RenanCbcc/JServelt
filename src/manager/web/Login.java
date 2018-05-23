package manager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.dao.UsuarioDAO;
import manager.models.Usuario;

public class Login implements Task {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("passwd");

		Usuario user = new UsuarioDAO().buscaPorEmailESenha(email, password);

		if (user == null) {
			return "/WEB-INF/pages/noUser.html";
		} else {
			request.getSession().setAttribute("userLoggedin", user);
			return "/index.jsp";

		}

	}

}
