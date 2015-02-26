package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/selam")
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static ResourceBundle lStrings = ResourceBundle.getBundle("servlet.ServletStrings");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter writer = resp.getWriter();
		writer.write("Merhaba dünya!");
	}

}
