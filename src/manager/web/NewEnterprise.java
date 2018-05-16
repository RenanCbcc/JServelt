package manager.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Empresa;
import manager.dao.EmpresaDAO;

@WebServlet(urlPatterns = "/newEnterprise")
public class NewEnterprise extends HttpServlet {

	private static final long serialVersionUID = -3906652746025906919L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nome = req.getParameter("name");
		Empresa entreprise = new Empresa(nome);
		new EmpresaDAO().adiciona(entreprise);
		req.setAttribute("enterprise", entreprise);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/enterprise.jsp");
		rd.forward(req, resp);

	}
}
