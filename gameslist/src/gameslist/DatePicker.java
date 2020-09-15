package gameslist;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DatePicker {
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	JLabel l = new 	JLabel("", JLabel.CENTER);
	JLabel monthYear;
	String day = "";
	JDialog d;
	JButton[] button = new JButton[49];
	
	public DatePicker(JButton parent, String pDate) {
		//Sets the date to the passed in string
		setDate(pDate);
		
		//If there is a parent 
		if  (parent != null) {
			//Create a new dialog
			d = new JDialog();
			//Set to modal
			d.setModal(true);
			//Create an array for the headers
			String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
			//Create a panel with a grid layout
			JPanel p1 = new JPanel(new GridLayout(7, 7));
			//Set the size
			p1.setPreferredSize(new Dimension(430, 120));
			//Loops for all of the buttons
			for (int x = 0; x < button.length; x++) {
				final int selection = x;
				//Creates a new button and set attributes
				button[x] = new JButton();
				button[x].setFocusPainted(false);
				button[x].setBackground(Color.white);
				//7 days in a week
				if (x > 6) {
					//Add a listener to each of these buttons
					button[x].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							day = button[selection].getActionCommand();
							d.dispose();
						}
					});
				//These are for the headers
				} else if (x < 7) {
					button[x].setText(header[x]);
					button[x].setForeground(Color.red);
				}
				//Add the buttons to the panel
				p1.add(button[x]);
			}
			//Create another panel for the next and previous buttons and the label
			JPanel p2 = new JPanel(new GridLayout(1, 3));
	        JButton previous = new JButton("<");
	        JButton next = new JButton(">");
	        
	        //Add listener to the next button
	        next.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	//If the month is already on 12, reset to 1 and increase year
	            	if (month >= 12) {
	            		month = 1;
	            		year++;
	            	//Or, just increase month
	            	} else
	            		month++;
	                displayDate();
	            }
	        });
	        
	        //Add listener to the previous button
	        previous.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	//If the month is already on 1, reset to 12 and increase year
	            	if (month <= 1) {
	            		year--;
	            		month = 12;
	            	//Or, just decrease the month
	            	} else
	            		month--;
	                displayDate();
	            }
	        });
	        
	        //Creates the label for the month and year
	        monthYear = new JLabel(getMonth(month) + " " + year);
	        //Set allignment to center
	        monthYear.setHorizontalAlignment(JLabel.CENTER);
	        //Add the buttons and label to the second panel
	        p2.add(previous);
	        p2.add(monthYear);
	        p2.add(next);
	        //Add the panels to the dialog
	        d.add(p1, BorderLayout.CENTER);
	        d.add(p2, BorderLayout.SOUTH);
	        //Package the dialog
	        d.pack();
	        //Set it to relative to parent
	        d.setLocationRelativeTo(parent);
	        //Display the date
	        displayDate();
	        //Set the dialog to visible
	        d.setVisible(true);
		}
	}
	
	/**
	 * Gets the month
	 * @param month
	 * @return
	 */
	String getMonth(int month) {
		switch(month) {
		case 1:
			return "January";
		case 2:
			return "Febuary";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "";
		}
	}
	
	/**
	 * Gets the day
	 * @return
	 */
	String getDay() {
		switch(day) {
		case "1":
			return "01";
		case "2":
			return "02";
		case "3":
			return "03";
		case "4":
			return "04";
		case "5":
			return "05";
		case "6":
			return "06";
		case "7":
			return "07";
		default:
			return "";
		}
	}
	
	/**
	 * Display the date
	 */
	public void displayDate() {
		//Loop for all buttons except for the headers
        for (int x = 7; x < button.length; x++)
        	//Blank the buttons text
            button[x].setText("");
        //Create a date format
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        //Get a calendar
        java.util.Calendar cal = java.util.Calendar.getInstance();
        //Set the date on the calendar
        cal.set(year, month, 1);
        //Get day from calendar
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        //Get number of days in month from calendar
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        //Loop through the days in the week
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
        	//Set the text of the buttons
            button[x].setText("" + day);
        //Set the text of the label
        l.setText(sdf.format(cal.getTime()));
        //Set dialog title
        d.setTitle("Date Picker");
        //Set text of month and year label
        monthYear.setText(getMonth(month) + " " + year);;
    }
	
	/**
	 * Set the picked date
	 * @return
	 */
	public String setPickedDate() {
		//Checks if day is blank
        if (day.equals(""))
            return day;
        //Otherwise, create a date format and get a calendar
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "dd-MM-yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        //Set the calendar to the selected day
        cal.set(year, month, Integer.parseInt(day));
        //Return the formatted date
        return sdf.format(cal.getTime());
    }
	
	/**
	 * Set the date
	 * @param date
	 */
	void setDate(String date) {
		//Seperate the passed in date string into year, month and day
		String y;
		String m;
		String d;
		if (date.length() != 0 && date.length() == 8) {
			y = date.substring(0, 4);
			m = date.substring(4, 6);
			d = date.substring(6, 8);
			
			year = Integer.parseInt(y);
			month = Integer.parseInt(m);
			day = d;
		} else if (date.length() != 0) {
			y = date.substring(0, 4);
			m = date.substring(5, 7);
			d = date.substring(8, 10);
			
			year = Integer.parseInt(y);
			month = Integer.parseInt(m);
			day = d;
		} else {
			//Standard to 1995
			year = 1995;
			month = 1;
			day = "1";
		}
	}
	
	/**
	 * Format the date to "yyyymmddT000000"
	 * @return
	 */
	String formatDate() {
		String mont = "";
		String d = "";
		if (month < 10)
			mont = "0" + Integer.toString(month);
		else
			mont = Integer.toString(month);
		if (Integer.parseInt(day) < 10)
			d = "0" + day;
		else
			d = day;
		return Integer.toString(year) + mont + d + "T000000";
	}
}
