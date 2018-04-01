package javaCalendar;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class MonthPanel extends JPanel
{
    // Variables
    private EventIO events = new EventIO();
    private JButton load = new JButton("Load");
    private JButton save = new JButton("Save");
    private JButton next = new JButton("Next");
    private JButton prev = new JButton("Previous");
    private JLabel monthLabel = new JLabel();
    private JLabel yearLabel = new JLabel();
    private JButton[] dayButtons = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
    private GridLayout calendarLayout = new GridLayout(8,7);
    // Calendar objects used to track current date and active date TODO: Implement tracking of current date in calendar GUI (MP)
    private Calendar activeDate = Calendar.getInstance(); // this should be changed as the month / day edited does.
    private Calendar currentDate = Calendar.getInstance(); // this should *not* be changed as the month / day edited does.
    
    MonthPanel(LayoutManager layout, CalendarFrame frame)
    {
        super(layout);
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
            public void actionPerformed(ActionEvent actionEvent)
            {
                forwardCalendar();
            }
        }));

        // Set up the day labels
        JLabel sun = new JLabel("Sunday");
        JLabel mon = new JLabel("Monday");
        JLabel tue = new JLabel("Tuesday");
        JLabel wed = new JLabel("Wednesday");
        JLabel thu = new JLabel("Thursday");
        JLabel fri = new JLabel("Friday");
        JLabel sat = new JLabel("Saturday");
        this.add(sun);
        this.add(mon);
        this.add(tue);
        this.add(wed);
        this.add(thu);
        this.add(fri);
        this.add(sat);

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
                                frame.gotoDayPanel(activeDate);
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null,"Calendar is not finished loading...");
                            }
                        }
                    }
                }
            }));
            this.add(dayButtons[i]);
        }

        // Set up month/year label
        monthLabel.setText("Month: " + activeDate.get(Calendar.MONTH));
        yearLabel.setText("Year: " + activeDate.get(Calendar.YEAR));

        this.add(load);
        this.add(save);
        this.add(prev);
        this.add(next);
        this.add(monthLabel);
        this.add(yearLabel);

        // Actually set up the month
        fillCalendar(currentDate);
    }

    private void forwardCalendar()
    {
        Calendar date = activeDate;
        date.set(Calendar.MONTH, activeDate.get(Calendar.MONTH) + 2);

        fillCalendar(date);

        // Set up month/year label
        monthLabel.setText("Month: " + (activeDate.get(Calendar.MONTH)));
        yearLabel.setText("Year: " + activeDate.get(Calendar.YEAR));
    }

    private void backCalendar()
    {
        Calendar date = activeDate;
        date.set(Calendar.MONTH, (activeDate.get(Calendar.MONTH)));

        fillCalendar(date);

        // Set up month/year label
        monthLabel.setText("Month: " + (activeDate.get(Calendar.MONTH)));
        yearLabel.setText("Year: " + activeDate.get(Calendar.YEAR));
    }

    private void fillCalendar(Calendar date)
    {
        // Variables
        activeDate = date;
        int daysInMonth = getDaysInMonth(activeDate) - 1;
        int day = 1;
        Calendar tempDate = activeDate; // Marked as redundant TODO: fix this (Low Priority)

        for(int i = 0; i < dayButtons.length; i++)
        {
            String output = "";
            int c;

            if (i < 7)
            {
                if (i == 0)
                {
                    tempDate.set(Calendar.DATE, 1); // TODO: this is really frickin weird
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
                /* TODO:(Fix the button actionlistener) This if would add a * at the end of the date when there is an event. Currently breaks
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

    private int getDaysInMonth(Calendar date)
    {
        // Variables
        int daysInMonth;

        // Determine the number of days in the given month
        switch(date.get(Calendar.MONTH) + 1)
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
}
