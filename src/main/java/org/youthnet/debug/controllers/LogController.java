package org.youthnet.debug.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.youthnet.debug.services.LogService;

import javax.annotation.Resource;

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
                                ModelMap modelMap) throws Exception {
        
        log.info("Log controller");

        modelMap.addAttribute("logName", logName);
        modelMap.addAttribute("logString", logService.getLogHTML(logName));

        return "logs.jsp";
    }
}
