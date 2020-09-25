package deenicholas.delnovo.dto;
import java.io.Serializable;
import java.util.*;
public class HiredEquipmentDTO implements Serializable
{
	/**
	 * private member variable
	 */
	private String time;
	private Date date;
	private ArrayList<String> list;
	private String roomName;
	/**
	 * Constructor
	 * @param time
	 * @param date
	 * @param list
	 */
	public HiredEquipmentDTO(String time, Date date,String roomName, ArrayList<String> list) {
		this.time = time;
		this.date = date;
		this.roomName = roomName;
		this.list = list;
	}
	public String getRoomName() {
		return roomName;
	}
	/**
	 * Getter method for the meeting time
	 * @return time 
	 */
	public String getTime() {
		return time;
	}
	/**
	 * Getter method for the meeting date
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Getter method for the hired equipment list
	 * @return
	 */
	public ArrayList<String> getList() {
		return list;
	}
	@Override
	public String toString()
	{
		return "\nEquipment needed \ntime: " + time + "\n date: " + date + "\n list: " + list.toString()+ "\n ConferenceRoomVenue: " + roomName ;
	}
}
