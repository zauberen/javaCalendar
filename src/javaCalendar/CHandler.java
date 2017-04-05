package javaCalendar;

import java.util.Date; // For time handling.
import java.util.HashMap;

/** CHandler Class
 * Assigned to: Isaac (isaacwalth)
 * Superclass to JEventIO.
 * This class handles calendar events. It allows you to both edit and add events.
 *
 * Required Variables:
 * HashMap<Date,String> jEventList: Stores all of the events. Info on HashMaps: https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
 *
 * Functions:
 * boolean checkForEvents(Date): Checks if there are any events that occur on the given date.
 * Hashmap<Date,String> getEvents(Date): Return all events that occur on the given date.
 * void newEvent(Date,String): Creates and adds a new event to the event hashmap.
 * void editEvent(Date,String): Replaces an old event from the hashmap.
 *
 * Javadocs created by dandreas on 4/4/17.
 */

public class CHandler
{
    HashMap<Date,String> jEventList = new HashMap<>();

    public boolean checkForEvents(Date date)
    {
        return true; //TODO: dummy return, replace me
    }

    public HashMap<Date,String> getEvents(Date date)
    {
        return new HashMap<>(); //TODO: dummy return, replace me
    }

    public void newEvent(Date time, String description)
    {

    }

    public void editEvent(Date time,String description)
    {

    }

}
