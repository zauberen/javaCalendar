package javaCalendar;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/** CalendarFrame Class
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

public class CalendarFrame extends JFrame
{
    /**
	 * Autogen'd version UID
	 */
	private static final long serialVersionUID = -2609125190252367020L;
	private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    /* TODO: Remove after testing
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
    */
    // Panel setup //
    // CardPanel setup
    private JPanel cardPanel = new JPanel(new CardLayout()); // Holds the calendar and event panels
    private CardLayout cardPanelLayout;
    // CalendarPanel setup
    private GridLayout calendarLayout = new GridLayout(8,7); // Layout for the calendar panel
    //private JPanel monthPanel = new JPanel(calendarLayout);
    private MonthPanel monthPanel = new MonthPanel(calendarLayout, this);
    // dayPanel Setup
    private GridLayout eventLayout = new GridLayout(0,2); // Layout for the event panel
    private DayPanel dayPanel = new DayPanel(eventLayout, this);

    // Create instance of the event handler
    private EventIO events = new EventIO();

    // Calendar objects used to track current date and active date TODO: Implement tracking of current date in calendar GUI (MP)
    private Calendar activeDate = Calendar.getInstance(); // this should be changed as the month / day edited does.
    private Calendar currentDate = Calendar.getInstance(); // this should *not* be changed as the month / day edited does.

    public CalendarFrame()
    {
        // Set up the basic frame
        super("Java Calendar");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Setup cardPanel
        cardPanel.add(monthPanel, "Calendar");
        cardPanel.add(dayPanel, "Event");
        this.add(cardPanel);
        // end cardPanel

        gotoMonthPanel();
    }

    public void gotoDayPanel(Calendar date)
    {
        activeDate = date;
        // Show the event panel
        dayPanel.showDate(activeDate);
        cardPanelLayout = (CardLayout)(cardPanel.getLayout());
        cardPanelLayout.show(cardPanel,"Event");
    }

    public void gotoMonthPanel()
    {
    	// Show the calendar panel
        cardPanelLayout = (CardLayout)(cardPanel.getLayout());
        cardPanelLayout.show(cardPanel,"Calendar");
    }
}
