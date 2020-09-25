package deenicholas.delnovo.dao;
import java.sql.*;
import java.sql.Date;
import deenicholas.delnovo.server.*;
import java.util.*;
import deenicholas.delnovo.dto.*;
public class CRRSDAO {
	
	private Connection connection;

	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url =  "jdbc:mysql://localhost:3306/crrs";
			String username = "root";
			String psw = "root";
			Connection con = DriverManager.getConnection(url, username, psw);
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<ListDTO> findRooms(SearchDTO bean){
		Vector<ListDTO> listA = new Vector<>();
		 Vector<ListDTO> listW = new Vector<>();
		ArrayList<ConferenceRoomDTO> matchedRooms = new ArrayList<>();
		connection=getConnection();
		int startH = bean.getStartH();
		int startM = bean.getStartM();
		int endH = bean.getEndH();
		int endM = bean.getEndM();
		int lengthOfmeeting = bean.getLength();
		Time startTime = new Time(startH, startM, 0);
		Time interval = new Time(startH, startM+lengthOfmeeting, 0);
		Time endTime = new  Time(endH, endM+lengthOfmeeting, 0);
		Date startDate = new Date(bean.getD1().getYear(), bean.getD1().getMonth(), bean.getD1().getDate()); 
		Date endDate = new Date(bean.getD2().getYear(), bean.getD2().getMonth(), bean.getD2().getDate());
		int amount = bean.getCapacity();
		int c=0;
		if(amount<=10){
			c=10;
		}
		if(amount>10 && amount<=15){
			c=15;
		}
		if(amount>15 && amount<=20){
			c=20;
		}
		if(amount>20 && amount<=25){
			c=25;
		}
		if(amount>25 && amount<=30){
			c=30;
		}
		try {
				Statement s = connection.createStatement();
				ResultSet rs =s.executeQuery("select * from conferenceroomtable where Capacity="+c+";");
				while(rs.next()){
					String roomName = rs.getString(1);
					int capacity = rs.getInt(2);
					String equip;
					int count =3;
					ArrayList<String> standardEquipment = new ArrayList<String>();
					while((equip=rs.getString(count))!=null){
					standardEquipment.add(equip);
					count++;
					if(count==8){
						break;
					}
				}
					ConferenceRoomDTO room = new ConferenceRoomDTO(roomName, capacity,standardEquipment);
					matchedRooms.add(room);
				}
				int counter=1;
				for (int i = 0; i < matchedRooms.size(); i++) {
					  while(startDate.compareTo(endDate)<=0){
						while(!(interval.getHours()==endTime.getHours()&&interval.getMinutes()==endTime.getMinutes())){
							PreparedStatement prep = connection.prepareStatement("select * from bookedtable where roomName = ? and date = ? and startTime = ? and endTime = ?;");
							prep.setString(1, matchedRooms.get(i).getRoomName());
							prep.setDate(2, startDate);
							prep.setTime(3, startTime);
							prep.setTime(4, interval);
							try {
								ResultSet rs1 = prep.executeQuery();
								rs1.next();
								String email = rs1.getString(1);
							    ListDTO list1 = new ListDTO(startTime, interval, startDate, matchedRooms.get(i),email,0);
								listW.addElement(list1);
							} catch (Exception e) {
								ListDTO list = new ListDTO(startTime, interval, startDate, matchedRooms.get(i),null,0  ); 
								listA.addElement(list);
							}
							startTime = new Time(interval.getHours(), interval.getMinutes(), 0);
							interval = new Time(startTime.getHours(), startTime.getMinutes()+lengthOfmeeting, 0);
						}
						startDate = new Date(bean.getD1().getYear(), bean.getD1().getMonth(), bean.getD1().getDate()+counter);
						counter++;
						startTime = new Time(startH, startM, 0);
						interval = new Time(startH, startM+lengthOfmeeting, 0);
					}
					  startDate = new Date(bean.getD1().getYear(), bean.getD1().getMonth(), bean.getD1().getDate());
					  startTime = new Time(startH, startM, 0);
					  interval = new Time(startH, startM+lengthOfmeeting, 0);
					  counter=1;
				}
				
			connection.close();
			return listA;
		} catch (SQLException e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}
	public Vector<ListDTO> findBRooms(SearchDTO bean){
		Vector<ListDTO> listA = new Vector<>();
		 Vector<ListDTO> listW = new Vector<>();
		ArrayList<ConferenceRoomDTO> matchedRooms = new ArrayList<>();
		connection=getConnection();
		int startH = bean.getStartH();
		int startM = bean.getStartM();
		int endH = bean.getEndH();
		int endM = bean.getEndM();
		int lengthOfmeeting = bean.getLength();
		Time startTime = new Time(startH, startM, 0);
		Time interval = new Time(startH, startM+lengthOfmeeting, 0);
		Time endTime = new  Time(endH, endM+lengthOfmeeting, 0);
		Date startDate = new Date(bean.getD1().getYear(), bean.getD1().getMonth(), bean.getD1().getDate()); 
		Date endDate = new Date(bean.getD2().getYear(), bean.getD2().getMonth(), bean.getD2().getDate());
		int amount = bean.getCapacity();
		int c=0;
		if(amount<=10){
			c=10;
		}
		if(amount>10 && amount<=15){
			c=15;
		}
		if(amount>15 && amount<=20){
			c=20;
		}
		if(amount>20 && amount<=25){
			c=25;
		}
		if(amount>25 && amount<=30){
			c=30;
		}
		try {
				Statement s = connection.createStatement();
				ResultSet rs =s.executeQuery("select * from conferenceroomtable where Capacity="+c+";");
				while(rs.next()){
					String roomName = rs.getString(1);
					int capacity = rs.getInt(2);
					String equip;
					int count =3;
					ArrayList<String> standardEquipment = new ArrayList<String>();
					while((equip=rs.getString(count))!=null){
					standardEquipment.add(equip);
					count++;
					if(count==8){
						break;
					}
				}
					ConferenceRoomDTO room = new ConferenceRoomDTO(roomName, capacity,standardEquipment);
					matchedRooms.add(room);
				}
				int counter=1;
				for (int i = 0; i < matchedRooms.size(); i++) {
					  while(startDate.compareTo(endDate)<=0){
						while(!(interval.getHours()==endTime.getHours()&&interval.getMinutes()==endTime.getMinutes())){
							PreparedStatement prep = connection.prepareStatement("select * from bookedtable where roomName = ? and date = ? and startTime = ? and endTime = ?;");
							prep.setString(1, matchedRooms.get(i).getRoomName());
							prep.setDate(2, startDate);
							prep.setTime(3, startTime);
							prep.setTime(4, interval);
							try {
								ResultSet rs1 = prep.executeQuery();
								rs1.next();
								String email = rs1.getString(1);
							    ListDTO list1 = new ListDTO(startTime, interval, startDate, matchedRooms.get(i),email,0);
								listW.addElement(list1);
							} catch (Exception e) {
			
							}
							startTime = new Time(interval.getHours(), interval.getMinutes(), 0);
							interval = new Time(startTime.getHours(), startTime.getMinutes()+lengthOfmeeting, 0);
						}
						startDate = new Date(bean.getD1().getYear(), bean.getD1().getMonth(), bean.getD1().getDate()+counter);
						counter++;
						startTime = new Time(startH, startM, 0);
						interval = new Time(startH, startM+lengthOfmeeting, 0);
					}
					  startDate = new Date(bean.getD1().getYear(), bean.getD1().getMonth(), bean.getD1().getDate());
					  startTime = new Time(startH, startM, 0);
					  interval = new Time(startH, startM+lengthOfmeeting, 0);
 					  counter=1;
				}
				
			connection.close();
			return listW;
		} catch (SQLException e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}

	public HiredEquipmentDTO bookRoom(BookingDTO booking){
		 connection=getConnection();
		 try {
			PreparedStatement ps = connection.prepareStatement("insert into bookedtable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			ps.setString(1, booking.getUser().getEmail());
			ps.setString(2, booking.getConference().getRoomName());
			ps.setString(3, booking.getDescription());
			ps.setInt(4, booking.getLength());
			Time t = new Time(booking.getStartH(), booking.getStartM(),0);
			ps.setTime(5, t);
			Time tt = new Time(booking.getEndH(), booking.getEndM(), 0);
			ps.setTime(6, tt);
			Date d = new Date(booking.getDate().getYear(),booking.getDate().getMonth(),booking.getDate().getDate());
			ps.setDate(7, d);
			ps.setInt(8, booking.getNoOfAttendees());
			for (int i = 9; i<18; i++){
				ps.setString(i, null);
			}
			ArrayList<String> list =  booking.getEquipment();
			for (int ii = 0; ii <list.size(); ii++){
				PreparedStatement ps1 = connection.prepareStatement("insert into hiredequipmenttable values(?,?);");
				ps1.setDate(1, d);
				ps1.setString(2,list.get(ii));
				ps1.executeUpdate();
				ps.setString(9+ii,list.get(ii));
			 }
			ps.executeUpdate();
			if(list.size()!=0){
				String time = String.valueOf(booking.getStartH())+":"+String.valueOf(booking.getStartM());
				HiredEquipmentDTO e = new HiredEquipmentDTO(time, booking.getDate(),booking.getConference().getRoomName(), list);
				connection.close();
				return e;
			}
		} catch (SQLException e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e.printStackTrace();
		}
		 return null;
	}
	
	public Vector<BookingDTO> findUserBookings(String email){ 
		Vector<BookingDTO> v = new Vector<>();
		connection=getConnection();
		try {
			Statement s = connection.createStatement();
			ResultSet rs1 = s.executeQuery("select * from bookedtable where EmailAddress='"+email+"' and Date>= curdate();");
			while(rs1.next()){
				ArrayList<String> equip = new ArrayList<>();
				int count=9;
				String equipment;
				String roomName=rs1.getString(2);
				String description=rs1.getString(3);
				int length=rs1.getInt(4);
				java.sql.Time start=rs1.getTime(5);
				java.sql.Time end=rs1.getTime(6);
				java.sql.Date date1=rs1.getDate(7);
				int no = rs1.getInt(8);
				while((equipment=rs1.getString(count))!=null){
					equip.add(equipment);
					count++;
				}
				Statement ss = connection.createStatement();
				ResultSet rs2 = ss.executeQuery("select * from usertable where emailAddress = '"+email+"';");
				rs2.next();
				String name = rs2.getString(1);
				String surname = rs2.getString(2);
				Boolean title = rs2.getBoolean(3);
				String dept = rs2.getString(4);
				String tel = rs2.getString(5);
				String psw = rs2.getString(7);
				UserDTO user = new UserDTO(name, surname, String.valueOf(title), dept, tel, email, psw);
				Statement s1 = connection.createStatement();
				ResultSet rs3 = s1.executeQuery("select * from conferenceroomtable where roomName ='"+roomName+"';");
				rs3.next();
				int capacity = rs3.getInt(2);
				String equip1;
				int count1 =3;
				ArrayList<String> standardEquipment = new ArrayList<String>();
				while((equip1=rs3.getString(count1))!=null){
					standardEquipment.add(equip1);
					count1++;
					if(count1==8){
						break;
					}
				}
				ConferenceRoomDTO conference = new ConferenceRoomDTO(roomName, capacity,standardEquipment);
				BookingDTO b = new BookingDTO(user, conference, description, length, start.getHours(), start.getMinutes(), end.getHours(), end.getMinutes(), date1,no, equip);
				v.addElement(b);
			}
			connection.close();
			return v;
		} catch (SQLException e) {
			try {
				connection.close();
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		}
	
	public HiredEquipmentDTO confirmReservation(BookingDTO book){
		connection= getConnection();
		try {
			PreparedStatement state = connection.prepareStatement("delete from reservationtable where roomName = ? and Date = ? and startTime = ? and Endtime = ?;");
			state.setString(1, book.getConference().getRoomName());
			java.sql.Date d = new Date(book.getDate().getYear(), book.getDate().getMonth(), book.getDate().getDate());
			state.setDate(2,d);
			Time tt = new  Time(book.getStartH(), book.getStartM(), 0);
			Time te = new  Time(book.getEndH(), book.getEndM(), 0);
			state.setTime(3, tt);
			state.setTime(4, te);
			state.executeUpdate();
			ArrayList<String> list = book.getEquipment();
			for (int ii = 0; ii <list.size(); ii++){
				PreparedStatement ps1 = connection.prepareStatement("insert into hiredequipmenttable values(?,?);");
				ps1.setDate(1, d);
				ps1.setString(2,list.get(ii));
				ps1.executeUpdate();
			 }
			if(list.size()!=0){
				String time = String.valueOf(book.getStartH())+":"+String.valueOf(book.getStartM());
				HiredEquipmentDTO e = new HiredEquipmentDTO(time, book.getDate(),book.getConference().getRoomName(), list);
				connection.close();
				return e;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<BookingDTO> viewResveration(String email){
		connection =  getConnection();
		Vector<BookingDTO> v = new Vector<>();
		try {
			Statement s = connection.createStatement();
			ResultSet rs1 = s.executeQuery("select * from reservationtable where EmailAddress = '"+email+"';");
			while(rs1.next()){
				String roomName=rs1.getString(2);
				String description=rs1.getString(3);
				int length=rs1.getInt(4);
				java.sql.Time start=rs1.getTime(5);
				java.sql.Time end=rs1.getTime(6);
				java.sql.Date date1=rs1.getDate(7);
				Statement s11 = connection.createStatement();
				ResultSet rs33= s11.executeQuery("select * from bookedtable where EmailAddress = '"+email+"' and startTime = '"+start+"' and endTime = '"+end+"' and roomName = '"+roomName+"' and Date = ' "+date1 +"';");
				ArrayList<String> equip = new ArrayList<>();
				int count=9;
				String equipment;
				rs33.next();
				int no = rs33.getInt(8);
				while((equipment=rs33.getString(count))!=null){
					equip.add(equipment);
					count++;
				}
				Statement ss = connection.createStatement();
				ResultSet rs2 = ss.executeQuery("select * from usertable where emailAddress = '"+email+"';");
				rs2.next();
				String name = rs2.getString(1);
				String surname = rs2.getString(2);
				Boolean title = rs2.getBoolean(3);
				String dept = rs2.getString(4);
				String tel = rs2.getString(5);
				String psw = rs2.getString(7);
				UserDTO user = new UserDTO(name, surname, String.valueOf(title), dept, tel, email, psw);
				Statement ss3 = connection.createStatement();
				ResultSet rs3 = ss3.executeQuery("select * from conferenceroomtable where roomName ='"+roomName+"';");
				rs3.next();
				int capacity = rs3.getInt(2);
				String equip1;
				int count1 =3;
				ArrayList<String> standardEquipment = new ArrayList<String>();
				while((equip1=rs3.getString(count1))!=null||count1>8){
					standardEquipment.add(equip1);
					count1++;
					if(count1==8){
						break;
					}
				}
				ConferenceRoomDTO conference = new ConferenceRoomDTO(roomName, capacity,standardEquipment);
				BookingDTO b = new BookingDTO(user, conference, description, length, start.getHours(), start.getMinutes(), end.getHours(), end.getMinutes(), date1,no, equip);
				v.add(b);
			}
			connection.close();
			return v;
		} catch (SQLException e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e.printStackTrace();
		}
		return v;
	}
	
	public boolean validateBooking(BookingDTO book){
		java.util.Date d = new java.util.Date();
		Date d1 = new Date(d.getYear(), d.getMonth(), d.getDate());
		System.out.println("Bookings date = " + book.getDate());
		 int i = book.getDate().compareTo(d1);
		 System.out.println("time difference = " + i);
		if(i<0){
			cancelReservation(book);
			System.out.println("date passed");
			return false;
		}
		
		if(i==0){
			Time currentTime = new Time(d.getHours(),d.getMinutes()+300,0);
			System.out.println("currentTime = " + currentTime);
			Time bookingTime = new Time(book.getStartH(), book.getStartM(), 0);
			if((bookingTime.compareTo(currentTime)>=0)){
				System.out.println("Today but avalible");
				Timing timer = new Timing(120, book);//18 000 no of seconds after testing
				return true;
			}
			cancelReservation(book);
			System.out.println("today but passed");
			return false;
		}
		
		if(i>0){
			System.out.println("date coming");
			Timing timer = new Timing(60 ,book);//86400 no of seconds after testing
			return true;  
		}
		return false;
	}
	//************************REPORTS**********************  
	
	public int viewCancellations(String d1,String d2){
		connection = getConnection();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select count(*) from cancellationTable where Date >= '"+d1+"' and Date<='"+d2+"';");
			rs.next();
			int i = rs.getInt("count(*)");
			connection.close();
			return i;
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<String> percentageOccupation(String date1,String date2){
		connection = getConnection();
		ArrayList<String> list = new ArrayList<>();
		ArrayList<Integer> numbers =new ArrayList<>();
		ArrayList<String> sorted = new ArrayList<>();
		ArrayList<String> rooms = new ArrayList<>();
		rooms.add("A-CR1");
		rooms.add("A-CR2");
		rooms.add("A-CR3");
		rooms.add("B-CR1");
		rooms.add("B-CR2");
		rooms.add("B-CR3");
		rooms.add("C-CR1");
		rooms.add("C-CR2");
		rooms.add("C-CR3");
		rooms.add("D-CR1");
		rooms.add("D-CR2");
		rooms.add("D-CR3");
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select roomName,count(*) from bookedtable where Date >= '"+date1+"' and Date<='"+date2+"' group by roomName order by count(*) desc;");
			int count = 0;
			while(rs.next()){
				list.add(rs.getString(1));
				int ii= rs.getInt(2);
				numbers.add(ii);
				count+=ii;
			}
			for (int i = 0; i < numbers.size(); i++) {
				double no = numbers.get(i);
				double c = count;
				int k = (int) ((no/c)*100);
				System.out.println("k = " + k);
				String room = list.get(i);
				sorted.add(room + "= " +k+"%");
			}
			
			for (int i = 0; i < list.size(); i++) {
				 if(list.get(i).equals(rooms.get(i))){
					 rooms.remove(i);
				 }
			}
			for (int i = 0; i < rooms.size(); i++) {
				sorted.add(rooms.get(i)+ "= " +0+"%");
			}
			connection.close();
			return sorted;
		} catch (SQLException e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}
	
	public UserDTO findUser(String emailAddress, String pswd)
	{
		Connection con = this.getConnection();
		ResultSet rs;
		String querySQL = "SELECT * FROM usertable WHERE EmailAddress = '" + emailAddress + "' AND Password = '" + pswd + "';";
		try 
		{
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(querySQL);
			while(rs.next())
			{
				String name = rs.getString(1);
				String surname = rs.getString(2);
				String title = rs.getString(3);
				String dept = rs.getString(4);
				String tel = rs.getString(5);
				String checkedEmailAddress = rs.getString(6);
				String checkedPswd = rs.getString(7);
				UserDTO user = new UserDTO(name, surname, title, dept, tel, checkedEmailAddress, checkedPswd);
				con.close();
				return user;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				con.close();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean addToWaitList(BookingDTO booking)
	{
		Connection con = this.getConnection();
		ArrayList<String> equipmentList = new ArrayList<String>();
		String updateSQL = "INSERT INTO waitlisttable values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, booking.getUser().getEmail());
			pstmt.setString(2, booking.getConference().getRoomName());
			pstmt.setString(3, booking.getDescription());
			pstmt.setInt(4, booking.getLength());
			Time startTime = new Time(booking.getStartH(), booking.getStartM(), 0);
			pstmt.setTime(5, startTime);
			Time endTime = new Time(booking.getEndH(), booking.getEndM(), 0);
			pstmt.setTime(6, endTime);
			Date date = new Date(booking.getDate().getYear(), booking.getDate().getMonth(), booking.getDate().getDate());
			pstmt.setDate(7, date);
			pstmt.setInt(8, booking.getNoOfAttendees());
			
			for (int ii = 9; ii<18; ii++) {
				pstmt.setString(ii, null);
			}
			equipmentList = booking.getEquipment();
			for (int i = 0, i2 = 9; i < equipmentList.size(); i++, i2++) 
			{
				pstmt.setString(i2, equipmentList.get(i));	
			}
			pstmt.executeUpdate();
			con.close();
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				con.close();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	public BookingDTO cancelBooking(BookingDTO booking)
	{
		Connection con = this.getConnection();
		ResultSet rs;
		Time startTime = new Time(booking.getStartH(), booking.getStartM(), 0);
		Time endTime = new Time(booking.getEndH(), booking.getEndM(), 0);
		Date date = new Date(booking.getDate().getYear(), booking.getDate().getMonth(), booking.getDate().getDate());
		String update3SQL = "DELETE FROM bookedtable WHERE EmailAddress = '" + booking.getUser().getEmail() 
						  + "' AND RoomName = '" + booking.getConference().getRoomName()
						  + "' AND Description = '" + booking.getDescription() 
						  + "' AND Duration = " + booking.getLength() 
						  + " AND StartTime = '" + startTime 
						  + "' AND EndTime = '" + endTime 
						  + "' AND Date = '" + date + "';";
		String querySQL1 = "SELECT * FROM waitlisttable WHERE roomName = '"+booking.getConference().getRoomName()+"' and StartTime = '" + startTime 
				           + "' AND EndTime = '" + endTime 
				           + "' AND Date = '" + date + "';";
		String querySQL2 = "DELETE FROM waitlisttable WHERE EmailAddress = ? and roomName = ? and StartTime = ?  AND EndTime = ? AND Date = ? ;";
		String updateSQL = "INSERT INTO reservationtable values(?, ?, ?, ?, ?, ?, ?);";
		String updateSQL2 = "INSERT INTO bookedtable values(?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?);";
		String updateSQL4 = "INSERT INTO cancellationtable values(?);";
		try 
		{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(update3SQL);
			Statement stmt2 = con.createStatement();
			rs = stmt2.executeQuery(querySQL1);
			PreparedStatement pstmt5 = con.prepareStatement(updateSQL4);
			java.util.Date date2= new java.util.Date();
			Date date3 = new  Date(date2.getYear(), date2.getMonth(), date2.getDate());
			pstmt5.setDate(1, date3);
			pstmt5.executeUpdate();
			String emailAdd;
			try 
			{
				rs.next();
				emailAdd = rs.getString(1);
				PreparedStatement ps = con.prepareStatement(querySQL2);
				ps.setString(1, emailAdd);
				ps.setString(2, booking.getConference().getRoomName());
				ps.setTime(3, startTime);
				ps.setTime(4, endTime);
				ps.setDate(5, date);
				ps.executeUpdate();
			} 
			catch (Exception e) 
			{
				con.close();
				return null;
			}
			PreparedStatement pstmt3 = con.prepareStatement(updateSQL);
			PreparedStatement pstmt4 = con.prepareStatement(updateSQL2);
			Statement s = con.createStatement();
			ResultSet rs2 = s.executeQuery("select * from usertable where EmailAddress = '"+emailAdd+"';");
			rs2.next();
			String name = rs2.getString(1);
			String surname = rs2.getString(2);
			Boolean title = rs2.getBoolean(3);
			String dept = rs2.getString(4);
			String tel = rs2.getString(5);
			String psw = rs2.getString(7);
			UserDTO user = new UserDTO(name, surname, String.valueOf(title), dept, tel, emailAdd, psw);
			String roomName = rs.getString(2);
			Statement ss = con.createStatement();
			ResultSet rs3 = ss.executeQuery("select * from conferenceroomtable where roomName ='"+roomName+"';");
			rs3.next();
			int capacity = rs3.getInt(2);
			String equip;
			int count =3;
			ArrayList<String> standardEquipment = new ArrayList<String>();
			while((equip=rs.getString(count))!=null){
				standardEquipment.add(equip);
				count++;
				if(count == 8){
					break;
				}
			}
			ConferenceRoomDTO conference = new ConferenceRoomDTO(roomName, capacity,standardEquipment);
			String description = rs.getString(3);
			int length = rs.getInt(4);
			Time start = rs.getTime(5);
			Time end = rs.getTime(6);
			Date date1 = rs.getDate(7);
			int no = rs.getInt(8);
			ArrayList<String> equipment = new ArrayList<String>();
			int count1 = 9;
			String equip1;
			while((equip1=rs.getString(count1))!=null)
			{
				equipment.add(equip1);
				count1++;
			}
			BookingDTO room = new BookingDTO(user, conference, description, length, start.getHours(), start.getMinutes(), end.getHours(), end.getMinutes(), date1,no, equipment);
			pstmt3.setString(1, emailAdd);
			pstmt3.setString(2, roomName);
			pstmt3.setString(3, description);
			pstmt3.setInt(4, length);
			pstmt3.setTime(5, start);
			pstmt3.setTime(6, end);
			pstmt3.setDate(7, date1);
			pstmt3.executeUpdate();
			pstmt4.setString(1, emailAdd);
			pstmt4.setString(2, roomName);
			pstmt4.setString(3, description);
			pstmt4.setInt(4, length);
			pstmt4.setTime(5, start);
			pstmt4.setTime(6, end);
			pstmt4.setDate(7, date1);
			pstmt4.setInt(8, no);
			for (int ii = 9; ii<18; ii++) {
				pstmt4.setString(ii, null);
			}
			int cc = 9;
			for (int i =0; i <equipment.size(); i++) {
				pstmt4.setString(cc, equipment.get(i));
				cc++;
			}
			int i = pstmt4.executeUpdate();
			con.close();
			return room;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				con.close();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public Vector<BookingDTO> query()
	{
		Connection con = this.getConnection();
		String querySQL = "SELECT * FROM bookedtable WHERE Date >= CURDATE()";
		Vector<BookingDTO> bookings = new Vector<BookingDTO>();
		ResultSet rs;
		try 
		{
			PreparedStatement pstmt = con.prepareStatement(querySQL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String email = rs.getString(1);
				String roomName = rs.getString(2);
				String description = rs.getString(3);
				int length = rs.getInt(4);
				Time t1 = rs.getTime(5);
				Time t2 = rs.getTime(6);
				Date date = rs.getDate(7);
				int no = rs.getInt(8);
				ArrayList<String> equipment = new ArrayList<>();
				String eq;
				int c = 9;
				while((eq=rs.getString(c))!=null){
					equipment.add(eq);
					c++;
					if(c==18){
						break;
					}
				}
				Statement s = con.createStatement();
				ResultSet rs2 = s.executeQuery("select * from usertable where emailAddress = '"+email+"';");
				rs2.next();
				String name = rs2.getString(1);
				String surname = rs2.getString(2);
				Boolean title = rs2.getBoolean(3);
				String dept = rs2.getString(4);
				String tel = rs2.getString(5);
				String psw = rs2.getString(7);
				UserDTO user = new UserDTO(name, surname, String.valueOf(title), dept, tel, email, psw);
				Statement ss = con.createStatement();
				ResultSet rs3 = ss.executeQuery("select * from conferenceroomtable where roomName ='"+roomName+"';");
				rs3.next();
				int capacity = rs3.getInt(2);
				String equip;
				int count =3;
				ArrayList<String> standardEquipment = new ArrayList<String>();
				while((equip=rs3.getString(count))!=null){
					standardEquipment.add(equip);
					count++;
					if(count==8){
						break;
					}
				}
				ConferenceRoomDTO conference = new ConferenceRoomDTO(roomName, capacity,standardEquipment);
				BookingDTO booking = new BookingDTO(user, conference, description, length, t1.getHours(), t1.getMinutes(), t2.getHours(), t2.getMinutes(), date,no, equipment);
				bookings.add(booking);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				con.close();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return bookings;
	}
	
	
	public Vector<ListDTO> viewWaitlist(SearchDTO search)
	{
		Connection con = this.getConnection();
		Vector<ListDTO> bookedRooms = findBRooms(search);
		String querySQL = "SELECT count(*) FROM waitlisttable WHERE RoomName = ? AND Date = ? AND StartTime = ? AND EndTime = ? group by RoomName";
		ResultSet rs;
		int waitListNum = 0;
		int numRooms = bookedRooms.size();
		for(int i = 0; i <numRooms; i++)
		{
			try
			{
				PreparedStatement pstmt6 = con.prepareStatement(querySQL);
				pstmt6.setString(1, bookedRooms.elementAt(i).getConferenceRoom().getRoomName());
				pstmt6.setDate(2, bookedRooms.elementAt(i).getDate());
				pstmt6.setTime(3, bookedRooms.elementAt(i).getStartTime());
				pstmt6.setTime(4, bookedRooms.elementAt(i).getEndTime());
				rs = pstmt6.executeQuery();
				rs.next();
				waitListNum = rs.getInt(1);
				bookedRooms.elementAt(i).setWaitList(waitListNum);
			} 
			catch (SQLException e)
			{
				bookedRooms.elementAt(i).setWaitList(0);
			}
		}
		return bookedRooms;
	}
	
	public BookingDTO cancelReservation(BookingDTO booking)
	{
		Connection con = this.getConnection();
		Time startTime = new Time(booking.getStartH(), booking.getStartM(), 0);
		Time endTime = new Time(booking.getEndH(), booking.getEndM(), 0);
		Date date = new Date(booking.getDate().getYear(), booking.getDate().getMonth(), booking.getDate().getDate());
		String querySQL = "delete FROM reservationtable WHERE EmailAddress = '" + booking.getUser().getEmail() 
						  + "' AND RoomName = '" + booking.getConference().getRoomName()
						  + "' AND Description = '" + booking.getDescription() 
						  + "' AND Duration = '" + booking.getLength() 
						  + "' AND StartTime = '" + startTime 
						  + "' AND EndTime = '" + endTime 
						  + "' AND Date = '" + date + "';";
		BookingDTO b = null;
		try 
		{
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate(querySQL);
			if(i>0){
				b = this.cancelBooking(booking);
			}
			con.close();
			return b;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				con.close();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public void timeUp(BookingDTO booking)
	{
		this.cancelReservation(booking);
	}
	
	//***********************Reports*********************************
	public Vector<BookingDTO> requestWaitlist()
	{
		Connection con = this.getConnection();
		String querySQL = "SELECT * FROM waitlisttable;";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(querySQL);
			Vector<BookingDTO> waitList = new Vector<BookingDTO>();
			while(rs.next()){
				String email = rs.getString(1);
				String roomName = rs.getString(2);
				String description = rs.getString(3);
				int length = rs.getInt(4);
				Time t1 = rs.getTime(5);
				Time t2 = rs.getTime(6);
				Date date = rs.getDate(7);
				int no = rs.getInt(8);
				ArrayList<String> equipment = new ArrayList<>();
				String eq;
				int c = 9;
				while((eq=rs.getString(c))!=null||c<17){
					equipment.add(eq);
					c++;
				}
				Statement s = con.createStatement();
				ResultSet rs2 = s.executeQuery("select * from usertable where EmailAddress = '"+email+"';");
				rs2.next();
				String name = rs2.getString(1);
				String surname = rs2.getString(2);
				Boolean title = rs2.getBoolean(3);
				String dept = rs2.getString(4);
				String tel = rs2.getString(5);
				String psw = rs2.getString(7);
				UserDTO user = new UserDTO(name, surname, String.valueOf(title), dept, tel, email, psw);
				Statement ss = con.createStatement();
				ResultSet rs3 = ss.executeQuery("select * from conferenceroomtable where roomName ='"+roomName+"';");
				rs3.next();
				int capacity = rs3.getInt(2);
				String equip;
				int count =3;
				ArrayList<String> standardEquipment = new ArrayList<String>();
				while((equip=rs.getString(count))!=null||count<8){
					standardEquipment.add(equip);
					count++;
				}
				ConferenceRoomDTO conference = new ConferenceRoomDTO(roomName, capacity,standardEquipment);
				BookingDTO booking = new BookingDTO(user, conference, description, length, t1.getHours(), t1.getMinutes(), t2.getHours(), t2.getMinutes(), date,no, equipment);
				waitList.add(booking);
			}
			con.close();
			return waitList;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				con.close();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public Vector<String> requestEquipment(String d1, String d2)
	{
		Connection con = this.getConnection();
		try 
		{
			Statement stmt = con.createStatement();
			String querySQL = "select Equipment, count(*) from hiredequipmenttable where Date >= '" + d1 + "' and Date<='" + d2 + "' group by equipment;";
			ResultSet rs;
			rs = stmt.executeQuery(querySQL);
			Vector<String> equipment = new Vector<String>();
			while(rs.next())
			{
				equipment.add(rs.getString(1) + " - " + rs.getInt(2));
			}
			con.close();
			return equipment;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				con.close();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		return null;
	}
}
