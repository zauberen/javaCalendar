package javaCalendar;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class DayPanel extends JPanel
{
    // Variables
    private JButton newEvent = new JButton("New/Edit");
    private JButton back = new JButton("Back");
    private GridLayout eventLayout = new GridLayout(0,2);
    // Create instance of the event handler
    private EventIO events = new EventIO();
    // Calendar objects used to track current date and active date TODO: Implement tracking of current date in calendar GUI (MP)
    private Calendar activeDate = Calendar.getInstance(); // this should be changed as the month / day edited does.
    private Calendar currentDate = Calendar.getInstance(); // this should *not* be changed as the month / day edited does.

    DayPanel(LayoutManager layout, CalendarFrame frame)
    {
        super(layout);
        // Set up actionListeners
        newEvent.addActionListener((new AbstractAction() {
            private static final long serialVersionUID = 735060752220097605L;

            @Override
            public void actionPerformed(ActionEvent e) {
                events.editEvent(activeDate);
                showDate(activeDate);
            }
        }));

        back.addActionListener((new AbstractAction() {
            private static final long serialVersionUID = -6483552148578258852L;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.gotoMonthPanel();
            }
        }));
    }

    public void showDate(Calendar date)
    {
        // Variables
        activeDate = date;
        HashMap<String,String> hashEvents = events.getEvents(activeDate);
        Set setEvents = hashEvents.entrySet();
        Object[] arrayEvents = setEvents.toArray();
        JLabel monthLabel = new JLabel("Month: " + (activeDate.get(Calendar.MONTH) + 1));
        JLabel dayLabel = new JLabel("Day: " + activeDate.get(Calendar.DATE));
        JLabel dateHeader = new JLabel("Date");
        JLabel descrHeader = new JLabel("Description");

        // Refresh event panel
        this.removeAll();
        this.add(monthLabel);
        this.add(dayLabel);
        this.add(dateHeader);
        this.add(descrHeader);
        // Fill in events on the table.
        for (int i = 0; i < hashEvents.size(); i++)
        {
            JLabel description = new JLabel(arrayEvents[i].toString());
            this.add(description);
        }
        this.add(back);
        this.add(newEvent);
    }
}
