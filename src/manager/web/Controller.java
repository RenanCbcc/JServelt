package manager.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/execute")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String task = req.getParameter("task");
		if (task == null)
			throw new IllegalArgumentException("You forgget to pass the task.");
		try {
			String className = "manager.web." + task;
			Class<?> type = Class.forName(className);
			Task instance = (Task) type.newInstance();
			String page = instance.execute(req, resp);

			RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
			requestDispatcher.forward(req, resp);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}
	}
}
