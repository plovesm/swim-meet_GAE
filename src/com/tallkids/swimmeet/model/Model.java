/**
 * 
 */
package com.tallkids.swimmeet.model;

import java.util.List;

import javax.jdo.PersistenceManager;

/**
 * @author Paul
 *
 */
public class Model {
	
	protected PersistenceManager getPM()
	{
		return PMF.get().getPersistenceManager();
	}
	
	/*protected List<?> getAllByType(Class type)
	{
		List<> resultList = new ArrayList<type>();
		
		return new List<type>;
		
	}*/
	
	
}
