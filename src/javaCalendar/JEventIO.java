package javaCalendar;
import javax.swing.*;
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
		Path file = Paths.get ("c:\\users\\jCalendarEvents.txt");

		JOptionPane.showMessageDialog(null, "This be !!!!!!!! ");
	}
	public static void save()
	{
		Path file = Paths.get("c:\\users\\jCalendarEvents.txt");
		String key = "0000000000" + System.getProperty("line.separator");
		final int RECSIZE = key.length();
	}

	public static void createFile()
	{
		Path file = Paths.get("c:\\users\\jCalendarEvents.txt");
		String key = "0000000000" + System.getProperty("line.separator");
		byte[] data = key.getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(data);
		final int NUMREC = 64;

		try
		{
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
			for(int count = 0; count < NUMREC; ++count) writer.write(key, 0, key.length());
			writer.close();

		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "This be ??????? ");

			e.printStackTrace();

		}
	}
}