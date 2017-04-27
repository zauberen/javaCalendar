package javaCalendar;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import static java.nio.file.StandardOpenOption.CREATE;

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
    public static void open()
    {
		Path file = Paths.get ("c:\\jCalendarEvents");
	}
    public static void save()
    {
    	createFile();

			Path file = Paths.get("C:jCalendarEvents\\");
			String key = "dd/mm/yy/hhmm?M, " + System.getProperty("line.separator"); // waiting to work this out
			byte[] data = key.getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(data);
		final int NUMREC = 64;

		try
		{
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
			for(int count = 0; count < NUMREC; ++count)
				// writer.write();
			writer.close();

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