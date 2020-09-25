package deenicholas.delnovo.dto;
import java.io.Serializable;
import java.util.*;
public class SearchDTO implements Serializable
{
	/** 
	 * Private member variables
	 */
	private Date d1;
	private Date d2;
	private int startH;
	private int startM;
	private int endH;
	private int endM;
	private int length;
	private int capacity;
	private ArrayList<String> equip;
	/**
	 * Constructor
	 * @param d1
	 * @param d2
	 * @param startH
	 * @param startM
	 * @param endH
	 * @param endM
	 * @param length
	 * @param capacity
	 * @param equip
	 */
	public SearchDTO(Date d1,Date d2,int startH,int startM,int endH,int endM,int length,int capacity,ArrayList<String> equip) {
		this.d1=d1;
		this.d2=d2;
		this.startH=startH;
		this.startM=startM;
		this.endH=endH;
		this.endM=endM;
		this.length=length;
		this.capacity=capacity;
		this.equip=equip;
 }
	
	public SearchDTO(Date d1,Date d2,int startH,int startM,int endH,int endM,int length,int capacity) {
		this.d1=d1;
		this.d2=d2;
		this.startH=startH;
		this.startM=startM;
		this.endH=endH;
		this.endM=endM;
		this.length=length;
		this.capacity=capacity;
 }
	/**
	 * Overloaded constructor
	 */
	public SearchDTO() 
	{}
	/**
	 * Getter method for the date of the meeting
	 * @return start date
	 */
	public Date getD1() {
		return d1;
	}
	/**
	 * Setter method for the start date
	 * @param d1
	 */
	public void setD1(Date d1) {
		this.d1 = d1;
	}
	/**
	 * Getter method end date 
	 * @return
	 */
	public Date getD2() {
		return d2;
	}
	/**
	 * Setter method for the end date
	 * @param d2
	 */
	public void setD2(Date d2) {
		this.d2 = d2;
	}
	/**
	 * Getter method for the start time in hours
	 * @return start time in hours
	 */
	public int getStartH() {
		return startH;
	}
	/**
	 * Setter method the start time in hours
	 * @param startH
	 */
	public void setStartH(int startH) {
		this.startH = startH;
	}
	/**
	 * Getter method for the start time in minutes
	 * @return start time in minutes
	 */
	public int getStartM() {
		return startM;
	}
	/**
	 * Setter method the end time in minutes
	 * @param endM
	 */
	public void setEndM(int endM) {
		this.endM = endM;
	}
	/**
	 * Setter method for the end time hours
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
	 * Getter method for the end time in hours
	 * @return end time in hours
	 */
	public int getEndH() {
		return endH;
	}
	/**
	 * Getter method end time in minutes
	 * @return end time in minutes
	 */
	public int getEndM() {
		return endM;
	}
	/**
	 * Getter method for the meeting length
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * Getter method for the conference room capacity
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * Setter method for the conference room capacity
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * Getter method for the equipment
	 * @return list of of equipment
	 */
	public ArrayList<String> getEquip() {
		return equip;
	}
	/**
	 * Setter method for the equipment list
	 * @param equip
	 */
	public void setEquip(ArrayList<String> equip) {
		this.equip = equip;
	}

	
	
}
