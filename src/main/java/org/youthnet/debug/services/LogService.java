package org.youthnet.debug.services;

import org.youthnet.debug.io.InputStreamCollection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: karl
 * Date: 16-Jun-2010
 */
public interface LogService {

    public String getLogFileName(String logName);

    public long getLogFileLength(String logName);

    /**
     * Function to get the log file represented in a string and only containing a given number of the last lines in the file.
     *
     * @param  logName the name of the log file to return
     * @param  lineNum the number of lines to return from the end of the file
     * @return  the log file as a string truncated to the last lineNum lines..
     * */
    public String getLogString(String logName, int lineNum);

    /**
     * Function to get the log file represented as a string.
     *
     * @param  logName the name of the log file to return
     * @return  the log file as a string.
     * */
    public String getLogString(String logName);

     /**
     * Function to get the log file represented as a string and escaped for HTML along with each new line ending in <BR>.
     *
     * @param  logName the name of the log file to return
     * @return  the log file as a string that has been HTML escaped with embedded <BR>'s.
     * */
    public String getLogHTML(String logName);

    /**
     * Function to get the log file represented in a string and escaped for HTML along with each new line ending in <BR>.
     * That also only contains a given number of the last lines in the file.
     *
     * @param  logName the name of the log file to return
     * @param  lineNum the number of lines to return from the end of the file
     * @return  the log file as a string truncated to the last lineNum lines and  that has been HTML escaped with embedded <BR>'s...
     * */
    public String getLogHTML(String logName, int lineNum);

    public InputStream getLogInputStream(String logName, Integer lines) throws IOException;

    public InputStream getLogInputStream(String logName) throws IOException;

    public InputStreamCollection getLogInputStreamCollection(String logName, int lines) throws IOException;

    public InputStreamCollection getLogInputStreamCollectionHTMLEscaped(String logName, int lines) throws IOException;
}
