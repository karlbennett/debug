package org.youthnet.debug.util.io;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.youthnet.debug.io.RandomAccessFileInputStream;

import javax.swing.JFileChooser;
import java.io.*;

/**
 * User: karl
 * Date: 25-Jun-2010
 */
public class FileUtil {

    private static final Log log = LogFactory.getLog(FileUtil.class);

    private static final int BUFFER_SIZE = 1024;

    private FileUtil() {
    }

    public static long getNCharOffset(File file, int n, char c) {
        long offset = n; // Variable to hole the position of the file pointer.
        RandomAccessFile randomAccessLog = null;

        if (offset > -1) { // Test to see if offset should be found at all.
            try {
                randomAccessLog = new RandomAccessFile(file, "r"); //  Open the log with random access.

                long charCount = 0; // Variable to store the number of lines (\n) found.
                offset = file.length(); // Set the offset to point to the end of thee file.
                int finalChunk = BUFFER_SIZE; // An int to hold the lenght of the final chunk of the file to read in if the whole file is read.
                byte[] byteBuffer = new byte[BUFFER_SIZE]; // Buffer to hold a chunk of the file.
                char[] charArray = null; // Character array to hole the chars held in the byte buffer.
                // Starting from the end of the file and read backwards until enough lines have been found.
                while (offset > 0 && charCount < n) {
                    offset -= BUFFER_SIZE; // Set the offset one buffers length before the current one.
                    if (offset < 0) { // Check to see if we have gone past the start of the file.
                        finalChunk = BUFFER_SIZE + (int) offset; // If we have find the number of the last few bytes left to read.
                        offset = 0; // And set the offset to zero.
                    }
                    randomAccessLog.seek(offset); // Move the file point back to the new position.
                    // If we are not at the start of the file...
                    if (offset > 0) randomAccessLog.read(byteBuffer); // ...read in a buffers worth of the file...
                    else {
                        byteBuffer = new byte[finalChunk]; // ...else set the buffer to the size of the last few bytes to read.
                        randomAccessLog.read(byteBuffer); // Then read them in.
                    }
                    charArray = convertByteToCharArray(byteBuffer); // Convert the bytes into chars.
                    // Loop through the chars counting all the new lines.
                    for (int i = charArray.length - 1; i >= 0; i--) {
                        if (charArray[i] == c) charCount++;
                        if (charCount >= n) { // When we have counted enough chars...
                            offset += i; // ...set the offset to point to the position of the last char...
                            break; // ...then break out of this loop.
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                log.error("File " + file.getPath() + " not found. Exception: " + e.getClass().getName()
                        + "\nMessage: " + e.getMessage());
            } catch (IOException e) {
                log.error("Error with " + file.getPath() + ". Exception: " + e.getClass().getName()
                        + "\nMessage: " + e.getMessage());
            } finally {
                try {
                    if (randomAccessLog != null) randomAccessLog.close();
                } catch (IOException e) {
                    log.error("File " + file.getPath() + " could not be closed. Exception: " + e.getClass().getName()
                            + "\nMessage: " + e.getMessage());
                }
            }
        }

        return offset;
    }

    public static String getStringFromFile(File file, long offset, Parser parser) {
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer(); // StringBuffer to hold the contents retrieved from the file.

        try {
            reader = new BufferedReader(new FileReader(file)); // Open the file.

            if (offset > -1) reader.skip(offset); // Skip to the give section in the file.

            char[] charArray = new char[BUFFER_SIZE]; // Create a buffer to hold the chars read in from the file.
            int charNum = 0; // Variable to hold the number of chars that have been read from the file.
            if (parser != null) { // Optimisation: Check once for parser instead of every time the file is read.
                while ((charNum = reader.read(charArray)) > -1) {
                    stringBuffer.append(
                            parser.pars(new String(charArray, 0, charNum))); // Read the chars into the String Buffer.
                }
            } else {
                while ((charNum = reader.read(charArray)) > -1) {
                    stringBuffer.append(charArray, 0, charNum); // Read the chars into the String Buffer.
                }
            }

        } catch (FileNotFoundException e) {
            log.error("File " + file.getPath() + " not found. Exception: " + e.getClass().getName()
                    + "\nMessage: " + e.getMessage());
        } catch (IOException e) {
            log.error("Error with " + file.getPath() + ". Exception: " + e.getClass().getName()
                    + "\nMessage: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                log.error("File " + file.getPath() + " could not be closed. Exception: " + e.getClass().getName()
                        + "\nMessage: " + e.getMessage());
            }
        }

        return stringBuffer.toString();
    }

    public static String getStringFromFile(File file, long offset) {
        return getStringFromFile(file, offset, null);
    }

    public static String getStringFromFile(File file) {
        return getStringFromFile(file, -1, null);
    }

    public static String getHTMLEscapedStringFromFile(File file, long offset) {
        return getStringFromFile(file, offset, new HTMLParser());
    }

    public static String getHTMLEscapedStringFromFile(File file) {
        return getStringFromFile(file, -1, new HTMLParser());
    }

    private static char[] convertByteToCharArray(byte[] array) {
        char[] charArray = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            charArray[i] = (char) array[i];
        }

        return charArray;
    }

    private static class HTMLParser implements Parser {

        @Override
        public String pars(String str) {
            return StringEscapeUtils.escapeHtml(str).replaceAll("\n", "<BR>\n").replaceAll(" ", "&nbsp;");
        }
    }
}
