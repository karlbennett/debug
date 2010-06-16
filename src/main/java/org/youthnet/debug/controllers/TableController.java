package org.youthnet.debug.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.db.bean.session.Schema;
import org.youthnet.debug.util.HibernateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * User: karl
 * Date: 25-May-2010
 */
@Controller
public class TableController {

    private static final Log log = LogFactory.getLog(TableController.class);

    @Resource(name = "schema")
    private Schema schema;

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
        log.info("Table controller");

        log.info("  -- Getting the current schema name.");

        log.info("      -- Schema name is: " + schema.getName());
        log.info("      -- Schema id is: " + schema.hashCode());
        modelMap.addAttribute("currentSchemaName", schema.getName()); // Make the schema names list accessible to the page.


        log.info("  -- Getting admin table names.");
        // Add the schema names to be used to auto generate schema dropdown.
        modelMap.addAttribute("schemaNames", adminJdbcDao.getSchemaNames()); // Make the schema names list accessible to the page.

        // Add the table names to be used to auto generate and name the table tabs.
        List<String> tableNames = adminJdbcDao.getTableNames(); // Get the admin table names.
        log.info("  -- Getting core table names.");
        tableNames.addAll(coreJdbcDao.getTableNames()); // Add the core table names.
        modelMap.addAttribute("tableNames", tableNames); // Make the table names list accessible to the page.

        // If a table name has not been  given set it to the first table in the tab list.
        if (tableName == null || tableName.equals("")) {
            tableName = tableNames.get(0);
        }

        log.info("  -- Searching for " + tableName + " in admin schema.");
        // Search for the requested table in the admin schema.
        List<Map<String, Object>> rows = adminJdbcDao.getTableRows(tableName);
        // If it's not there search for it in the core schema.
        if (rows == null || rows.size() == 0) {
            log.info("  -- Not found in admin schema searching for " + tableName + " in core schema.");
            rows = coreJdbcDao.getTableRows(tableName);
            if (rows == null) log.info("    -- Table " + tableName + " not found in core schema.");
        }

        modelMap.addAttribute("tableRows", rows);

        // If a row id has been given pass it to the page so that it will be highlighted.
        modelMap.addAttribute("rowId", id);

        return "tables.jsp";
    }

    @RequestMapping("/row.html")
    public String handleColumnRequest(@RequestParam(required = false) String id,
                                      @RequestParam(required = false) String columnName) {
        log.info("Table controller -- column request handler");

        log.info("  -- Finding the table name.");
        String tableName = HibernateUtil.getTableNameForClass(HibernateUtil.getTableClassNameForColumnReference(
                columnName.toLowerCase(), adminSessionFactory), adminSessionFactory);
        if (tableName == null) {
            tableName = HibernateUtil.getTableNameForClass(HibernateUtil.getTableClassNameForColumnReference(
                    columnName.toLowerCase(), coreSessionFactory), coreSessionFactory);
        }
        log.info("      -- Table name found: " + tableName);
        return "redirect:tables.html?tableName="
                + tableName + "&id=" + id + "#" + tableName;
    }

    @RequestMapping(value = "/setschema.html", method = RequestMethod.POST)
    public String handleSetSchema(@RequestParam String schemaName,
                                  HttpServletRequest request) {
        log.info("Table controller -- set schema handler");

        log.info("  -- Set the schema to: " + schemaName);
        schema.setName(schemaName);
        log.info("  -- Schema set to: " + schema.getName());

        // Get the url of the page that the submit was called on.
        String referer = request.getHeader("Referer");

        // If the URL exist redirect back to it.
        if (referer != null) return "redirect:" + referer;

        // Else return to the tables page.
        return "redirect:tables.html";
    }
}
