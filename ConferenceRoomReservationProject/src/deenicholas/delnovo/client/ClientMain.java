package deenicholas.delnovo.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import deenicholas.delnovo.dto.*;

public class ClientMain
{
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private Vector<ListDTO> conferenceRooms = null, bookedRooms = null;
	private Vector<ConferenceRoomDTO>conferenceRoomWaitList = null;
	private int capacity;
	
	public ClientMain()
	{
		try {
			socket = new Socket("localhost", 23000);	
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Takes in the email address and the password of the user
	 * @param emailAdd
	 * @param password
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @returns a user from socket
	 */
	
	public UserDTO login(String emailAdd, String password) throws UnknownHostException, IOException, ClassNotFoundException
	{
		oos.writeUTF("login");
		oos.flush();
		oos.writeUTF(emailAdd);
		oos.flush();
		oos.writeUTF(password);
		oos.flush();
		UserDTO userDto = (UserDTO) ois.readObject();
		if(userDto == null)
		{
			userDto = new UserDTO("err", "err");
		}
		return userDto;
		
	}
	/**
	 * searches boardrooms whether booked or available, according to the user's specifications
	 * @param d1
	 * @param d2
	 * @param startH
	 * @param startM
	 * @param endH
	 * @param endM
	 * @param length
	 * @param capacity
	 * @param equip
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	public  Vector<ListDTO> searchRoomClient(Date d1,Date d2,int startH,int startM,int endH,int endM,int length,int capacity) throws IOException, ClassNotFoundException
	{	
		SearchDTO searchRoom = new SearchDTO(d1, d2, startH, startM, endH, endM, length, capacity);
		oos.writeUTF("search");
		oos.flush();
		oos.writeObject(searchRoom);
		oos.flush();
		conferenceRooms = (Vector<ListDTO>) ois.readObject();
		return conferenceRooms;	
	}
	
	/**
	 * Reserves a room for a particular user
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
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public boolean reserveARoomForUser(UserDTO user,ConferenceRoomDTO conference,String description,int length,int startH,int startM,int endH,int endM,Date date, int capacity, ArrayList<String> equipment) throws IOException, ClassNotFoundException
	{	
		BookingDTO bookingDTO = new BookingDTO(user, conference, description, length, startH, startM, endH, endM, date, capacity, equipment );
		oos.writeUTF("Book");
		oos.writeObject(bookingDTO);
		oos.flush();
	    String  result = ois.readUTF();
		if(result.equals("SuccessfulBooking"))
		{
			return true;
		}
		else
		{	
			return false;
		}
	}
	/**
	 * Allows the user to view a waiting list for a particular room
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Vector<ListDTO> viewWaitingList(Date d1,Date d2,int startH,int startM,int endH,int endM,int length,int capacity) throws IOException, ClassNotFoundException
	{
		SearchDTO searchRoom = new SearchDTO(d1, d2, startH, startM, endH, endM, length, capacity);
		oos.writeUTF("viewWaitingList");
		oos.writeObject(searchRoom);
		oos.flush();
		Vector<ListDTO> waitingList = (Vector<ListDTO>) ois.readObject();
		return waitingList;
	}
	/**
	 * Puts the user on a conference room of their choice
	 * @param message
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public boolean putOnWaitingList(BookingDTO bookingDTO, String email) throws IOException, ClassNotFoundException
	{
		oos.writeUTF("PutMeOnWaitingList");
		oos.flush();
		oos.writeUTF(email);
		oos.writeObject(bookingDTO);
		oos.flush();
		String result =ois.readUTF();
		if(result.equals("addedToWaitingListSuccessfully"))
		{	
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns a list of bookings of a particular user
	 * @param emailAdd
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Vector<BookingDTO> cancelARoom( String emailAdd) throws IOException, ClassNotFoundException
	{
		oos.writeUTF("cancel");
		oos.writeUTF(emailAdd);
		oos.flush();
		Vector<BookingDTO> bookingsOfUser = (Vector<BookingDTO>) ois.readObject();
		return bookingsOfUser;
	}
	

	/**
	 * Removes a user's booking
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
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public boolean removeBooking(UserDTO user,ConferenceRoomDTO conference,String description,int length,int startH,int startM,int endH,int endM,Date date, int capacity, ArrayList<String> equipment) throws IOException, ClassNotFoundException
	{
		BookingDTO bookingDTO = new BookingDTO(user, conference, description, length, startH, startM, endH, endM, date, capacity, equipment);
		oos.writeUTF("RemoveBooking");
		oos.writeObject(bookingDTO);
		oos.flush();
		String result = ois.readUTF();
		if(result.equals("BookingRemoved"))//GUI
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/*************************************REPORTS********************************************************/
/**
 * Generates and displays the number of conference room booking cancellations
 * @param d1
 * @param d2
 * @throws IOException
 * @throws ClassNotFoundException 
 */
	
	public int displayAllCancellations(Date d1, Date d2) throws IOException, ClassNotFoundException
	{
		DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = dateToString.format(d1);
		String date2 = dateToString.format(d2);
		oos.writeUTF("CancellationReport");
		oos.flush();
		oos.writeUTF(date1);
		oos.writeUTF(date2);
		oos.flush();
		
		int noOfCancellations = ois.readInt();
		return noOfCancellations;
	}
	/**
	 * Generates a report that displays a waiting list for a particular conference room
	 */
	public Vector<BookingDTO> requestForWaitingList() throws UnknownHostException, IOException, ClassNotFoundException
	{
		oos.writeUTF("requestForWaitingList");
		oos.flush();
		Vector<BookingDTO> waitList = (Vector<BookingDTO>) ois.readObject();
		return waitList;
	}
	/**
	 * Generates a report that displays all the non-standard equipment requested by users
	 * @param d1
	 * @param d2
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public Vector<String> equipmentRequest(Date d1 , Date d2) throws UnknownHostException, IOException, ClassNotFoundException
	{
		DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = dateToString.format(d1);
		String date2 = dateToString.format(d2);
		oos.writeUTF("equipmentRequest");
		oos.flush();
		oos.writeUTF(date1);
		oos.writeUTF(date2);
		oos.flush();
		
		Vector<String> equipment = (Vector<String>) ois.readObject();
		System.out.println(equipment);
		return equipment;
	}
	/**
	 * A report that shows percentage occupation of each venue during the selected period
	 * @param d1
	 * @param d2
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<String> occupationPercentageOfRooms(Date d1, Date d2) throws UnknownHostException, IOException, ClassNotFoundException
	{	
		DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = dateToString.format(d1);
		String date2 = dateToString.format(d2);
		oos.writeUTF("OccupationPercentageOfRooms");
		oos.flush();
		oos.writeUTF(date1);
		oos.writeUTF(date2);
		oos.flush();
		
		ArrayList<String> percentageOccupation = (ArrayList<String>) ois.readObject();
		return percentageOccupation;
		
	}
	/**
	 * Allows the user to query a room and check who is currently booked under a partcular conference room
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	public Vector<BookingDTO> queryARoom() throws IOException, ClassNotFoundException
	{
		oos.writeUTF("ShowMeAListOfAllTheBookings");
		oos.flush();
		
		Vector<BookingDTO> queriedBookings = (Vector<BookingDTO>) ois.readObject();
		return queriedBookings; 
	}
	
	public Vector<BookingDTO> viewReservation(String emailAdd) throws UnknownHostException, IOException, ClassNotFoundException
	{
		oos.writeUTF("viewReservation");
		oos.flush();
		oos.writeUTF(emailAdd);
		oos.flush();	
		Vector<BookingDTO> reservations = (Vector<BookingDTO>) ois.readObject();
		return reservations;
		
	}
	
	public void confirmReservation(BookingDTO bookingDto ) throws UnknownHostException, IOException, ClassNotFoundException
	{
		oos.writeUTF("confirmReservation");
		oos.writeObject(bookingDto);
		oos.flush();
	}
	
	public void cancelReservation(BookingDTO bookingDto ) throws UnknownHostException, IOException, ClassNotFoundException
	{
		oos.writeUTF("cancelReservation");
		oos.writeObject(bookingDto);
		oos.flush();
		
	}
	public void closeConnections() throws IOException{
		oos.writeUTF("END");
		oos.flush();
		oos.close();
		ois.close();
		socket.close();
	}
}

