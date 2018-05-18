package manager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Empresa;
import manager.dao.EmpresaDAO;

public class NewEnterprise implements Task {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");

		Empresa empresa = new Empresa(name);
		new EmpresaDAO().adiciona(empresa);

		request.setAttribute("nome", name);

		return "/WEB-INF/pages/enterprise.jsp";

	}
}
