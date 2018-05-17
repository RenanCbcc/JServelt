package manager.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Empresa;
import manager.dao.EmpresaDAO;

@WebServlet(urlPatterns = "/search")
public class SearchEntreprise extends HttpServlet {

	private static final long serialVersionUID = -5640846148829616329L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String filtro = req.getParameter("filter");
		Collection<Empresa> entreprises = new EmpresaDAO().buscaPorSimilaridade(filtro);

		req.setAttribute("enterprises", entreprises);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/enterpriseList.jsp");
		rd.forward(req, resp);

	}
}