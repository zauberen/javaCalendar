package javaCalendar;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/** CFrame Class
 * Assigned to: Dillon (dandreas)
 * Extends JFrame
 * Creates the JFrame and handles all related actions primary to the GUI.
 *
 * void fillCalendar(): Fills the calendar with dates on start/movement back to the calendar panel
 * void fillCalendar(Calendar date): As above, with a specified month + yr
 * void backCalendar(): Moves the calendar month one month back from the current active calendar month
 * void forwardCalendar(): Moves the calendar month one month forward from the current active calendar month
 * int getDaysInMonth(Calendar): As the name implies.
 *
 * Notes-to-Self: ln98 lambda needs finishing, need to add lambdas to JMenu objects, need to add the editPanel's guts, need to add swap functions for switching between calendar and edit panels.
 *
 *
 * Javadocs created by dandreas on 4/4/17.
 */

public class CFrame  extends JFrame
{
    /**
	 * Autogen'd version UID
	 */
	private static final long serialVersionUID = -2609125190252367020L;
	private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    
    // Panel item setup //
    // for the calendar panel
    private JButton load = new JButton("Load");
    private JButton save = new JButton("Save");
    private JButton next = new JButton("Next");
    private JButton prev = new JButton("Previous");
    private JLabel monthLabel = new JLabel();
    private JLabel yearLabel = new JLabel();
    private JButton[] dayButtons = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
    // for the event panel
    private JButton newEvent = new JButton("New/Edit");
    private JButton back = new JButton("Back");
    
    // Panel setup //
    // CardPanel setup
    private JPanel cardPanel = new JPanel(new CardLayout()); // Holds the calendar and event panels
    private CardLayout cardPanelLayout;
    // CalendarPanel setup
    private GridLayout calendarLayout = new GridLayout(8,7); // Layout for the calendar panel
    private JPanel calendarPanel = new JPanel(calendarLayout);
    // eventPanel Setup
    private GridLayout eventLayout = new GridLayout(0,2); // Layout for the event panel
    private JPanel eventPanel = new JPanel(eventLayout);

    // Create instance of the event handler
    private JEventIO events = new JEventIO();

    // Calendar objects used to track current date and active date TODO: Implement tracking of current date in calendar GUI (MP)
    private Calendar activeDate = Calendar.getInstance(); // this should be changed as the month / day edited does.
    private Calendar currentDate = Calendar.getInstance(); // this should *not* be changed as the month / day edited does.

    public CFrame()
    {
        // Set up the basic frame
        super("Java Calendar");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set up the button actionlisteners
        load.addActionListener((new AbstractAction() {
            /**
			 * Autogen'd version UID
			 */
			private static final long serialVersionUID = -2099243940908081717L;

			@Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    events.open();
                }
                catch (Exception e)
                {
                	JOptionPane.showMessageDialog(null,"Failed to load events");
                }
            }
        }));

        save.addActionListener((new AbstractAction() {
            /**
			 * Autogen'd version UID
			 */
			private static final long serialVersionUID = 3861969805329674521L;

			@Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    events.save();
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Failed to save events");
                }
            }
        }));

        prev.addActionListener((new AbstractAction() {
            /**
			 * Autogen'd version UID
			 */
			private static final long serialVersionUID = 8037398815281296710L;

			@Override
            public void actionPerformed(ActionEvent actionEvent) 
			{
                backCalendar();
            }
        }));

        next.addActionListener((new AbstractAction() {
            /**
			 * Autogen'd version UID
			 */
			private static final long serialVersionUID = 5112880502963842794L;

			@Override
            public void actionPerformed(ActionEvent actionEvent) {
                forwardCalendar();
            }
        }));

        newEvent.addActionListener((new AbstractAction() {
            /**
			 * Autogen'd version UID
			 */
			private static final long serialVersionUID = 735060752220097605L;

			@Override
            public void actionPerformed(ActionEvent e) {
                events.editEvent(activeDate);
                gotoCalendarPanel();
            }
        }));

        back.addActionListener((new AbstractAction() {
            /**
			 * Autogen'd version UID
			 */
			private static final long serialVersionUID = -6483552148578258852L;

			@Override
            public void actionPerformed(ActionEvent actionEvent) {
                gotoCalendarPanel();
            }
        }));

        // Set up the calendar panel
        JLabel sun = new JLabel("Sunday");
        JLabel mon = new JLabel("Monday");
        JLabel tue = new JLabel("Tuesday");
        JLabel wed = new JLabel("Wednesday");
        JLabel thu = new JLabel("Thursday");
        JLabel fri = new JLabel("Friday");
        JLabel sat = new JLabel("Saturday");
        calendarPanel.add(sun);
        calendarPanel.add(mon);
        calendarPanel.add(tue);
        calendarPanel.add(wed);
        calendarPanel.add(thu);
        calendarPanel.add(fri);
        calendarPanel.add(sat);

        for(int i = 0; i < dayButtons.length; i++)
        {
        	// Sets up dates.
            int c;
            if (i > 30)
                c = i - 30;
            else
                c = i + 1;

            dayButtons[i].setText(c + "");
            dayButtons[i].addActionListener(( new AbstractAction()
            {
                /**
				 * Autogen'd version UID
				 */
				private static final long serialVersionUID = 5149440874468494393L;

				@Override
                public void actionPerformed( ActionEvent e )
                {
                    for (int i = 0; i < dayButtons.length; i++)
                    {
                        if(dayButtons[i] == e.getSource())
                        {
                            try
                            {
                                int day = Integer.parseInt(dayButtons[i].getText());
                                activeDate.set(Calendar.DATE,day);
                                gotoEventPanel();
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null,"Calendar is not finished loading...");
                            }
                        }
                    }
                }
            }));
            calendarPanel.add(dayButtons[i]);
        }
        
        // Set up month/year label
        monthLabel.setText("Month: " + activeDate.get(Calendar.MONTH));
        yearLabel.setText("Year: " + activeDate.get(Calendar.YEAR));

        calendarPanel.add(load);
        calendarPanel.add(save);
        calendarPanel.add(prev);
        calendarPanel.add(next);
        calendarPanel.add(monthLabel);
        calendarPanel.add(yearLabel);

        fillCalendar();
        // end calendar panel

        // Setup cardPanel
        cardPanel.add(calendarPanel, "Calendar");
        cardPanel.add(eventPanel, "Event");
        this.add(cardPanel);
        // end cardPanel

        gotoCalendarPanel();
    }

    private void gotoEventPanel()
    {
    	// Variables
    	HashMap<String,String> hashEvents = events.getEvents(activeDate);
    	//ArrayList<String> eventlist = events.getEvents(activeDate); //TODO implement this.
        Set setEvents = hashEvents.entrySet();
        Object[] arrayEvents = setEvents.toArray();
        JLabel dateHeader = new JLabel("Date");
        JLabel descrHeader = new JLabel("Description");
    	
    	// Refresh event panel
        eventPanel.removeAll();
        eventPanel.add(dateHeader);
        eventPanel.add(descrHeader);
        // Fill in events on the table.
        for (int i = 0; i < hashEvents.size(); i++)
        {
            JLabel description = new JLabel(arrayEvents[i].toString());
            eventPanel.add(description);
        }
        eventPanel.add(back);
        eventPanel.add(newEvent);
        
        // Show the event panel
        cardPanelLayout = (CardLayout)(cardPanel.getLayout());
        cardPanelLayout.show(cardPanel,"Event");
    }

    private void gotoCalendarPanel()
    {
    	// Show the calendar panel
        cardPanelLayout = (CardLayout)(cardPanel.getLayout());
        cardPanelLayout.show(cardPanel,"Calendar");
    }

    private int getDaysInMonth(Calendar date)
    {
    	// Variables
        int daysInMonth;
        
        // Determine the number of days in the given month
        switch(date.get(Calendar.MONTH)+1)
        {
            case 1:
                daysInMonth = 31;
                break;
            case 2:
                // Determines if it is a leap year
                if (date.get(Calendar.YEAR) % 4 == 0)
                    daysInMonth = 29;
                else
                    daysInMonth = 28;
                break;
            case 3:
                daysInMonth = 31;
                break;
            case 4:
                daysInMonth = 30;
                break;
            case 5:
                daysInMonth = 31;
                break;
            case 6:
                daysInMonth = 30;
                break;
            case 7:
                daysInMonth = 31;
                break;
            case 8:
                daysInMonth = 31;
                break;
            case 9:
                daysInMonth = 30;
                break;
            case 10:
                daysInMonth = 31;
                break;
            case 11:
                daysInMonth = 30;
                break;
            case 12:
                daysInMonth = 31;
                break;
            default:
                daysInMonth = 0;
                JOptionPane.showMessageDialog(null,"Invalid month: " + date.get(Calendar.MONTH));
        }

        return daysInMonth;
    }


    private void fillCalendar()
    {
    	// Variables
        Calendar date = Calendar.getInstance();
        activeDate = date;
        int daysInMonth = getDaysInMonth(date) - 1;
        int day = 1;
        Calendar tempDate = date; // Marked as redundant TODO: fix this (Low Priority)

        for(int i = 0; i < dayButtons.length; i++)
        {
            String output = "";
            int c;

            if (i < 7)
            {
                if (i == 0)
                {
                    tempDate.set(Calendar.DATE, 1);
                    day = tempDate.get(Calendar.DAY_OF_WEEK);
                    tempDate.set(Calendar.MONTH, tempDate.get(Calendar.MONTH) - 1);
                    dayButtons[i].setEnabled(true);
                }
                if (day > i + 1)
                {
                    c = this.getDaysInMonth(tempDate) - (day - i - 2);
                    dayButtons[i].setEnabled(false);
                }
                else
                {
                    c = i - day + 2;
                    dayButtons[i].setEnabled(true);
                }
            }
            else
            {
                if (i < daysInMonth + day)
                {
                    c = i - day + 2;
                /* TODO:(find why this doesn't work) This if would add a * at the end of the date when there is an event. Currently breaks
                 * the button actionlistener, so unfortunately we will have to do without for now
                if (events.checkForEvents(new Calendar(date.get(Calendar.YEAR),date.get(Calendar.MONTH),c-1)))
                    output = c + "*";
                */
                    dayButtons[i].setEnabled(true);
                }
                else
                {
                    c = i - day + 1 - daysInMonth;
                    dayButtons[i].setEnabled(false);
                }
            }

            output += c;

            dayButtons[i].setText(output);
        }
        // Set up month/year label
        monthLabel.setText("Month: " + (activeDate.get(Calendar.MONTH) + 2));
        yearLabel.setText("Year: " + activeDate.get(Calendar.YEAR));
    }


    private void backCalendar()
    {
        Calendar date = activeDate;
        date.set(Calendar.MONTH, activeDate.get(Calendar.MONTH));

        int daysInMonth = getDaysInMonth(date) - 1;
        int day = 1;
        Calendar tempDate = date; // Marked as redundant TODO: Fix this (Low Priority)

        for(int i = 0; i < dayButtons.length; i++)
        {
            String output = "";
            int c;

            if (i < 7)
            {
                if (i == 0)
                {
                    tempDate.set(Calendar.DATE, 1);
                    day = tempDate.get(Calendar.DAY_OF_WEEK);
                    tempDate.set(Calendar.MONTH, tempDate.get(Calendar.MONTH) - 1);
                    dayButtons[i].setEnabled(true);
                }
                if (day > i + 1)
                {
                    c = this.getDaysInMonth(tempDate) - (day - i - 2);
                    dayButtons[i].setEnabled(false);
                }
                else
                {
                    c = i - day + 2;
                    dayButtons[i].setEnabled(true);
                }
            }
            else
            {
                if (i < daysInMonth + day)
                {
                    c = i - day + 2;
                /* This if would add a * at the end of the date when there is an event. Currently breaks
                 * the button actionlistener, so unfortunately we will have to do without for now
                if (events.checkForEvents(new Date(date.get(Calendar.YEAR),date.get(Calendar.MONTH),c-1)))
                    output = c + "*";
                */
                    dayButtons[i].setEnabled(true);
                }
                else
                {
                    c = i - day + 1 - daysInMonth;
                    dayButtons[i].setEnabled(false);
                }
            }

            output += c;

            dayButtons[i].setText(output);
        }

        // Set up month/year label
        monthLabel.setText("Month: " + (activeDate.get(Calendar.MONTH) + 2));
        yearLabel.setText("Year: " + activeDate.get(Calendar.YEAR));
    }

    private void forwardCalendar()
    {
        Calendar date = activeDate;
        date.set(Calendar.MONTH, activeDate.get(Calendar.MONTH) + 2);

        int daysInMonth = getDaysInMonth(date) - 1;
        int day = 1;
        Calendar tempDate = date; // Marked as redundant TODO: Fix this (Low Priority)

        for (int i = 0; i < dayButtons.length; i++) {
            String output = "";
            int c;

            if (i < 7) {
                if (i == 0) {
                    tempDate.set(Calendar.DATE, 1);
                    day = tempDate.get(Calendar.DAY_OF_WEEK);
                    tempDate.set(Calendar.MONTH, tempDate.get(Calendar.MONTH) - 1);
                    dayButtons[i].setEnabled(true);
                }
                if (day > i + 1) {
                    c = this.getDaysInMonth(tempDate) - (day - i - 2);
                    dayButtons[i].setEnabled(false);
                } else {
                    c = i - day + 2;
                    dayButtons[i].setEnabled(true);
                }
            } else {
                if (i < daysInMonth + day) {
                    c = i - day + 2;
                /* This if would add a * at the end of the date when there is an event. Currently breaks
                 * the button actionlistener, so unfortunately we will have to do without for now
                if (events.checkForEvents(new Date(date.get(Calendar.YEAR),date.get(Calendar.MONTH),c-1)))
                    output = c + "*";
                */
                    dayButtons[i].setEnabled(true);
                } else {
                    c = i - day + 1 - daysInMonth;
                    dayButtons[i].setEnabled(false);
                }
            }

            output += c;

            dayButtons[i].setText(output);
        }

        // Set up month/year label
        monthLabel.setText("Month: " + (activeDate.get(Calendar.MONTH) + 2));
        yearLabel.setText("Year: " + activeDate.get(Calendar.YEAR));
    }
}
