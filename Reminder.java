import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import javax.swing.JOptionPane;

public class Reminder {
    Timer timer;
    Date d;
    String message;
    public Reminder(String desc, String y, String m, String date, String h, String min, String sec) {
     	System.out.println("ïnside reminder");
     	timer = new Timer();
		int y1= Integer.parseInt(y);
		int m1 = Integer.parseInt(m);
		int d1 = Integer.parseInt(date);
		int h1 = Integer.parseInt(h);
		int min1 = Integer.parseInt(min);
		int sec1 = Integer.parseInt(sec);
		message = desc;
        d = new Date((y1-1900), m1-1,d1, h1,min1,sec1);
        System.out.println(d.toString());
        timer.schedule(new RemindTask(), d);
    }

    class RemindTask extends TimerTask {
        public void run() {
        	System.out.println(message);
        	JOptionPane.showMessageDialog(null, message, "REMINDER", JOptionPane.INFORMATION_MESSAGE);
	    timer.cancel(); //Terminate the timer thread
        }
    }
}

