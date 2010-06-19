package org.youthnet.debug;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: karl
 * Date: 18-May-2010
 */
public class DebugServer {

    private static final Log log = LogFactory.getLog(DebugServer.class);

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("config/spring/server-config.xml");
        context.registerShutdownHook();

        String pidString = ManagementFactory.getRuntimeMXBean().getName();

        Pattern pattern = Pattern.compile("^\\d+");
        Matcher matcher = pattern.matcher(pidString);

        log.info("Process ID string: " + pidString);

        if (matcher.find()) {
            String pid = matcher.group();
            log.info("Process ID: " + pid);

            File pidFile = new File("debug.pid");
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(pidFile));
                writer.write(pid);
            } catch (IOException e) {
                log.error("Could not write PID file: " + e.getMessage());
            } finally {
                try {
                    if(writer != null) writer.close();
                } catch (IOException e) {
                    log.error("Could not close PID file: " + e.getMessage());
                }
            }
        }
    }
}
