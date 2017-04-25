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
 * Javadocs created by dandreas on 4/4/17.
 */

public class CFrame  extends JFrame
{
    final int FRAME_WIDTH = 800;
    final int FRAME_HEIGHT = 600;
    GridLayout calendarLayout = new GridLayout(6,7);
    JPanel calendarPanel = new JPanel(calendarLayout);
    JPanel editPanel = new JPanel();
    JEventIO events = new JEventIO();
    Calendar currentMonth = Calendar.getInstance(); // this should be changed as the month / day edited does.

    JButton[] dayButtons = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};

    /// Menubar things
    // for the calendar panel
    JMenu load = new JMenu("Load");
    JMenu next = new JMenu("Next");
    JMenu prev = new JMenu("Previous");

    // for the edit panel
    JMenu edit = new JMenu("Edit");
    JMenu save = new JMenu("Save");
    JMenu back = new JMenu("Back");
    JMenu cancel = new JMenu("Cancel");
    /// end menubar things

    public CFrame()
    {
        // Set up the basic frame
        super("Java Calendar");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // end frame

        // Set up the menubar
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(load);
        menuBar.add(next);
        menuBar.add(prev);

        this.add(menuBar);
        // end menubar

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
            dayButtons[i].addActionListener(( new AbstractAction() {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    for (int i = 0; i < 35; i++)
                    {
                        if(dayButtons[i] == e.getSource())
                        {
                            int day = Integer.parseInt(dayButtons[i].getText());
                        }
                    }
                }
            }));
            calendarPanel.add(dayButtons[i]);
        }
        this.add(calendarPanel);
        fillCalendar();
        // end calendar panel
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
        int daysInMonth = getDaysInMonth(date);

        for(int i = 0; i < 35; i++)
        {
            String output = "";
            int c;

            if (i < daysInMonth)
            {
                c = i + 1;
                /* This if would add a * at the end of the date when there is an event. Currently breaks
                 * the button actionlistener, so unfortunately we will have to do without for now
                if (events.checkForEvents(new Date(date.get(Calendar.YEAR),date.get(Calendar.MONTH),c-1)))
                    output = c + "*";
                */
            }
            else
            {
                c = i - daysInMonth + 1;
                output += c;
            }

            dayButtons[i].setText(output);
        }
    }

    public void fillCalendar(Calendar date)
    {

    }

    public void backCalendar()
    {

    }

    public void forwardCalendar()
    {

    }
}
