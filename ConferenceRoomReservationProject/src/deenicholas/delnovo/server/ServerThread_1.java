package deenicholas.delnovo.server;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor;
import deenicholas.delnovo.dao.*;
import deenicholas.delnovo.dto.*;
public class ServerThread_1 extends Thread
{
	/**
	 * member attributes
	 */
	private Socket clientSocket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private BookingDTO bookingDto = null;
	private SearchDTO searchDto = null;
	private CRRSDAO dao = null;
	public ServerThread_1(Socket threadSocket) 
	{
			
			try {
				this.clientSocket = threadSocket;
				dao = new CRRSDAO();
				ois = new ObjectInputStream(clientSocket.getInputStream());
				oos = new ObjectOutputStream(clientSocket.getOutputStream());
				System.out.println("streams made");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
	@Override
	public void run()
	{
		while(true){
			try 
			{
				String message = ois.readUTF();
				System.out.println("message from client: " + message);
	/*****************************************LOGIN*********************************************************/
				if( message!=null&&message.equals("login"))
				{
						String email = ois.readUTF();
						String psw =  ois.readUTF();
						UserDTO user = dao.findUser(email, psw);
						oos.writeObject(user); 
						oos.flush();
						System.out.println("user logged in: " + user);
				}
	/*****************************************SEARCH FOR CONFERENCE ROOMS*********************************************************/	
				if(message!=null  &&  (message.equals("search")  ))
				{
					searchDto = (SearchDTO) ois.readObject();
					Vector<ListDTO> conferenceRooms = dao.findRooms(searchDto);
					oos.writeObject(conferenceRooms);
					oos.flush();
				}

	/****************************************RESERVE A CONFERENCE ROOM*********************************************************/	
				if(  message!=null && (message.equals("Book")  ))
				{
					
					bookingDto = (BookingDTO) ois.readObject();
					HiredEquipmentDTO equipment = dao.bookRoom(bookingDto);
					oos.writeUTF("SuccessfulBooking");
					oos.flush();
					if( equipment != null )
					{
						//MUST SEND AN EMAIL TO AV TEAM TO HIRE ADDITIONAL EQIUPMENTT
						
						 String AVTeamEmailAddress =  "avteamequipment@gmail.com";//password: avteam1234
						 //Sends email to the AV team when the client making a booking opts to hire additional equipment from the AVTeam					
						//SendEmailsToUsersAndToAVTeam.SendEmailToAVTeam(AVTeamEmailAddress, equipment);				
					}
					
				}
	/****************************************VIEW WAITING LIST*********************************************************/	
				if( message!=null  && (message.equals("viewWaitingList") ))
				{
					System.out.println("in view waiting list");
					
					searchDto = (SearchDTO) ois.readObject();
					System.out.println("search object read, object is: " + searchDto);
					
					Vector<ListDTO> waitList = dao.viewWaitlist(searchDto);
					
					oos.writeObject(waitList);
					oos.flush();
					System.out.println("object written in view waitlist");
				}
	/****************************************ADD USER ON WAITING LIST********************************************************/				
				if(message!=null && (message.equals("PutMeOnWaitingList") ))
				{
					
					String email = ois.readUTF();
					BookingDTO bookingDto = (BookingDTO) ois.readObject();
					boolean result = dao.addToWaitList(bookingDto);
					//TO DO: get the email of the current user 
					
					if(result == true)
					{
						oos.writeUTF("addedToWaitingListSuccessfully");
						oos.flush();
						
//						Send email to the client currently holding the room when another client volunteers to be added on the waiting list
						//SendEmailsToUsersAndToAVTeam.sendEmailToClients(email, bookingDto);
						
					}
					else
					{
						oos.writeUTF("addingToWaitingListNotSuccessful");
						oos.flush();
					}
						
				}
	/****************************************RETURNS ALL THE BOOKINGS OF A PARTICULAR USER********************************************************/	
				if( message!=null  &&(message.equals("cancel")))
				{
					String email = ois.readUTF();
					Vector<BookingDTO> bookingsOfUser = dao.findUserBookings(email);	
					oos.writeObject(bookingsOfUser);
					oos.flush();
				}
	/*************************************************QUERY A BOOKING***********************************************/	
				if(message!=null&& (message.equals("ShowMeAListOfAllTheBookings")  ))
				{
					Vector<BookingDTO> query = dao.query();
					oos.writeObject(query);
					oos.flush();
				}
	/***********************************************VIEW RESERVATIONS************************************************/	
				if( message!=null &&(message.equals("viewReservation") ))
				{
				
					String email = ois.readUTF();
					Vector<BookingDTO> query = dao.viewResveration(email);
					oos.writeObject(query);
					oos.flush();
				}
	/***********************************************CONFIRM RESERVATION************************************************/	
				if( message!=null&& (message.equals("confirmReservation") ))
				{	//STOP THE TIMER
					BookingDTO confirmReservation = (BookingDTO) ois.readObject();//chek if this initializes the object
					HiredEquipmentDTO confirmedReservation = dao.confirmReservation(confirmReservation);
					
//					String AVTeamEmailAddress = "AVTeam@gmail.com";
					 String AVTeamEmailAddress =  " lnshakoane@gmail.com";
					 if(confirmedReservation!=null){
						 	//SendEmailsToUsersAndToAVTeam.SendEmailToAVTeam(AVTeamEmailAddress, confirmedReservation);
					 }
				}
	/***********************************************CANCEL RESERVATION***********************************************/	
				if(  message!=null&& (message.equals("cancelReservation")  ))
				{	
					
					BookingDTO cancelReservation = (BookingDTO) ois.readObject();
					BookingDTO bookingDto= dao.cancelReservation(cancelReservation);
					if(bookingDto!=null)
					{
					boolean result = dao.validateBooking(bookingDto);
					
					if(result == true)
					{
//						1.send email to the person on the top on the waiting list to confirm
					//	SendEmailsToUsersAndToAVTeam.sendEmailToFirstPersonOnWaitingListWhenRoomBecomesAvailable(bookingDto.getUser().getEmail(), bookingDto);
						//Timing countDown = new Timing(300, bookingDto);
		            }
					
				}
				}

	/****************************************REMOVES A PARTICULAR BOOKING FOR A USER*********************************************************/
			if(  message!=null && (message.equals("RemoveBooking")  ))
				{
					
					BookingDTO bookingDTO = (BookingDTO)ois.readObject();
					BookingDTO bookingDto = dao.cancelBooking(bookingDTO);
					oos.writeUTF("BookingRemoved");
					oos.flush();
					if(bookingDTO.getEquipment().size() != 0)
					{
						String aVTeamEmail = "avteamequipment@gmail.com"; //password: avteam1234
						Time time =  new Time(bookingDTO.getStartH(),bookingDTO.getStartM() , 0);
						HiredEquipmentDTO hiredEquip = new HiredEquipmentDTO(time.toString(), bookingDTO.getDate(), bookingDTO.getConference().getRoomName(),bookingDTO.getEquipment());
						//SendEmailsToUsersAndToAVTeam.sendEmailToAVTeamWhenCancellingMeetingWithHiredEquipments(aVTeamEmail, hiredEquip);
					}
					if(bookingDto!=null)
					{
					boolean result = dao.validateBooking(bookingDto);
					
					if(result == true)
					{
//						1.send email to the person on the top on the waiting list to confirm
						//SendEmailsToUsersAndToAVTeam.sendEmailToFirstPersonOnWaitingListWhenRoomBecomesAvailable(bookingDto.getUser().getEmail(), bookingDto);
						//Timing countDown = new Timing(300, bookingDto);
		            }
					
				}
			}
						
					//2.send email to the supplier incase the person who cancelled has additional equipment ordered, only once the next person inline confirms the booking
//						stopTimer();
					//timesUp(String message, String emailAdd)
					
//				}
	/****************************************NUMBER OF CANCELLATION REPORT*********************************************************/				
				if( message!=null && (message.equals("CancellationReport")  ))
				{
					String date1 = ois.readUTF();			
					String date2 = ois.readUTF();
					int noOfCancellations = dao.viewCancellations(date1, date2);
					oos.writeInt(noOfCancellations);
					oos.flush();
				}
	/****************************************REQUEST WAITING LIST REPORT*********************************************************/		
				if(  message!=null &&  (message.equals("requestForWaitingList")))
				{
					Vector<BookingDTO> bookingDt= dao.requestWaitlist(); 
					oos.writeObject(bookingDt);
					oos.flush();
				}
	/****************************************EQUIPMENT REQUEST REPORT************************************************************/			
				if( message!=null &&  (message.equals("equipmentRequest") ))
				{
					String date1 = ois.readUTF();
					String date2 = ois.readUTF();
					Vector<String> equipment = dao.requestEquipment(date1, date2);
					
					oos.writeObject(equipment);
					oos.flush();
				}
	/****************************************OCCUPATION PERCENTAGE REPORT************************************************************/				
				if( message!=null && (message.equals("OccupationPercentageOfRooms") ))
				{
					
					String date1 = ois.readUTF();
					String date2 =  ois.readUTF();
					ArrayList<String> percentage = dao.percentageOccupation(date1, date2);	
					oos.writeObject(percentage);
					oos.flush();
				}
				
	/****************************************END************************************************************/	
				if(message!=null && message.equals("END")){
					oos.close();
					ois.close();
					clientSocket.close();
				}
				
				
				
			} catch (IOException e) 
			{
				continue;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
	/********************************************************************************************************/	
		
		}

	}

