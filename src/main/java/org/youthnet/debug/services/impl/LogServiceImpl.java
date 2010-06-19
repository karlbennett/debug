package org.youthnet.debug.services.impl;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.youthnet.debug.services.LogService;

import javax.annotation.Resource;
import java.io.*;
import java.util.Arrays;
import java.util.Map;

/**
 * User: karl
 * Date: 16-Jun-2010
 */
@Component("logService")
public class LogServiceImpl implements LogService {

    private static final Log log = LogFactory.getLog(LogServiceImpl.class);

    private static final int BUFFERSIZE = 1024;

    @Resource(name = "logFileMap")
    private Map<String, String> logFileMap;

    @Override
    public String getLog(String logName) {

        log.info("Getting log: " + logName);

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        if (logPath != null) {
            File logFile = new File(logPath);
            BufferedReader reader = null;
            StringBuffer logStringBuffer = new StringBuffer();

            try {
                reader = new BufferedReader(new FileReader(logFile));

                char[] charArray = new char[1024];
                int charNum = 0;
                while ((charNum = reader.read(charArray)) > -1) {
                    logStringBuffer.append(charArray, 0, charNum);
                }
            } catch (IOException e) {
                log.error("Failed to open the " + logFile.getName() + " log file.\n" + e.getStackTrace());
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Failed to close the buffered reader.\n" + e.getStackTrace());
                }
            }

            return logStringBuffer.toString();
        }

        return null;
    }

    @Override
    public String getLog(String logName, Integer lineNum) {

        log.info("  -- Getting log: " + logName);
        log.info("  -- Tailing to last " + lineNum + " lines.");

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        if (logPath != null && lineNum.intValue() > 0) {
            File logFile = new File(logPath);

            try {
                RandomAccessFile randomAccessLog = new RandomAccessFile(logFile, "r"); //  Open the log with random access.

                long lineCount = 0; // Variable to store the number of lines (\n) found.
                long offset = logFile.length(); // Variable to hole the position of the file pointer.
                int finalChunk = BUFFERSIZE; // An int to hold the lenght of the final chunk of the file to read in if the whole file is read.
                byte[] byteBuffer = new byte[BUFFERSIZE]; // Buffer to hold a chunk of the file.
                char[] charArray = null; // Character array to hole the chars held in the byte buffer.
                // Starting from the end of the file and read backwards until enough lines have been found.
                while (offset > 0 && lineCount < lineNum) {
                    offset -= (BUFFERSIZE); // Set the offset one buffers length before the current one.
                    if (offset < 0) { // Check to see if we have gone past the start of the file.
                        finalChunk = BUFFERSIZE + (int)offset; // If we have find the number of the last few bytes left to read.
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
                    for (int i = charArray.length-1; i >= 0; i--) {
                        if (charArray[i] == '\n') lineCount++;
                        if (lineCount >= lineNum) { // When we counted enough new lines...
                            offset += i; // ...set the offset to point to the start of the last line...
                            break; // ...then break out of this loop.
                        }
                    }
                }

                randomAccessLog.seek(offset); // Move the file pointer to the start of the last line we wish to display.

                // Starting from the offset read to the end of the file.
                StringBuffer tailedLog = new StringBuffer(); // String buffer to hold the log text.
                byteBuffer = new byte[BUFFERSIZE]; // Reset the buffer to it's original size.
                int byteNum = 0; // Variable to hold the number of bytes that have been read.
                // Read all of the rest of the file.
                while ((byteNum = randomAccessLog.read(byteBuffer)) > -1) {
                    tailedLog.append(new String(Arrays.copyOfRange(byteBuffer, 0, byteNum)));
                }

                return tailedLog.toString(); // Return the tailed log.
            } catch (FileNotFoundException e) {
                log.error("Failed to OPEN the " + logFile.getName() + " log file.\n" + e.getMessage());
            } catch (IOException e) {
                log.error("Failed to READ the " + logFile.getName() + " log file.\n" + e.getMessage());
            }
        }

        return null;
    }

    @Override
    public String getLogHTML(String logName) {

        String logString = getLog(logName);

        if (logString != null) {
            return StringEscapeUtils.escapeHtml(logString).replaceAll("\n", "<BR>\n").replaceAll(" ", "&nbsp;");
        }

        return null;
    }

    @Override
    public String getLogHTML(String logName, Integer lineNum) {

        String logString = getLog(logName, lineNum);

        if (logString != null) {
            return StringEscapeUtils.escapeHtml(logString).replaceAll("\n", "<BR>\n").replaceAll(" ", "&nbsp;");
        }

        return null;
    }

    private char[] convertByteToCharArray(byte[] array) {
        char[] charArray = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            charArray[i] = (char) array[i];
        }

        return charArray;
    }

    private int countChar(char[] array, char character) {

        int count = 0;
        for (char c : array) {
            if (c == character) count++;
        }

        return count;
    }

    private int indexOf(char[] array, char character) {
        int count = 0;
        while (count < array.length && array[count] != character) count++;
        return count;
    }
}
