package manager.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Empresa;
import manager.dao.EmpresaDAO;


public class SearchEntreprise implements Task {

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String filtro = request.getParameter("filter");
		Collection<Empresa> entreprises = new EmpresaDAO().buscaPorSimilaridade(filtro);

		request.setAttribute("enterprises", entreprises);
		return "/WEB-INF/pages/enterpriseList.jsp";
		

	}
}