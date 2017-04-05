package javaCalendar;

import java.util.Date; // For time handling.

/** CHandler Class
 * Assigned to: Isaac (isaacwalth)
 * Superclass to JEventIO.
 * This class handles calendar events. It allows you to both edit and add events.
 *
 * Functions:
 * (superclass) open(String) (see JEventIO)
 * (superclass) save(String) (see JEventIO)
 * void editEvent(int): Edits an event at the given index (base 0)
 * void newEvent(Date,String): Creates and adds a new event to the event array.
 *
 * Javadocs created by dandreas on 4/4/17.
 */

public class CHandler extends JEventIO
{
    public JEvent editEvent(int index)
    {
        return new JEvent(); //TODO filler return statement, replace me
    }

    public void newEvent(Date time, String description)
    {

    }

}
