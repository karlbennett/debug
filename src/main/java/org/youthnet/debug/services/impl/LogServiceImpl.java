package org.youthnet.debug.services.impl;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.youthnet.debug.io.RandomAccessFileInputStream;
import org.youthnet.debug.services.LogService;
import org.youthnet.debug.util.exceptions.ExceptionsUtil;
import org.youthnet.debug.util.io.FileUtil;

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

    private static final int BUFFER_SIZE = 1024;

    @Resource(name = "logFileMap")
    private Map<String, String> logFileMap;

    @Override
    public String getLogString(String logName, Integer lineNum) {

        log.info("  -- Getting log string: " + logName);

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        if (logPath != null && lineNum.intValue() > 0) {
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

        String logString = getLogString(logName);

        if (logString != null) {
            return StringEscapeUtils.escapeHtml(logString).replaceAll("\n", "<BR>\n").replaceAll(" ", "&nbsp;");
        }

        return null;
    }

    @Override
    public String getLogHTML(String logName, Integer lineNum) {

        String logString = getLogString(logName, lineNum);

        if (logString != null) {
            return StringEscapeUtils.escapeHtml(logString).replaceAll("\n", "<BR>\n").replaceAll(" ", "&nbsp;");
        }

        return null;
    }

    @Override
    public InputStream getLogInputStream(String logName, Integer lineNum) throws IOException {
        log.info("  -- Getting log input stream: " + logName);

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        return FileUtil.getLogInputStream(new File(logPath), lineNum);
    }
}
