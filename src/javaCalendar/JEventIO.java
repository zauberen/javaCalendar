package javaCalendar;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
/** JEventIO Class
 * Assigned to: Charles (cws2017)
 * Subclass to CHandler
 * Handles the file opening and closing operations for CHandler.
 * Functions:
 * void open(String): Reads events from the file at the given filepath.
 * void save(String): Saves events to the file at the given path.
 * Javadocs created by dandreas on 4/4/17.*/
public class JEventIO extends CHandler
{
    public static Object open(String path)throws Exception
    {
//    	Path path = Paths.get(".\\..\\jEventList");
		ObjectInputStream file = new ObjectInputStream(new FileInputStream(path));
		Object jEventList = file.readObject();
		file.close();
		return jEventList;
    }
    public static void save(Object obj, String path)throws Exception
    {
    	makeDirectory();
    	createFile();
		ObjectOutputStream snip = new ObjectOutputStream(new FileOutputStream(path));
		snip.writeObject(obj);
		snip.flush();
		snip.close();
    }
    public static void makeDirectory()//Creates a directory for jCalendar Application IF it does not exist
    {
    	try
		{
			Path path = Files.createDirectory(FileSystems.getDefault().getPath
						("c:\\jCalendarEvents"),  new FileAttribute<?>[0] );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
    public static void createFile()//Creates an empty jEventList file IF it does not exist.
    {
    	File fileX = new File("jEventList");
    	try
		{
			fileX.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
}