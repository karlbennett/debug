package org.youthnet.debug.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.youthnet.debug.services.LogService;
import org.youthnet.debug.util.io.FileUtil;

import javax.annotation.Resource;
import java.io.*;
import java.util.Map;

/**
 * User: karl
 * Date: 16-Jun-2010
 */
@Component("logService")
public class LogServiceImpl implements LogService {

    private static final Log log = LogFactory.getLog(LogServiceImpl.class);

    private static final int BUFFER_SIZE = 1024;

    @Resource(name = "logFileMap")
    private Map<String, String> logFileMap;

    @Override
    public String getLogString(String logName, int lineNum) {

        log.info("  -- Getting log string: " + logName);

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        if (logPath != null) {
            File logFile = new File(logPath);
            return FileUtil.getStringFromFile(logFile, FileUtil.getNCharOffset(logFile, lineNum, '\n'));
        }

        return null;
    }

    @Override
    public String getLogString(String logName) {
        return getLogString(logName, -1);
    }

    @Override
    public String getLogHTML(String logName) {
        return getLogHTML(logName, -1);
    }

    @Override
    public String getLogHTML(String logName, int lineNum) {

        log.info("  -- Getting log HTML escaped string: " + logName);

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        if (logPath != null) {
            File logFile = new File(logPath);
            return FileUtil.getHTMLEscapedStringFromFile(logFile, FileUtil.getNCharOffset(logFile, lineNum, '\n'));
        }

        return null;
    }

    @Override
    public InputStream getLogInputStream(String logName, int lineNum) throws IOException {
        log.info("  -- Getting log input stream: " + logName);

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        if (logPath != null) {
            return FileUtil.getLogInputStream(new File(logPath), lineNum);
        }

        return null;
    }
}
