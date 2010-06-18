package org.youthnet.debug.services.impl;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.youthnet.debug.services.LogService;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * User: karl
 * Date: 16-Jun-2010
 */
@Component("logService")
public class LogServiceImpl implements LogService {

    private static final Log log = LogFactory.getLog(LogServiceImpl.class);

    @Resource(name = "logFileMap")
    Map<String, String> logFileMap;

    @Override
    public String getLog(String logName) {

        log.info("Getting log: " + logName);

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        if (logPath != null) {
            BufferedReader reader = null;
            StringBuffer logStringBuffer = new StringBuffer();

            try {
                reader = new BufferedReader(new FileReader(logPath));

                String line = null;
                char[] charArray = new char[1024];
                int charNum = 0;
                while ((charNum = reader.read(charArray)) > -1) {
                    logStringBuffer.append(charArray, 0, charNum);
                }
            } catch (IOException e) {
                log.error("Failed to open the " + logName + " log file.");
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Failed to close the buffered reader: " + e.getStackTrace());
                }
            }

            return logStringBuffer.toString();
        }

        return null;
    }

    @Override
    public String getLog(String logName, Integer lineNum) {
        return tail(getLog(logName), lineNum);
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
        return tail(getLogHTML(logName), lineNum);
    }

    private String tail(String log, Integer lineNum) {
        if (log != null && !log.equals("") && lineNum != null && !lineNum.equals(new Integer(0))) {
            String[] lineArray = log.split("\n");
            
            if (lineNum.intValue() < lineArray.length) {
                String[] subLineArray = Arrays.copyOfRange(lineArray, lineArray.length - (lineNum.intValue() + 1),
                        lineArray.length - 1);

                StringBuffer subLineStringBuffer = new StringBuffer();
                for (String line : subLineArray) {
                    subLineStringBuffer.append(line);
                }

                return subLineStringBuffer.toString();
            }
        }

        return log;
    }
}
