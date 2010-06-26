package org.youthnet.debug.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.youthnet.debug.services.LogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        modelMap.addAttribute("logCollection", logService.getLogInputStreamCollectionHTMLEscaped(logName, lineNum));

        return "logs.jsp";
    }

    @RequestMapping(value = "/downloadLog.html")
    public void handleLogDownload(@RequestParam String logName,
                                  HttpServletResponse response) {
        log.info("Log controller -- stream log: " + logName);

        try {
            response.setContentType("text/plain");
            response.setContentLength((int)logService.getLogFileLength(logName));
	        response.setHeader("Content-Disposition","attachment; filename=\"" + logService.getLogFileName(logName) +"\"");
            FileCopyUtils.copy(logService.getLogInputStream(logName), response.getOutputStream());
        } catch (IOException e) {
            log.error("Failed to open input stream for " + logName + " log file.");
        }
    }
}
