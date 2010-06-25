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

    @Resource(name = "logSettings")
    private LogSettings logSettings;

    @RequestMapping("/logs.html")
    public String handleRequest(@RequestParam(required = false) String logName,
                                @RequestParam(required = false) Integer lineNum,
                                ModelMap modelMap) throws Exception {
        
        log.info("Log controller");

        modelMap.addAttribute("logName", logName);
        if (logSettings.isTruncate()) modelMap.addAttribute("logString", logService.getLogHTML(logName, logSettings.getLineNum()));
        else modelMap.addAttribute("logString", logService.getLogHTML(logName));
        modelMap.addAttribute("logLineNum", logSettings.getLineNum());
        modelMap.addAttribute("truncate", logSettings.isTruncate());

        return "logs.jsp";
    }

    @RequestMapping(value = "/setloglinenum.html", method = RequestMethod.POST)
    public String handleSetLogLineNum(@RequestParam Integer logLineNum,
                                      HttpServletRequest request) {
        log.info("Log controller -- set log line number");

        log.info("  -- Set the line number to: " + logLineNum);
        logSettings.setLineNum(logLineNum);
        
        // Get the url of the page that the submit was called on.
        String referer = request.getHeader("Referer");

        // If the URL exist redirect back to it.
        if (referer != null) return "redirect:" + referer;

        // Else return to the tables page.
        return "redirect:tables.html";
    }

    @RequestMapping(value = "/setlogtruncate.html", method = RequestMethod.POST)
    public String handleSetLogTruncate(@RequestParam boolean truncate,
                                      HttpServletRequest request) {
        log.info("Log controller -- set log truncation to " + truncate);

        logSettings.setTruncate(truncate);

        // Get the url of the page that the submit was called on.
        String referer = request.getHeader("Referer");

        // If the URL exist redirect back to it.
        if (referer != null) return "redirect:" + referer;

        // Else return to the tables page.
        return "redirect:tables.html";
    }

    @RequestMapping(value = "/downloadLog.html")
    public void handleLogDownload(@RequestParam(required = false) String logName, OutputStream outputStream) {
        log.info("Log controller -- stream log: " + logName);

        try {
            FileCopyUtils.copy(logService.getLogInputStream(logName, logSettings.getLineNum()), outputStream);
        } catch (IOException e) {
            log.error("Failed to open input stream for " + logName + " log file.");
        }
    }
}
