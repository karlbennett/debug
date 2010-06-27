package org.youthnet.debug.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.youthnet.debug.io.InputStreamCollection;
import org.youthnet.debug.io.InputStreamCollectionHTMLEscaped;
import org.youthnet.debug.persistence.Page;
import org.youthnet.debug.persistence.Pageable;
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
public class LogServiceImpl implements LogService, Pageable {

    private static final Log log = LogFactory.getLog(LogServiceImpl.class);

    @Resource(name = "logFileMap")
    private Map<String, String> logFileMap;

    private Integer lineNum;

    @Override
    public String getLogFileName(String logName) {
        return getLogFile(logName).getName();
    }

    @Override
    public long getLogFileLength(String logName) {
        return getLogFile(logName).length();
    }

    @Override
    public String getLogString(String logName, int lineNum) {
        File logFile = getLogFile(logName);

        log.info("  -- Getting log string.");

        if (logFile != null) {
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
        File logFile = getLogFile(logName);

        log.info("  -- Getting log HTML escaped string.");

        if (logFile != null) {
            return FileUtil.getHTMLEscapedStringFromFile(logFile, FileUtil.getNCharOffset(logFile, lineNum, '\n'));
        }

        return null;
    }

    @Override
    public InputStream getLogInputStream(String logName, Integer lines) throws IOException {
        log.info("  -- Getting log input stream.");

        if (logName != null) {
            File logFile = getLogFile(logName);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(logFile));

            if (lines > -1) inputStream.skip(FileUtil.getNCharOffset(logFile, lines, '\n'));

            return inputStream;
        }

        return null;
    }

    @Override
    public InputStream getLogInputStream(String logName) throws IOException {
        return getLogInputStream(logName, -1);
    }

    @Override
    public InputStreamCollection getLogInputStreamCollection(String logName, int lines) throws IOException {
        if (logName != null) return new InputStreamCollection(getLogInputStream(logName, lines));
        return null;
    }

    @Override
    public InputStreamCollection getLogInputStreamCollectionHTMLEscaped(String logName, int lines) throws IOException {
        if (logName != null) return new InputStreamCollectionHTMLEscaped(getLogInputStream(logName, lines));
        return null;
    }

    private File getLogFile(String logName) {
        log.info("  -- Getting log file: " + logName);

        String logPath = logFileMap.get(logName);

        log.info("  -- Path: " + logPath);

        if (logPath != null) {
            return new File(logPath);
        }

        return null;
    }

    @Override
    public Integer getLineNumber() {
        return this.lineNum;
    }

    @Override
    public void setLineNumber(Integer lineNum) {
        this.lineNum = lineNum;
    }

    @Override
    public Page getPage(Integer pageNum) {
        return null;
    }
}
