package manager.web;

import java.io.IOException;
import java.io.PrintWriter;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		String nome = req.getParameter("name");
		Empresa empresa = new Empresa(nome);
		new EmpresaDAO().adiciona(empresa);
		
		PrintWriter writer = resp.getWriter();
	    writer.println("<html>");
	    writer.println("<body>");
	    writer.println("<h1>Enterprise "+empresa.getNome() +" added successfully</h1><br/>");

	}
}
