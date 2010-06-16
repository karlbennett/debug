package org.youthnet.debug.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.youthnet.debug.services.LogService;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        log.info("  -- Path: " + logFileMap.get(logName));

        BufferedReader reader = null;
        StringBuffer logStringBuffer = new StringBuffer();

        try {
            reader = new BufferedReader(new FileReader(logFileMap.get(logName)));

            String line = null;
            while ((line = reader.readLine()) != null) {
                logStringBuffer.append(line);
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
}
