/**
 * 
 */
package com.tallkids.swimmeet.model;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.tallkids.swimmeet.objects.SwimEvent;

/**
 * @author Paul
 *
 */
public class SwimEventDAO {
	
	private final String SWIM_EVENT_KIND = "SwimEvent";
	private final String SWIM_EVENT_EVENT_NUM = "eventNum";
	private final String SWIM_EVENT_HEAT_NUM = "heatNum";
	
	public SwimEvent addSwimEvent()
	{
		SwimEvent se = new SwimEvent();
		se = addSwimEvent(se);
		return se;
	}
	
	public SwimEvent addSwimEvent(SwimEvent event)
	{
		Key eventKey = ModelDAO.addEntity(SWIM_EVENT_KIND);
		
		long idKey = eventKey.getId();
		
		event.setId(idKey);
		
		//Initialize
		event = updateSwimEvent(event);
		
		return event;
	}
	public SwimEvent updateSwimEvent(SwimEvent event)
	{
		DatastoreService ds = ModelDAO.getDataStore();
		Transaction txn = ds.beginTransaction();
		Entity swimEntity;		
		
		try
		{
			swimEntity = getSwimEvent(event.getId());
		
			swimEntity.setProperty(SWIM_EVENT_HEAT_NUM, event.getHeatNum());
			swimEntity.setProperty(SWIM_EVENT_EVENT_NUM, event.getEventNum());
			ModelDAO.saveEntity(swimEntity);
			
			txn.commit();
			
		} finally {
		    if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		
		return event;
		
	}
	
	
	public List<SwimEvent> getAllSwimEvents()
	{
		List<Entity> listE = null; 
		List<SwimEvent> listSE = new ArrayList<SwimEvent>();
		
		listE = ModelDAO.getEntities("SwimEvent");
		
		for(Entity e : listE)
		{
			listSE.add(getSwimEvent(e));
		}
		
		return listSE;
	}
	
	public Entity getSwimEvent(String id)
	{
		return getSwimEvent(Long.parseLong(id));
	}
	
	public Entity getSwimEvent(long id)
	{
		Key keyIn = KeyFactory.createKey(SWIM_EVENT_KIND, id);
		System.out.println("Key in: " + keyIn);
		Entity swimEventEnt = ModelDAO.getEntity(keyIn);
		System.out.println(swimEventEnt);
		return swimEventEnt;
	}
	
	public SwimEvent getSwimEvent(Entity swimEventEnt)
	{
		SwimEvent swimEvent = new SwimEvent();

		Object eventNumProp = swimEventEnt.getProperty(SWIM_EVENT_EVENT_NUM);
		Object heatNumProp = swimEventEnt.getProperty(SWIM_EVENT_HEAT_NUM);
		
		int eventNum = Integer.parseInt((eventNumProp == null) ? "0" : eventNumProp.toString());
		int heatNum = Integer.parseInt((heatNumProp == null) ? "0" : heatNumProp.toString());
		
		swimEvent.setId(swimEventEnt.getKey().getId());
		swimEvent.setEventNum(eventNum);
		swimEvent.setHeatNum(heatNum);
				
		return swimEvent;
		
	}
	
	public boolean incrementEvent()
	{
		return false;
	}
	
	public boolean decrementEvent()
	{
		return false;
		
	}
	
	public boolean incrementHeat()
	{
		return false;
		
	}
	
	public SwimEvent getEventByMeet(String meetId)
	{
		return null;
		
	}
	
	public boolean resetEvent()
	{
		return false;
		
	}
}
