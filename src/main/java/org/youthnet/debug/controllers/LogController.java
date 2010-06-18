package org.youthnet.debug.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.youthnet.debug.controllers.bean.session.LogSettings;
import org.youthnet.debug.services.LogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
                                ModelMap modelMap) throws Exception {
        
        log.info("Log controller");

        modelMap.addAttribute("logName", logName);
        modelMap.addAttribute("logString", logService.getLogHTML(logName, logSettings.getLineNum()));
        modelMap.addAttribute("logLineNum", logSettings.getLineNum());

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
}
