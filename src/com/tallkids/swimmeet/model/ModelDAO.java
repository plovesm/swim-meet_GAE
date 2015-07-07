package com.tallkids.swimmeet.model;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;


public class ModelDAO 
{
	
	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	/*private static DatastoreService getDataStore()
	{
		ds = (ds == null)? DatastoreServiceFactory.getDatastoreService() : ds;
		
		return ds;
	}
*/
	/**
	 * Adds an Entity passed in
	 * @return boolean indicating success
	 */
	public static Key addEntity(Entity entity)
	{
		Key key = null;
		
		ds.put(entity);
		
		key = entity.getKey();
		
		return key;
	}
	
	/**
	 * Add an Entity by details
	 * @return boolean indicating success
	 */
	public static Key addEntity(String kind)
	{
		Key key = null;
		
		Entity event = new Entity(kind);
		
		ds.put(event);
		
		key = event.getKey();
		
		return key;
	}
	
	/**
	 * Adds a default SwimEvent 
	 * @return
	 */
	public static Key addEntity()
	{
		Key key = null;
		
		Entity event = new Entity("SwimEvent");
		
		event.setProperty("eventNum", "1");
		event.setProperty("heatNum", "1");
		event.setProperty("eventName", "Meet 1");

		ds.put(event);
		
		key = event.getKey();
		
		return key;
		
	}
	
	public static Entity getEntity(Key key)
	{
		try 
		{
			//First retrieve the entity
			Entity ent = ds.get(key);
		
			return ent;
			
		} 
		catch (EntityNotFoundException e) 
		{
			return null;
		}
	}
	
	public static Entity getEntity(Query q)
	{
		try 
		{
			//First retrieve the entity
			Entity ent = ds.prepare(q).asSingleEntity();
		
			return ent;
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	public static List<Entity> getEntities(Query q)
	{
		//First retrieve the entity
		List<Entity> results = ds.prepare(q)
				.asList(FetchOptions.Builder.withDefaults());
			
		return results;
	}
	
	public static List<Entity> getEntities(String kind)
	{
		Query q = new Query(kind);
		
		return getEntities(q);
	}
	
	public static boolean updateEntity(Key key, String property, String value)
	{
		Entity ent = getEntity(key);
		
		//Check if the entity was found or short circuit
		if(ent == null) { return false; }
		
		//Set the new property
		ent.setProperty(property, value);
		
		//Save the changes
		return saveEntity(ent);
	}
	
	public static boolean updateEntity(Key key, String property, int value)
	{
		Entity ent = getEntity(key);
		
		//Check if the entity was found or short circuit
		if(ent == null) { return false; }
		
		//Set the new property
		ent.setProperty(property, value);
		
		//Save the changes
		return saveEntity(ent);
	}
	
	public static boolean updateEntity(Key key, String property, Date value)
	{
		Entity ent = getEntity(key);
		
		//Check if the entity was found or short circuit
		if(ent == null) { return false; }
		
		//Set the new property
		ent.setProperty(property, value);
		
		//Save the changes
		return saveEntity(ent);
	}
	
	public static boolean updateEntity(Key key, String property, double value)
	{
		Entity ent = getEntity(key);
		
		//Check if the entity was found or short circuit
		if(ent == null) { return false; }
		
		//Set the new property
		ent.setProperty(property, value);
		
		//Save the changes
		return saveEntity(ent);
	}
	
	public static boolean updateEntity(Key key, String property, boolean value)
	{
		Entity ent = getEntity(key);
		
		//Check if the entity was found or short circuit
		if(ent == null) { return false; }
		
		//Set the new property
		ent.setProperty(property, value);
		
		//Save the changes
		return saveEntity(ent);
	}
	
	public static boolean saveEntity(Entity entity)
	{
		try
		{
			ds.put(entity);

			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean deleteEntity(Entity entity)
	{
		return	deleteEntity(entity.getKey());
	}
	
	public static boolean deleteEntity(Key key)
	{

		try
		{
			ds.delete(key);

			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
}
