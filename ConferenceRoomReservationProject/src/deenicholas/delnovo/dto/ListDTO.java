package deenicholas.delnovo.dto;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class ListDTO implements Serializable
{
	/**
	 * private member variables
	 */
//	private int startH = 0;
//	private int startM = 0;
//	private int endH = 0;
//	private int endM = 0;
	private String email;
	private Time startTime;
	private Time endTime;
	private Date date;
	private ConferenceRoomDTO conferenceRoom;
	private int waitList;
	/**
	 * Constructor
	 * @param startTime
	 * @param endTime
	 * @param date
	 * @param conferenceRoom
	 * @param email
	 */
	public ListDTO(Time startTime,Time endTime,Date date, ConferenceRoomDTO conferenceRoom,String email,int waitList)
	{	this.email=email;
		this.startTime=startTime;
		this.endTime=endTime;
		this.date = date;
		this.conferenceRoom = conferenceRoom;
		this.waitList =waitList;
	}
	/**
	 * Getter method for the waitList
	 * @return waitList
	 */
	public int getWaitList() {
		return waitList;
	}
	/**
	 * Setter method for the waitList
	 * @param waitList
	 */
	public void setWaitList(int waitList) {
		this.waitList = waitList;
	}
	/**
	 * Getter method for the start time of the meeting
	 * @return start time
	 */
	public Time getStartTime() {
		return startTime;
	}
	/**
	 * Getter method for end time of the meeting
	 * @return end time
	 */
	public Time getEndTime() {
		return endTime;
	}
	/**
	 * Getter method for the date of the meething
	 * @return
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Getter method for the conference details
	 * @return conference
	 */
	public ConferenceRoomDTO getConferenceRoom() {
		return conferenceRoom;
	}
	/**
	 * Getter method for email of the user who booked the room
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	@Override
	public String toString()
	{
		if(email != null)
			return "Room Name: " + conferenceRoom.getRoomName() + " Time: " + startTime.toString() + "-"+ endTime.toString() + " Date: "+ date.toString() + " Equipment: " + conferenceRoom.getStandardEquipment().toString() + " Wait List: " + waitList;
		else
			return "Room Name: " + conferenceRoom.getRoomName() + " Time: " + startTime.toString() + "-"+ endTime.toString() + " Date: "+ date.toString() + " Equipment: " + conferenceRoom.getStandardEquipment().toString();
	}
	
	

	
}
