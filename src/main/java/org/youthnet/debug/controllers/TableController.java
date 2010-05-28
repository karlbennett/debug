package org.youthnet.debug.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.util.HibernateUtil;

import javax.annotation.Resource;
import java.util.Collections;
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

    @Resource(name = "coreJdbcDaoImpl")
    private JdbcDao coreJdbcDao;

    @Resource(name = "&adminSessionFactory")
    AnnotationSessionFactoryBean adminSessionFactory;

    @Resource(name = "&coreSessionFactory")
    AnnotationSessionFactoryBean coreSessionFactory;

    @RequestMapping("/tables.html")
    public String handleRequest(@RequestParam(required = false) String tableName,
                                @RequestParam(required = false) String id,
                                ModelMap modelMap) throws Exception {
        log.debug("Table controller");

        // Add the table names to be used to auto generate and name the table tabs.
        List<String> tableNames = adminJdbcDao.getTableNames();
        tableNames.addAll(coreJdbcDao.getTableNames());
        Collections.sort(tableNames);
        modelMap.addAttribute("tableNames", tableNames);

        // If a table name has not been  given set it to the first table in the tab list.
        if (tableName == null || tableName.equals("")) {
            tableName = tableNames.get(0);
        }
        modelMap.addAttribute("tableRows", adminJdbcDao.getTableRows(tableName));
        modelMap.addAttribute("rowId", id);

        return "tables.jsp";
    }

    @RequestMapping("/row.html")
    public String handleColumnRequest(@RequestParam(required = false) String id,
                                      @RequestParam(required = false) String columnName) {
        return "redirect:tables.html?tableName="
                + HibernateUtil.getTableNameForClass(HibernateUtil.getTableClassNameForColumnReference(
                columnName.toLowerCase(), adminSessionFactory), adminSessionFactory) + "&id=" + id + "#" + id;
    }
}
