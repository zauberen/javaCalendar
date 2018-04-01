package javaCalendar;
import javax.swing.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

/** EventIO Class
 * Subclass to EventFrame
 * Handles the file opening and closing operations for EventFrame.
 * Functions:
 * void open(String): Reads events from the file at the given filepath.
 * void save(String): Saves events to the file at the given path.
 * TODO: Fix this whole class.
 * Javadocs created by dandreas on 4/4/17.
 */
public class EventIO extends EventFrame
{
	/**
	 * Autogen'd version UID
	 */
	private static final long serialVersionUID = 2107611223867266056L;

	public void open()
	{ //TODO: Fill this function
		
	}
	public void save()
	{ //TODO: Fill this function
		
	}

	public void createFile()
	{ //TODO: Remake this function
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