import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Label;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.sql.ResultSet;

public class Organizer{
	private static JTable table;
	private static JTable table_1;
	
public static void createAndShowGUI() { 
	
	
	JFrame frame = new JFrame("Hello World Swing");
	frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	
	JTextPane Description = new JTextPane();
	Description.setBounds(133, 43, 225, 33);
	frame.getContentPane().add(Description);
	
	
	//Combo box to provide drop down menu
	
	
	JComboBox YearX = new JComboBox();
	YearX.setModel(new DefaultComboBoxModel(new String[] {"2018", "2019", "2020", "2021", "2022"}));
	YearX.setBounds(133, 87, 77, 29);
	frame.getContentPane().add(YearX);
//	frame.pack();
	frame.setSize(718, 352);
	
	JComboBox MonthX = new JComboBox();
	MonthX.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
	MonthX.setBounds(133, 124, 77, 28);
	frame.getContentPane().add(MonthX);
	
	JComboBox DateX = new JComboBox();
	DateX.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
	DateX.setBounds(133, 162, 77, 26);
	frame.getContentPane().add(DateX);
	
	//TextPane for user to input data
	
	JTextPane Hours = new JTextPane();
	Hours.setBounds(293, 87, 47, 32);
	frame.getContentPane().add(Hours);
	
	JTextPane Min = new JTextPane();
	Min.setBounds(293, 124, 47, 31);
	frame.getContentPane().add(Min);
	
	JTextPane Sec = new JTextPane();
	Sec.setBounds(293, 162, 47, 29);
	frame.getContentPane().add(Sec);
	
	ReminderDAO reminderDAO = new ReminderDAO();
	System.out.println("DAO created");


	JButton Save = new JButton("Save");
	Save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Button has been clicked");
			String y,d,m,date,hrs,min,sec;
			y = YearX.getSelectedItem().toString();
			d = Description.getText();
			m = MonthX.getSelectedItem().toString();
			date = DateX.getSelectedItem().toString();
			hrs = Hours.getText();
			min = Min.getText();
			sec = Sec.getText();
			reminderDAO.insertIntoDB(d, y, m, date, hrs, min, sec);
			populateTable();
			Reminder reminder = new Reminder(d, y, m, date, hrs, min, sec);	
			}});
	Save.setBounds(133, 235, 109, 43);
	frame.getContentPane().add(Save);
	
	
	JLabel lblDescription = new JLabel("Description");
	lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
	lblDescription.setBounds(10, 43, 113, 33);
	frame.getContentPane().add(lblDescription);
	
	JLabel lblNewLabel = new JLabel("Year");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(37, 83, 61, 26);
	frame.getContentPane().add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("Month");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setBounds(37, 120, 61, 33);
	frame.getContentPane().add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("Date");
	lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_2.setBounds(37, 157, 65, 26);
	frame.getContentPane().add(lblNewLabel_2);
	
	JLabel lblNewLabel_3 = new JLabel("Hours");
	lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_3.setBounds(238, 87, 36, 26);
	frame.getContentPane().add(lblNewLabel_3);
	
	Label label = new Label("Min");
	label.setFont(new Font("Tahoma", Font.PLAIN, 14));
	label.setAlignment(Label.CENTER);
	label.setBounds(238, 130, 36, 22);
	frame.getContentPane().add(label);
	
	
	
	JLabel lblNewLabel_4 = new JLabel("Sec");
	lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_4.setBounds(238, 164, 36, 23);
	frame.getContentPane().add(lblNewLabel_4);
	
	table = new JTable(5,8);
	table.setBorder(new LineBorder(new Color(0, 0, 0)));
	table.setBounds(368, 43, 312, 86);
	
	frame.getContentPane().add(table);
			
	JButton btnUpdate = new JButton("Update");
	btnUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			reminderDAO.updateDB(
					table_1.getValueAt(0, 0).toString(),
					table_1.getValueAt(0, 1).toString(),
					table_1.getValueAt(0, 2).toString(),
					table_1.getValueAt(0, 3).toString(),
					table_1.getValueAt(0, 4).toString(),
					table_1.getValueAt(0, 5).toString(),
					table_1.getValueAt(0, 6).toString(),
					table_1.getValueAt(0, 7).toString()
					);
			populateTable();			
			
		}
	});
	btnUpdate.setBounds(398, 245, 89, 23);
	frame.getContentPane().add(btnUpdate);
	
	JButton btnDelete = new JButton("Delete");
	btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			reminderDAO.deleteFromDB(table_1.getValueAt(0, 0).toString());
			populateTable();
		}
	});
	btnDelete.setBounds(562, 245, 89, 23);
	frame.getContentPane().add(btnDelete);
	
	JTextPane txtpnCurrentReminderList = new JTextPane();
	txtpnCurrentReminderList.setText("           Current Reminder List");
	txtpnCurrentReminderList.setBounds(428, 12, 181, 20);
	frame.getContentPane().add(txtpnCurrentReminderList);
	
	table_1 = new JTable(1,8);
	table_1.setBounds(368, 202, 312, 20);
	frame.getContentPane().add(table_1);
	
	JButton btnNewButton = new JButton("Select");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		System.out.println("Selected ");
		System.out.println(table.getSelectedRow());
		int rowid = table.getSelectedRow();
		
		table_1.setValueAt(table.getValueAt(rowid, 0), 0, 0);
		table_1.setValueAt(table.getValueAt(rowid, 1), 0, 1);
		table_1.setValueAt(table.getValueAt(rowid, 2), 0, 2);
		table_1.setValueAt(table.getValueAt(rowid, 3), 0, 3);
		table_1.setValueAt(table.getValueAt(rowid, 4), 0, 4);
		table_1.setValueAt(table.getValueAt(rowid, 5), 0, 5);
		table_1.setValueAt(table.getValueAt(rowid, 6), 0, 6);
		table_1.setValueAt(table.getValueAt(rowid, 7), 0, 7);
				
		}
	});
	populateTable();
	

	btnNewButton.setBounds(489, 164, 89, 23);
	frame.getContentPane().add(btnNewButton);
	
	frame.setVisible(true);
}

public static void populateTable() {
	ReminderDAO reminderDAO = new ReminderDAO();
	ResultSet rs = reminderDAO.readallfromDB();
	try {
		int i=0;
		while(rs.next())  {
				System.out.println((rs.getInt(1) +"  " + rs.getString(2)));
				table.setValueAt(rs.getInt(1), i,0);
				table.setValueAt(rs.getString(2), i,1);
				table.setValueAt(rs.getString(3), i,2);
				table.setValueAt(rs.getString(4), i,3);
				table.setValueAt(rs.getString(5), i,4);
				table.setValueAt(rs.getString(6), i,5);
				table.setValueAt(rs.getString(7), i,6);
				table.setValueAt(rs.getString(8), i,7);
			i = i+1;
		}
	}
	catch (Exception e) {e.printStackTrace();}
	}
public static void main(String[]args) {
	   createAndShowGUI();}
}