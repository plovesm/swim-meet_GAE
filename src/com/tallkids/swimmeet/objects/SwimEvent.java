/**
 * 
 */
package com.tallkids.swimmeet.objects;

/*import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;*/

/**
 * @author Paul
 *
 */

/*@Entity*/
public class SwimEvent 
{
	/*@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)*/
    private long id;
	private int eventNum = 0;
	private String eventName = "";
	private int heatNum = 0;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getEventNum() {
		return eventNum;
	}
	public void setEventNum(int eventNum) {
		this.eventNum = eventNum;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getHeatNum() {
		return heatNum;
	}
	public void setHeatNum(int heatNum) {
		this.heatNum = heatNum;
	}
	
	
}