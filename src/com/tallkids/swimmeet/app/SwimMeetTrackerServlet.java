package com.tallkids.swimmeet.app;
import java.io.IOException;
import java.util.List;

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
		
		resp.setContentType("text/html");
		
		resp.getWriter().println("<html><body>");//buildXMLOutput(name, method));
		
		Key personKey = null; 
		Key votedOut = null;
		List<Entity> results = null;
		
		if( name != null && !name.isEmpty())
		{
			personKey = ModelDAO.addEntity("Person");
			
			if(personKey != null)
			{
				ModelDAO.updateEntity(personKey, "name", name);
			}
		}
		
		results = ModelDAO.getEntities("Person");
		
		if(results != null && results.size() > 0)
		{
			//Print the list			
			resp.getWriter().println("Roll Call...<br />");
			for(Entity person : results)
			{
				Object personName =  person.getProperty("name");
				
				String strPersonName = (personName != null) ? personName.toString(): "BOOM";
				resp.getWriter().println("Key: " + person.getKey() +
										" Name: " + strPersonName + "<br />");
				
				if("Paul".equals(strPersonName))
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
		
		
		resp.getWriter().println("<form action=\"http://localhost:8888/swim_meet\" method=\"get\">"
				+ "<button type=\"submit\">Refresh</button>"
				+ "</form>");
		
		resp.getWriter().println("</body></html>");
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
