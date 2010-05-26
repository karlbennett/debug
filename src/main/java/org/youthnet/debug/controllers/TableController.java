package org.youthnet.debug.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.youthnet.debug.dao.admin.AdminGenericDao;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.util.HibernateUtil;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public String handleRequest(@RequestParam(required = false) String tableName,
                                @RequestParam(required = false) String id,
                                @RequestParam(required = false) String columnName,
                                ModelMap modelMap) throws Exception {
        log.debug("Table controller");

        // Add the table names to be used to auto generate and name the table tabs.
        List<String> tableNames = adminJdbcDao.getTableNames();
        Collections.sort(tableNames);
        modelMap.addAttribute("tableNames", tableNames);

        // If a table name has not been  given set it to the first table in the tab list.
        if (tableName == null || tableName.equals("")) {
            tableName = tableNames.get(0);
        }
        modelMap.addAttribute("tableRows", adminJdbcDao.getTableRows(tableName));

        return "tables.jsp";
    }
}
