package org.youthnet.debug.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.youthnet.debug.controllers.bean.session.LogSettings;
import org.youthnet.debug.services.LogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * User: karl
 * Date: 16-Jun-2010
 */
@Controller
public class LogController {

    private static final Log log = LogFactory.getLog(LogController.class);

    @Resource(name = "logService")
    private LogService logService;

    @RequestMapping("/logs.html")
    public String handleRequest(@RequestParam(required = false) String logName,
                                @RequestParam(required = false) Integer lineNum,
                                ModelMap modelMap) throws Exception {
        
        log.info("Log controller");

        if (lineNum == null) lineNum = 0; // Stop any null pointer exceptions.

        modelMap.addAttribute("logString", logService.getLogHTML(logName, lineNum));

        return "logs.jsp";
    }

    @RequestMapping(value = "/downloadLog.html")
    public void handleLogDownload(@RequestParam String logName,
                                  @RequestParam(required = false) Integer lineNum,
                                  OutputStream outputStream) {
        log.info("Log controller -- stream log: " + logName);

        try {
            if (lineNum == null) lineNum = 0;
            FileCopyUtils.copy(logService.getLogInputStream(logName, lineNum), outputStream);
        } catch (IOException e) {
            log.error("Failed to open input stream for " + logName + " log file.");
        }
    }
}
