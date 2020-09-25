package deenicholas.delnovo.dto;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;
public class BookingDTO implements Serializable
{
	/**
	 * Private member attributes
	 */
	private UserDTO user;
	private ConferenceRoomDTO conference;
	private int startH;
	private int startM;
	private int endH;
	private int endM;
	private int length;
	private String description;
	private Date date;
	private int noOfAttendees;
	private ArrayList<String> equipment;
	/**
	 * Constructor
	 * @param user
	 * @param conference
	 * @param description
	 * @param length
	 * @param startH
	 * @param startM
	 * @param endH
	 * @param endM
	 * @param date
	 * @param equipment
	 */
	public BookingDTO(UserDTO user,ConferenceRoomDTO conference,String description,int length,int startH,int startM,int endH,int endM,Date date,int noOfAttendess,ArrayList<String> equipment){
		this.user = user;
		this.conference=conference;
		this.description=description;
		this.length=length;
		this.startH=startH;
		this.startM=startM;
		this.endH=endH;
		this.endM=endM;
		this.date= date; 
		this.noOfAttendees=noOfAttendess;
		this.equipment=equipment;
		
	}
	public int getNoOfAttendees() {
		return noOfAttendees;
	}
	/**
	 * Overloaded empty constructor
	 */
	public BookingDTO()
	{}
	/**
	 * Overloaded constructor
	 * @param roomName
	 * @param description
	 * @param length
	 * @param start
	 * @param end
	 * @param date1
	 * @param equipment
	 */
	/**
	 * Getter method for the user
	 * @return user
	 */
	public UserDTO getUser() {
		return user;
	}
	/**
	 * Getter method for the conference room
	 * @return conference
	 */
	public ConferenceRoomDTO getConference() {
		return conference;
	}
	/**
	 * Getter method for the start time in hours
	 * @return start time in hours
	 */
	public int getStartH() {
		return startH;
	}
	/**
	 * Getter method for the start time in minutes
	 * @return start time in minutes
	 */
	public int getStartM() {
		return startM;
	}
	/**
	 * Getter method for the end time in hours
	 * @return end time in hours
	 */
	public int getEndH() {
		return endH;
	}
	/**
	 * Getter method for the end time in minutes
	 * @return end time in minutes
	 */
	public int getEndM() {
		return endM;
	}
	/**
	 * Getter method for meeting length/duration
	 * @return meeting length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * Getter method for the meeting description
	 * @return meeting description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Getter method for the date of the meeting
	 * @return date 
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Getter method for the array list of the equipment
	 * @return equipment array list
	 */
	public ArrayList<String> getEquipment() {
		return equipment;
	}
	/**
	 * Setter method for the user details
	 * @param user
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}
	/**
	 * Setter method for the conference details
	 * @param conference
	 */
	public void setConference(ConferenceRoomDTO conference) {
		this.conference = conference;
	}
	/**
	 * Setter method for the start time in hours
	 * @param startH
	 */
	public void setStartH(int startH) {
		this.startH = startH;
	}
	
	/**
	 * Setter method for the end time in hours
	 * @param endH
	 */
	public void setEndH(int endH) {
		this.endH = endH;
	}
	/**
	 * Setter method for the start time in minutes
	 * @param startM
	 */
	public void setStartM(int startM) {
		this.startM = startM;
	}
	/**
	 * Setter method for the end time in minutes
	 * @param endM
	 */
	public void setEndM(int endM) {
		this.endM = endM;
	}
	/**
	 * Setter method for the meeting length
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * Setter method for the meeting description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Setter method for the meeting date
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Setter method for the equipments
	 */
	public void setEquipment(ArrayList<String> equipment) {
		this.equipment = equipment;
	}
	/**
	 * Getter method for the room name
	 * @return room name
	 */
	@Override
	public String toString()
	{
		Time st = new Time(startH, startM, 0);
		Time et = new Time(endH, endM, 0); 
		return "\nRoom Venue: " + conference.getRoomName() + "\n Time: " + st + " - " + et + " \n Duration: "  + length + " min. " + "\n Purpose of the meeting: " + description + " \n Date: " + date.toString();
	}
	
	
}
