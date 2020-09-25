package deenicholas.delnovo.server;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import vzap.delnovo.dao.CRRSDAO;
import vzap.delnovo.dto.BookingDTO;

public class Timing {
  Toolkit toolkit;

  Timer timer;
  public CRRSDAO dao;
  private BookingDTO bookingDTo;
  public Timing(int seconds, BookingDTO bookingDTO) //PUT HOURS
  {
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    this.bookingDTo = bookingDTO;
    timer.schedule(new RemindTask(bookingDTo), seconds * 1000);
    dao = new CRRSDAO();
  }

  class RemindTask extends TimerTask
  {
	  BookingDTO booking;
	  public RemindTask(BookingDTO booking)
	  {
		this.booking = booking;
	  }
    public void run() 
    {
      System.out.println("Time's up!");
      toolkit.beep();
      timer.cancel();
      dao.timeUp(booking);
    }
  }
}



