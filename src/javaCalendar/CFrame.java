package javaCalendar;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/** CFrame Class
 * Assigned to: Dillon (dandreas)
 * Extends JFrame
 * Creates the JFrame and handles all related actions relating to the GUI.
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
    final int FRAME_WIDTH = 800;
    final int FRAME_HEIGHT = 600;

    // for the calendar panel
    JButton load = new JButton("Load");
    JButton save = new JButton("Save");
    JButton next = new JButton("Next");
    JButton prev = new JButton("Previous");

    // for the event panel
    JButton newEvent = new JButton("New");
    JButton back = new JButton("Back");

    // CardPanel setup
    JPanel cardPanel = new JPanel(new CardLayout()); // Holds the calendar and event panels
    CardLayout cardPanelLayout;

    // CalendarPanel setup
    GridLayout calendarLayout = new GridLayout(8,7); // Layout for the calendar panel
    JPanel calendarPanel = new JPanel(calendarLayout);

    // eventPanel Setup
    GridLayout eventLayout = new GridLayout(0,2);
    JPanel eventPanel = new JPanel(eventLayout);

    // Create instance of the event handler
    JEventIO events = new JEventIO();

    Calendar currentMonth = Calendar.getInstance(); // this should be changed as the month / day edited does.

    JButton[] dayButtons = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};


    public CFrame()
    {
        // Set up the basic frame
        super("Java Calendar");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // end frame

        // Set up the old menubar options

        load.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    events.open();
                }
                catch (Exception e)
                {

                }
            }
        }));

        save.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    events.save();
                }
                catch (Exception e)
                {

                }
            }
        }));

        prev.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                backCalendar();
            }
        }));

        next.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                forwardCalendar();
            }
        }));

        newEvent.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.editEvent(currentMonth);
            }
        }));

        back.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gotoCalendarPanel();
            }
        }));
        // end

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
            int c;
            if (i > 30)
                c = i - 30;
            else
                c = i + 1;

            dayButtons[i].setText(c + "");
            dayButtons[i].addActionListener(( new AbstractAction()
            {
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
                                currentMonth.set(Calendar.DATE,day);
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

        calendarPanel.add(load);
        calendarPanel.add(save);
        calendarPanel.add(prev);
        calendarPanel.add(next);

        fillCalendar();
        // end calendar panel

        // Setup cardPanel
        cardPanel.add(calendarPanel, "Calendar");
        cardPanel.add(eventPanel, "Event");
        this.add(cardPanel);
        // end cardPanel

        gotoCalendarPanel();
    }

    public void gotoEventPanel()
    {
        eventPanel.removeAll();

        HashMap<String,String> hashEvents = events.getEvents(currentMonth);
        Set setEvents = hashEvents.entrySet();
        Object[] arrayEvents = setEvents.toArray();

        for (int i = 0; i < hashEvents.size(); i++)
        {
            JLabel description = new JLabel(arrayEvents[i].toString());
            JButton eventButton = new JButton(i + "");
            eventButton.addActionListener((new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            }));
            eventPanel.add(description);
            eventPanel.add(eventButton);
        }
        eventPanel.add(back);
        eventPanel.add(newEvent);

        cardPanelLayout = (CardLayout)(cardPanel.getLayout());
        cardPanelLayout.show(cardPanel,"Event");
    }

    public void gotoCalendarPanel()
    {
        cardPanelLayout = (CardLayout)(cardPanel.getLayout());
        cardPanelLayout.show(cardPanel,"Calendar");
    }

    public int getDaysInMonth(Calendar date)
    {
        int daysInMonth = 0;

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


    public void fillCalendar()
    {
        Calendar date = Calendar.getInstance();
        currentMonth = date;
        int daysInMonth = getDaysInMonth(date);
        int day = 1;
        Calendar tempDate = date;

        for(int i = 0; i < dayButtons.length; i++)
        {
            String output = "";
            int c = 9999;

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
    }

    public void fillCalendar(Calendar date)
    {
        currentMonth = date;
        int daysInMonth = getDaysInMonth(date);
        int day = 1;
        Calendar tempDate = date;

        for(int i = 0; i < dayButtons.length; i++)
        {
            String output = "";
            int c = 9999;

            if (i < 7)
            {
                if (i == 0)
                {
                    tempDate.set(Calendar.DATE, 1);
                    day = tempDate.get(Calendar.DAY_OF_WEEK);
                    tempDate.set(Calendar.MONTH, tempDate.get(Calendar.MONTH) - 1);
                }
                if (day > i + 1)
                {
                    c = this.getDaysInMonth(tempDate) - (day - i - 2);
                    dayButtons[i].setEnabled(false);
                }
                else
                {
                    c = i - day + 2;
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

    }

    public void backCalendar()
    {
        Calendar date = currentMonth;
        date.set(Calendar.MONTH, date.get(Calendar.MONTH) - 1);

        int daysInMonth = getDaysInMonth(date);
        int day = 1;
        Calendar tempDate = date;

        for(int i = 0; i < dayButtons.length; i++)
        {
            String output = "";
            int c = 9999;

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
    }

    public void forwardCalendar()
    {
        Calendar date = currentMonth;
        date.set(Calendar.MONTH, date.get(Calendar.MONTH) + 1);

        int daysInMonth = getDaysInMonth(date);
        int day = 1;
        Calendar tempDate = date;

        for (int i = 0; i < dayButtons.length; i++) {
            String output = "";
            int c = 9999;

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
    }
}
