package com.tallkids.swimmeet.app;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.tallkids.swimmeet.model.SwimEventDAO;
import com.tallkids.swimmeet.objects.SwimEvent;

@SuppressWarnings("serial")
public class SwimMeetTrackerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/** TODO
		 * 1. Check Meet identifier
		 * 2. Retrieve Current SwimEvent/Heat
		 * 3. Validate return type
		 */
		
		SwimEventDAO seDAO = new SwimEventDAO();
		String eventId = req.getParameter("eventId");
		SwimEvent se = null;
		
		if("new".equals(eventId))
		{
			se = seDAO.addSwimEvent();
		}
		else if (eventId != null && !eventId.isEmpty())
		{
			se = seDAO.getSwimEvent(seDAO.getSwimEvent(eventId));
		}
		else
		{
			se = seDAO.addSwimEvent();
		}
		
		JSONObject jsonObj = new JSONObject();
		
		try 
		{
			jsonObj.put("eventId", se.getId());
			jsonObj.put("eventNum", se.getEventNum());
			jsonObj.put("heatNum", se.getHeatNum());

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		resp.getWriter().println(jsonObj.toString());
		
		/*String name = req.getParameter("name");
		String method = req.getMethod();
		
		resp.setContentType("text/html");
		
		resp.getWriter().println("<html><body>");//buildXMLOutput(name, method));
		
		Key personKey = null; 
		Key votedOut = null;
		List<Entity> results = null;
		
		
		SwimEventDAO seDAO = new SwimEventDAO();
		
		resp.getWriter().println("Adding SwimEvent...<br />");
		SwimEvent se = seDAO.addSwimEvent();
		System.out.println("Key: " + se.getId());
		resp.getWriter().println("Starting ID: " + se.getId() +"<br />");
		resp.getWriter().println("Starting Event Number: " + se.getEventNum() +"<br />");
		resp.getWriter().println("Starting Heat Number: " + se.getHeatNum() +"<br />");
		
		resp.getWriter().println("Updating...<br />");
		
		se.setEventNum(se.getEventNum() + 1);
		se.setHeatNum(se.getHeatNum() + 1);
				
		se = seDAO.updateSwimEvent(se);
		
		SwimEvent se2 = seDAO.getSwimEvent(seDAO.getSwimEvent(se.getId()));
		
		resp.getWriter().println("Ending ID: " + se2.getId() +"<br />");
		resp.getWriter().println("Ending Event Number: " + se2.getEventNum() +"<br />");
		resp.getWriter().println("Ending Heat Number: " + se2.getHeatNum() +"<br />");
		*/
		/*
		
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
										" Name: " + strPersonName +
										" Key Name: " + person.getKey().getName() + 
										" ID: " + person.getKey().getId() +
										" Namespace: " + person.getKey().getNamespace() + 
										"<br />");
				
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
		*/
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
