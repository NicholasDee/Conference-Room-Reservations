package deenicholas.delnovo.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ConferenceRoomDTO implements Serializable
{
	/**
	 * Private member variables
	 */
	private String roomName = null;
	private int capacity = 0;
	private ArrayList<String> standardEquipment = new ArrayList<String>();
	/**
	 * Constructor
	 * @param roomName
	 * @param capacity
	 * @param standardEquipment
	 */
	public ConferenceRoomDTO(String roomName, int capacity, ArrayList<String> standardEquipment)
	{
		this.roomName = roomName;
		this.capacity = capacity;
		this.standardEquipment = standardEquipment;
	}
	/**
	 * Overloaded constructor
	 */
	public ConferenceRoomDTO() {}
	/**
	 * Getter method for the conference room name
	 * @return conference room name
	 */
	public String getRoomName() 
	{
		return roomName;
	}
	/**
	 * Setter method for the room name
	 * @param roomName
	 */
	public void setRoomName(String roomName) 
	{
		this.roomName = roomName;
	}
	/**
	 * Getter method for the conference room capacity
	 * @return
	 */
	public int getCapacity() 
	{
		return capacity;
	}
	/**
	 * Setter method for the conference room capacity
	 * @param capacity
	 */
	public void setCapacity(int capacity) 
	{
		this.capacity = capacity;
	}
	/**
	 * Getter method for the standard equipment
	 * @return standard equipment
	 */
	public ArrayList<String> getStandardEquipment() 
	{
		return standardEquipment;
	}
	/**
	 * Setter method for the standard equipment
	 * @param standardEquipment
	 */
	public void setStandardEquipment(ArrayList<String> standardEquipment) 
	{
		this.standardEquipment = standardEquipment;
	}
}
