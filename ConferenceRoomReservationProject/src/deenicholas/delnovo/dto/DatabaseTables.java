package deenicholas.delnovo.dto;
import java.sql.*;

public class DatabaseTables {
	private static String  userTable ="create table usertable(Name varchar(60) not null,Surname varchar(60) not null,Title boolean not null,Department  varchar(60) not null,ContactNumber varchar(10) not null,EmailAddress varchar(60) not null,Password varchar(60) not null);";
	private static String conferenceRoomTable = "create table conferenceroomtable(RoomName varchar(10) not null , Capacity int not null,Equipment1 varchar(60) not null,Equipment2 varchar(60) not null,Equipment3 varchar(60),Equipment4 varchar(60),Equipment5 varchar(60));";
	private static String bookedTable = " create table bookedtable(EmailAddress varchar(60) not null,RoomName varchar(10) not null,Description varchar(300) not null,Duration int not null,StartTime Time not null,EndTime Time not null,Date date not null,NumberOfAttendees int not null,"
			+ "Equipment1 varchar(60),Equipment2 varchar(60),Equipment3 varchar(60),Equipment4 varchar(60),Equipment5 varchar(60),Equipment6 varchar(60),Equipment7 varchar(60),Equipment8 varchar(60),Equipment9 varchar(60));";
	private static String reservation = "create table reservationtable(EmailAddress varchar(60) not null,RoomName varchar(10) not null,Description varchar(300) not null,Duration int not null,StartTime Time not null,EndTime Time not null,Date date not null);";
	private static String waitlisttable =" create table waitlisttable(EmailAddress varchar(60) not null,RoomName varchar(10) not null,Description varchar(300) not null,Duration int not null,StartTime Time not null,EndTime Time not null,Date date not null,NumberOfAttendees int not null,"
			+ "Equipment1 varchar(60),Equipment2 varchar(60),Equipment3 varchar(60),Equipment4 varchar(60),Equipment5 varchar(60),Equipment6 varchar(60),Equipment7 varchar(60),Equipment8 varchar(60),Equipment9 varchar(60));";
	private static String cancellationTable = "create table cancellationTable(Date date not null);";	
	private static String hiredequipmenttable = " create table hiredequipmenttable(Date date not null,Equipment varchar(60) not null);";
	//********END OF TABLES**************************
	
	private static String insert1 = "insert into conferenceroomtable values('A-CR1',30,'Smart Electronic White-Board','Digital Projector','Microphone','Amplifier','Wi-fi');";
	private static String insert2 = " insert into conferenceroomtable values('A-CR2',15,'Digital Projector','Wi-fi',null,null,null);";
	private static String insert3 = " insert into conferenceroomtable values('A-CR3',10,'White Board','Wi-fi',null,null,null);";
	private static String insert4=" insert into conferenceroomtable values('B-CR1',25,'Smart Electronic White-Board','Digital Projector','Wi-fi',null,null);";
	private static String insert5=" insert into conferenceroomtable values('B-CR2',15,'Digital Projector','Wi-fi',null,null,null);";
	private static String insert6="insert into conferenceroomtable values('B-CR3',10,'White board','Wi-fi',null,null,null);";
	private static String insert7=" insert into conferenceroomtable values('C-CR1',20,'Smart Electronic White-Board','Digital Projector','Wi-fi',null,null);";
	private static String insert8="insert into conferenceroomtable values('C-CR2',15,'Smart Electronic White-Board','Digital Projector','Wi-fi',null,null);";
	private static String insert9="insert into conferenceroomtable values('C-CR3',10,'White Board','Wi-fi',null,null,null);";
	private static String insert10="insert into conferenceroomtable values('D-CR1',20,'Digital Projector','Wi-fi',null,null,null);";
	private static String insert11="insert into conferenceroomtable values('D-CR2',15,'Digital Projector','Wi-fi',null,null,null);";
	private static String insert12="insert into conferenceroomtable values('D-CR3',10,'Digital Projector','Wi-fi',null,null,null);";
	
	private static String user1="insert into usertable values('Keanen','Moodley',true,'IT','0748916637','keanenmoodley@gmail.com','1234');";
	private static String user2="insert into usertable values('Nicholas','Dee',false,'Finance','0746661997','deenicholas25@gmail.com','1234');";
	private static String user3="insert into usertable values('Lerato','Shakoane',true,'Procurement','0670011596','lnshakoane@gmail.com','1234');";
	private static String user4="insert into usertable values('Christopher','Spalding',false,'HR','0732343370','dupeysam@gmail.com','1234');";
	
	public static void main(String[] args) {
		//DONT FORGET TO MAKE THE DATABASE crrs
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url =  "jdbc:mysql://localhost:3306/crrs";
			String username = "root";
			String psw = "root";
			Connection con = DriverManager.getConnection(url, username, psw);
			Statement state = con.createStatement();
			state.executeUpdate(userTable);
			state.executeUpdate(conferenceRoomTable);
			state.executeUpdate(bookedTable);
			state.executeUpdate(reservation);
			state.executeUpdate(waitlisttable);
			state.executeUpdate(cancellationTable);
			state.executeUpdate(hiredequipmenttable);
			
			state.executeUpdate(insert1);
			state.executeUpdate(insert2);
			state.executeUpdate(insert3);
			state.executeUpdate(insert4);
			state.executeUpdate(insert5);
			state.executeUpdate(insert6);
			state.executeUpdate(insert7);
			state.executeUpdate(insert8);
			state.executeUpdate(insert9);
			state.executeUpdate(insert10);
			state.executeUpdate(insert11);
			state.executeUpdate(insert12);
			
			state.executeUpdate(user1);
			state.executeUpdate(user2);
			state.executeUpdate(user3);
			state.executeUpdate(user4);
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
  