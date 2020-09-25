package deenicholas.delnovo.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import deenicholas.delnovo.dto.BookingDTO;
import deenicholas.delnovo.dto.HiredEquipmentDTO;


public class SendEmailsToUsersAndToAVTeam 
{
	/**
	 * Sends a courtesy email to the person holding a room, only when another user volunteers to be added on the waiting list for that particular room
	 * @param recipientUserEmailAddress
	 * @param bookingDTO
	 */
	public static void sendEmailToClients(String recipientUserEmailAddress, BookingDTO bookingDTO)
	{
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.debug", "true");
        
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("delnovovzap@gmail.com", "delnovo1234");
                
            }
        }
        );
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("delnovovzap@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientUserEmailAddress));
            message.setSubject("Courtesy Email: Do you Still need the following room");
            message.setText("Dear User" + 
            "\nIt looks like there are people who might be interested in the room you booked, if you no longer need the room please cancel the following booking "
            		+ "else, ignore this email:" +
            "\nBooking Details: " +"\nRoom Venue: " + bookingDTO.getConference().getRoomName() + "\nTime:   " + bookingDTO.getStartH() + ":" +  bookingDTO.getStartM()  + "-" +  bookingDTO.getEndH() 
			+ ":" +  bookingDTO.getEndM()  + " \nDuration: "  + bookingDTO.getLength() +" \nDate: " + bookingDTO.getDate()
			+ "\nYours Sincerely\nJavaDudes Software corp");
            Transport.send(message);

        } catch (Exception e) {
           System.out.println("error: " + e.toString());
        }
	}
	/**
	 * Sends an automated email to the AV team whenever a user makes a booking and hires  equipment
	 * @param AVTeamEmailAddress
	 * @param bookingDTO
	 */
	public static void SendEmailToAVTeam(String AVTeamEmailAddress, HiredEquipmentDTO equipmentDTO)
	{
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.debug", "true");
        
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("delnovovzap@gmail.com", "delnovo1234");
                
            }
        }
        );
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("delnovovzap@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(AVTeamEmailAddress));
            message.setSubject("Equipment needed");
            message.setText("Dear AV Team " + 
            "\nPlease send the following equipment to JavaDudes for the specified meeting" + 
            "\nMeeting details: " + equipmentDTO.toString()+"\nYours Sincerely\n JavaDudes Software corp");
            
            Transport.send(message);

        } catch (Exception e) 
        {
           System.out.println("error: " + e.toString());
        }
	}
	/**
	 * Sends an email to the AV team when a user cancels a booking that has hired equipment in it
	 * @param AVTeamEmailAddress
	 * @param bookingDTO
	 */
	public static void sendEmailToAVTeamWhenCancellingMeetingWithHiredEquipments(String AVTeamEmailAddress, HiredEquipmentDTO hiredEquipment)
	{

		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.debug", "true");
        
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("delnovovzap@gmail.com", "delnovo1234");
                
            }
        }
        );
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("delnovovzap@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(AVTeamEmailAddress));
            message.setSubject("Cancelling hired equipment ");
            message.setText("Dear AV Team " + 
            "\nPlease cancel the following equipment hired by JavaDudes for the specified meeting" + 
            "\nMeeting details: " + hiredEquipment.toString()+ "\nYours Sincerely\nJavaDudes Software corp");
            
            Transport.send(message);

        } catch (Exception e) 
        {
           System.out.println("error: " + e.toString());
        }
	}
	/**
	 * Send email to the first person on the waiting list when room becomes available due to a booking cancellation or when confirm reservation is declined
	 * @param userEmail
	 * @param bookingDto
	 */
	public static void sendEmailToFirstPersonOnWaitingListWhenRoomBecomesAvailable(String userEmail, BookingDTO bookingDto)
	{
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.debug", "true");
        
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("delnovovzap@gmail.com", "delnovo1234");
                
            }
        }
        );
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("delnovovzap@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Confirm Room Reservation ");
            message.setText("Dear " + bookingDto.getUser().getName() + bookingDto.getUser().getSurname() + " who is next on the room waiting list " + 
            "\nThe room you want with the following details has been made available for your use please go and confirm the reservation within 24 hours" + 
            	"\nMeeting details: " + bookingDto.toString()+"\nYours Sincerely\n JavaDudes Software corp");
         
            Transport.send(message);

        } catch (Exception e) 
        {
           System.out.println("error: " + e.toString());
        }
	}
	
	public static void main(String [] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the email address of the recipient: ");
		String receipientEmailAdd = br.readLine();
		
		//SendEmailsToUsers.sendEmailToClients(receipientEmailAdd);
		
		
	}

}
