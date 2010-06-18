package org.youthnet.debug.services;

/**
 * User: karl
 * Date: 16-Jun-2010
 */
public interface LogService {

    public String getLog(String logName);

    public String getLog(String logName, Integer lineNum);

    public String getLogHTML(String logName);

    public String getLogHTML(String logName, Integer lineNum);
}
