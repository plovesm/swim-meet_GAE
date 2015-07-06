/**
 * 
 */
package com.tallkids.swimmeet.objects;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Paul
 *
 */
public class Meet {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String id;
	private Date date;
	private String title;
	private SwimEvent currentEvent;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public SwimEvent getCurrentEvent() {
		return currentEvent;
	}
	public void setCurrentEvent(SwimEvent currentEvent) {
		this.currentEvent = currentEvent;
	}
}
