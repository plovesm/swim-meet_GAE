package com.tallkids.swimmeet.app;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
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
		
		String eventId = req.getParameter("eventId");
		String action = req.getParameter("action");

		if(action != null && !action.isEmpty() && ! "new".equals(eventId))
		{
			updateSwimEvent(eventId, action);
		}
		
		if("list".equals(eventId))
		{
			resp.getWriter().println(buildEventList().toString());	
		}
		else
		{
			resp.getWriter().println(buildJsonOutput(eventId).toString());
		}
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
	
	private JSONArray buildEventList()
	{
		SwimEventDAO seDAO =  new SwimEventDAO();
		JSONArray jsonArr = new JSONArray();
		
		List<SwimEvent> eventList = seDAO.getAllSwimEvents();
		
		for(SwimEvent se : eventList)
		{
			jsonArr.put(buildSwimEventJSONObj(se));
		}
		
		return jsonArr;
	}
	
	/**
	 * @param eventId
	 * @return
	 */
	private JSONObject buildJsonOutput(String eventId) {

		SwimEventDAO seDAO =  new SwimEventDAO();
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
		
		return buildSwimEventJSONObj(se);
	}

	/**
	 * @param se
	 * @return
	 */
	private JSONObject buildSwimEventJSONObj(SwimEvent se) {
		//take action on heat
		JSONObject jsonObj = new JSONObject();
		
		try 
		{
			jsonObj.put("eventId", se.getId());
			jsonObj.put("eventNum", se.getEventNum());
			jsonObj.put("heatNum", se.getHeatNum());

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}

	/**
	 * @param action
	 * @return
	 */
	private boolean updateSwimEvent(String eventId, String action) {
		
		SwimEventDAO seDAO = new SwimEventDAO();

		//Short circuit since you can't update if we don't have the id
		if (eventId == null || eventId.isEmpty())
		{
			return false;
		}
		
		SwimEvent se = seDAO.getSwimEvent(seDAO.getSwimEvent(eventId));

		//take action on event
		if("eventInc".equals(action))
		{
			se.setEventNum(se.getEventNum() + 1);
		}
		else if ("eventDec".equals(action))
		{
			se.setEventNum(se.getEventNum() - 1);
		}
		
		//take action on event
		if("heatInc".equals(action))
		{
			se.setHeatNum(se.getHeatNum() + 1);
		}
		else if ("heatDec".equals(action))
		{
			se.setHeatNum(se.getHeatNum() - 1);
		}
		//Save it back in the case of a change
		return (seDAO.updateSwimEvent(se) != null) ? true : false;
	}

}
