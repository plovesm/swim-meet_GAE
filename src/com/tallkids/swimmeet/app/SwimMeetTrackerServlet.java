package com.tallkids.swimmeet.app;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SwimMeetTrackerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/** TODO
		 * 1. Check Meet identifier
		 * 2. Retrieve Current Event/Heat
		 * 3. Validate return type
		 */
		String name = req.getParameter("name");
		String method = req.getMethod();
		
		resp.setContentType("text/xml");
		resp.getWriter().println(buildXMLOutput(name, method));
	}

	/**
	 * @param name
	 * @param method
	 * @return
	 */
	private String buildXMLOutput(String name, String method) {
		return "<xml>"
				+ "<title>Hello, world</title>"
				+ "<name>"
				+ name
				+ "</name>"
				+ "<method>"
				+ method
				+ "</method>"
				+ "</xml>";
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/** TODO
		 * 1. Validate security
		 * 2. Ensure user has edit permissions
		 * 3. Sanitize input
		 * 4. Update values
		 */
		doGet(req, resp);
	}
}
