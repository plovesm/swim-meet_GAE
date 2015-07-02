/**
 * 
 */
package com.tallkids.swimmeet.objects;

import java.util.Date;

/**
 * @author Paul
 *
 */
public class Meet {
	private String id;
	private Date date;
	private String title;
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
}
