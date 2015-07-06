package com.tallkids.swimmeet.app;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.tallkids.swimmeet.model.ModelDAO;

@SuppressWarnings("serial")
public class SwimMeetTrackerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/** TODO
		 * 1. Check Meet identifier
		 * 2. Retrieve Current SwimEvent/Heat
		 * 3. Validate return type
		 */
		String name = req.getParameter("name");
		String method = req.getMethod();
		
		//resp.setContentType("text/xml");
		
		resp.getWriter().println(buildXMLOutput(name, method));
		
		Key personKey = ModelDAO.addEntity("Person");
		Key votedOut = null;
		
		if(ModelDAO.updateEntity(personKey, "name", name))
		{
			//Print the list			
			System.out.println("Roll Call...");
			for(Entity person : ModelDAO.getEntities("Person"))
			{
				String personName =  person.getProperty("name").toString();
				
				System.out.println("Name: " + personName);
				
				if("Paul".equals(personName))
				{
					votedOut = person.getKey();
				}
			}
		}
		
		if(votedOut != null)
		{
			System.out.println("Sorry Paul, you were voted out...");
			System.out.println("Result of delete: " + ModelDAO.deleteEntity(votedOut));
			votedOut = null;
		}
		else
		{
			System.out.println("Paul is not on the list.");
		}
		
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
