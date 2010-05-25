package org.youthnet.debug.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.youthnet.debug.dao.jdbc.JdbcDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: karl
 * Date: 25-May-2010
 */
@Controller
public class TableController {

    private static final Log log = LogFactory.getLog(TableController.class);

    @Resource(name = "adminJdbcDaoImpl")
    private JdbcDao adminJdbcDao;

    @RequestMapping("/tables.html")
    public String handleRequest(@RequestParam(required = false)String tableName, ModelMap modelMap) throws Exception {
        log.debug("Table controller");

        // Add the table names to be used to auto generate and name the table tabs.
        List<String> tableNames = adminJdbcDao.getTableNames();

        for (String name : tableNames) {
            log.debug(" -- Table name: " + name);
        }

        modelMap.addAttribute("tableNames", tableNames);

        return "tables.jsp";
    }
}
