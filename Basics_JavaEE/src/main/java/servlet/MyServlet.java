package servlet;

import java.io.IOException;
import java.security.Principal;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.security.SecurityContext;

import ejb.UserService;

@WebServlet("/lgn")
public class MyServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(MyServlet.class
			.getName());

    @EJB
    protected UserService userService;
    
	private static ResourceBundle lStrings = ResourceBundle
			.getBundle("servlet.ServletStrings");

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// LOGGER.log(Level.INFO, "response.sendError()");
		// response.sendError(HttpServletResponse.SC_FORBIDDEN);

		String username = getStringParameter(request, "uname", null);
		String password = getStringParameter(request, "upass", null);

		LOGGER.log(Level.INFO, "[username]: " + username);
		LOGGER.log(Level.INFO, "[password]: " + password);

		String msg = lStrings.getString("servlet.test");

		try {

			request.login(username, password);

		} catch (ServletException e2) {

			LOGGER.log(Level.INFO, "Login ERROR: " + e2.getMessage());
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

		Principal principal = request.getUserPrincipal();
		response.getWriter().println(
				"Authenticated user: " + principal.getName());

		if (request.isUserInRole("ADMIN")) {
			LOGGER.log(Level.INFO, "Role: ADMIN ");
		} else {
			LOGGER.log(Level.INFO, "Role: NOT ADMIN ");
		}
		
//		User user = userService.getUserByName(username);
//		LOGGER.log(Level.INFO, "User loginAttempts: " + user.getLoginAttempts());
		
		
		/*
		 * response.setContentType("text/html;charset=UTF-8"); try (PrintWriter
		 * out = response.getWriter()) { out.println("<html>");
		 * out.println("<head><title>Hello World</title></title>");
		 * out.println("<body>");
		 * 
		 * out.println("<h1>ResourceBundle</h1><b>servlet.test</b>: " + msg);
		 * out.println("</body></html>"); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

	@Override
	public String getServletInfo() {
		return "The MyServlet servlet says hello.";

	}

	public static int getIntParameter(ServletRequest request, String paramName,
			int defaultVal) {

		if (request.getParameter(paramName) == null
				|| request.getParameter(paramName).isEmpty()) {

			return defaultVal;
		}

		return Integer.parseInt(request.getParameter(paramName));

	}

	public static String getStringParameter(ServletRequest request,
			String paramName, String defaultVal) {

		if (request.getParameter(paramName) == null
				|| request.getParameter(paramName).isEmpty()) {

			return defaultVal;
		}

		return request.getParameter(paramName);

	}

}
