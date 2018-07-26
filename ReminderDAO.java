import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReminderDAO {
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	ReminderDAO (){
		String dbUrl = "jdbc:mysql://127.0.0.1:3309/reminders?useSSL=false";
		String user = "Aditya";
		String pass = "aditya";
		System.out.println("creating database connection");
	try 
	{
		System.out.println("creating database connection");
		myConn = DriverManager.getConnection(dbUrl, user, pass);
		myStmt = myConn.createStatement();
		//System.out.println("created database connection");
	} 
	catch (Exception exc) 
	{
		exc.printStackTrace();
	} 		
	}
	
	void insertIntoDB(String desc, String y, String m, String date, String h, String min, String sec){
		int y1= Integer.parseInt(y);
		int m1 = Integer.parseInt(m);
		int d1 = Integer.parseInt(date);
		int h1 = Integer.parseInt(h);
		int min1 = Integer.parseInt(min);
		int sec1 = Integer.parseInt(sec);
		//String t1 = 

		try
		{
		 String querystring = "INSERT INTO reminder (Description, Year, Month, Date, Hours, Minutes, Seconds) values ('" + 
		desc + 
		"' ," +
		y1 + 
		" ,"+ 
		m1+
		" ,"+
		d1+
		"," +
		h1 +
		"," +
		min1 +
		"," +
		sec1 +
		")";
		 System.out.println(querystring);
		// myRs = myStmt.executeQuery(querystring);
		 
		 myStmt = myConn.createStatement();
		 myStmt.executeUpdate(querystring);
		 
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		}
	
	ResultSet readallfromDB() {
		try {
		myRs = myStmt.executeQuery("SELECT * FROM reminder");
		}
		catch (Exception e) {e.printStackTrace();}
		return myRs;
		
	}

	void deleteFromDB (String idString)
	{
		int id = Integer.parseInt(idString);
		
		
		try
		{
		 String querystring = "DELETE FROM reminder WHERE idReminder = " + id;
		 myStmt = myConn.createStatement();
		 myStmt.executeUpdate(querystring);
		 
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
	}

	void updateDB (String idstring,String desc, String y, String m, String date, String h, String min, String sec)
	{
		int id = Integer.parseInt(idstring);
		
		
		try
		{
		 String querystring = "UPDATE reminder SET Description = '"+ desc + 
		"', Year="+ y +
		", Month="+ m +
		", Date="+ date +
		", Hours="+ h +
		", Minutes="+ min +
		", Seconds="+ sec +
		" WHERE idReminder = " + id;
		 System.out.println(querystring);
		 myStmt = myConn.createStatement();
		 myStmt.executeUpdate(querystring);
		 
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}

