package javaCalendar;
import java.util.Calendar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date; // For time handling.
import java.util.HashMap;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



/** CHandler Class
 * Assigned to: Isaac (isaacwalth)
 * Superclass to JEventIO.
 * This class handles calendar events. It allows you to both edit and add events.
 * Required Variables:
 * HashMap<Date,String> jEventList: Stores all of the events. Info on HashMaps: https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
 * Functions:
 * done   boolean checkForEvents(Calendar): Checks if there are any events that occur on the given date.
 * Hashmap<Date,String> getEvents(Date): Return all events that occur on the given date.
 * 
 * donishh void editEvent(Calendar,String): Replaces an old event from the hashmap.
 * 
 * Javadocs created by dandreas on 4/4/17.
 * 
 * 
 */

public class CHandler extends JFrame implements ActionListener

{
	static Calendar currentDate = Calendar.getInstance();
	
	public int hour;
  
	public boolean checkForEvents(Calendar date)
    {
    	if(hash.isEmpty()==true)
    	{
    		return false; 
    	}
    	else
    	{
    		return true;
    	}   	
    }
    public HashMap<String,String> getEvents(Calendar date)
    {   	
    	
    	Set<String> keys = hash.keySet();
    		HashMap<String,String> hasher=new HashMap<String,String>();
    		String days = "";
        	String months="";
        String years="";
    int day=   CHandler.currentDate.get(Calendar.DATE);
    if(day<10)
    {
    	days=("0"+day);
    }
    else
    {
    	days=""+day;
    }
    int  month = CHandler.currentDate.get(Calendar.MONTH);
    if (month<10)
    {
    	months=("0"+month);
    }
    else
    {
    	months=""+month;
    }
    int year=CHandler.currentDate.get(Calendar.YEAR);        	
  years=""+year;
    String sethour;    	
  
        String sDate =(days+months+years);
        	
        	
    		
    	for(String key: keys)
    {
    	if (key.contains(sDate))
    	{
    		hasher.put(key, hash.get(key));
    	}
    	
    	
    }
     return  hasher ;
     
    	//  return new HashMap<>(); //TODO: dummy return, replace me
    }
    	//THIS this gives the integer for the year/month/date
    	//time.get(Calendar.YEAR,MONTH,DATE),
    
   static HashMap<String, String> hash = new HashMap<String, String>(); 
    JLabel label=new JLabel ("please select a time, and what event");
    	
    	//identifying hours
    	JTextField text=new JTextField(20);
    	String[] hours = { "1AM ", "2AM ", "3AM ", "4AM ", "5AM ","6AM ","7AM ","8AM ","9AM ","10AM ","11AM ","12AM ","1PM ", "2PM ", "3PM ", "4PM ", "5PM ","6PM ","7PM ","8PM ","9PM ","10PM ","11PM ","12PM " };
    	JComboBox list = new JComboBox(hours);
    	JButton button=new JButton("enter");
    	JButton close= new JButton("close");
    	
    	public CHandler(){
    		super();
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setLayout(new FlowLayout());
    	
    		
    		list.addActionListener(new ActionListener() { 
    			   public void actionPerformed(ActionEvent e) {
    			      
    				       ((JComboBox)e.getSource()).getSelectedItem();
    			   }
    			 });
    		//label2.setVisible(false);
    		add(label);	
    		add(list);
    		add(text);
    		
    		add(close);
    	add(button,BorderLayout.NORTH);
    	list.setSelectedIndex(0);
    	button.addActionListener((new AbstractAction() {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(list.getSelectedItem()=="1AM "  )
        		{
        			hour =1;
        		}
        		else if(list.getSelectedItem()=="2AM ")
        		{
        			hour=2;

        		}
        		else if(list.getSelectedItem()=="3AM ")
        		{
        			hour=3;

        		}
        		else if(list.getSelectedItem()=="4AM ")
        		{
        			hour=4;

        		}
        		else if(list.getSelectedItem()=="5AM ")
        		{
        			hour=5;

        		}
        		else if(list.getSelectedItem()=="6AM ")
        		{
        			hour=6;

        		}
        		else if(list.getSelectedItem()=="7AM ")
        		{
        			hour=7;

        		}
        		else if(list.getSelectedItem()=="8AM ")
        		{
        			hour=8;

        		}
        		else if(list.getSelectedItem()=="9AM ")
        		{
        			hour=9;
     
        		}
        		else if(list.getSelectedItem()=="10AM ")
        		{
        			hour=10;
        		}
        		else if(list.getSelectedItem()=="11AM ")
        		{
        			hour=11;
        	
        		}
        		else if(list.getSelectedItem()=="12AM ")
        		{
        			hour=12;
        		}
        		else if(list.getSelectedItem()=="1PM ")
        		{
        			hour=13;

        		}
        		else if(list.getSelectedItem()=="2PM ")
        		{
        			hour=14;

        		}
        		else if(list.getSelectedItem()=="3PM ")
        		{
        			hour=15;

        		}
        		else if(list.getSelectedItem()=="4PM ")
        		{
        			hour=16;

        		}
        		else if(list.getSelectedItem()=="5PM ")
        		{
        			hour=17;

        		}
        		else if(list.getSelectedItem()=="6PM ")
        		{
        			hour=18;

        		}
        		else if(list.getSelectedItem()=="7PM ")
        		{
        			hour=19;

        		}
        		else if(list.getSelectedItem()=="8PM ")
        		{
        			hour=20;

        		}
        		else if(list.getSelectedItem()=="9PM ")
        		{
        			hour=21;

        		}
        		else if(list.getSelectedItem()=="10PM ")
        		{
        			hour=22;

        		}
        		
        		else if(list.getSelectedItem()=="11PM ")
        		{
        			hour=23;

        		}
        		else if(list.getSelectedItem()=="12PM ")
        		{
        			hour=24;
        		}
        		
        		
        		
        		//gets the event to submit it
        		String event =text.getText();    		
        	//hash.put(hour + "", event);
        	
        		
        		label.setVisible(true);
        		  		
        		button.setText("edit");
        		list.setVisible(false);
        	close.setEnabled(true);
        	String days = "";
        	String months="";
        String years="";
    int day=   CHandler.currentDate.get(Calendar.DATE);
    if(day<10)
    {
    	days=("0"+day);
    }
    else
    {
    	days=""+day;
    }
    int  month = CHandler.currentDate.get(Calendar.MONTH);
    if (month<10)
    {
    	months=("0"+month);
    }
    else
    {
    	months=""+month;
    }
    int year=CHandler.currentDate.get(Calendar.YEAR);        	
  years=""+year;
    String sethour;    	
    if(hour<10)
    {
    	sethour=("0"+hour);
    	
    }
    else
    {
    	sethour=""+hour;
    }
        String key=(sethour+days+months+years);
        hash.put(key, event);
        		
            }
        }));
    	
    	text.addActionListener(this);
    	close.addActionListener(this);
    	
    	}
    	 
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
    		//converting the stringed combobox into an integer 
    		this.dispose();
    		
    		    	}
    	
    	//launch from here
    	
    public void editEvent(Calendar time)
    {
    	currentDate =time;
    	CHandler aFrame= new CHandler();
    	
    	final int width=300;
    		final int height =200;
    		aFrame.setSize(width,height);
    		aFrame.setVisible(true);
    		aFrame.setVisible(true);
    		time.get(hour);
    		time.isSet(hour);
    		
    		
    			
    	//	time.set(Calendar.HOUR_OF_DAY,hour);
    			//time.set(Calendar.HOUR, hash.get(hash.get(hours[hour])));
    		
    	
    		
    		//	time.set(Calendar.HOUR, hash.get(hours[hour]) );
    		
    
    }
   
}
