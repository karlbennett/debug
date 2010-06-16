package org.youthnet.debug.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.services.DBServices;
import org.youthnet.debug.util.HibernateUtil;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: karl
 * Date: 16-Jun-2010
 */
@Component("dbServices")
public class DBServicesImpl implements DBServices {

    private static final Log log = LogFactory.getLog(DBServicesImpl.class);

    @Resource(name = "adminJdbcDaoImpl")
    private JdbcDao adminJdbcDao;

    @Resource(name = "coreJdbcDaoImpl")
    private JdbcDao coreJdbcDao;

    @Resource(name = "&adminSessionFactory")
    AnnotationSessionFactoryBean adminSessionFactory;

    @Resource(name = "&coreSessionFactory")
    AnnotationSessionFactoryBean coreSessionFactory;

    @Override
    public List<String> getSchemaNames() {
        List<String> schemaNames = adminJdbcDao.getSchemaNames();
        Collections.sort(schemaNames);
        return schemaNames;
    }

    @Override
    public List<String> getTableNames() {
        List<String> tableNames = adminJdbcDao.getTableNames(); // Get the admin table names.
        tableNames.addAll(coreJdbcDao.getTableNames()); // Add the core table names.
        Collections.sort(tableNames);
        return tableNames;
    }

    @Override
    public List<String> getColumnNames() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getTableRows(String tableName) {
        // Search for the requested table in the admin schema.
        List<Map<String, Object>> rows = adminJdbcDao.getTableRows(tableName);
        // If it's not there search for it in the core schema.
        if (rows == null || rows.size() == 0) {
            rows = coreJdbcDao.getTableRows(tableName);
            if (rows == null) log.warn("    -- Table " + tableName + " not found.");
        }
        
        return rows;
    }

    @Override
    public String getTableNameForColumn(String column) {
        String tableName = HibernateUtil.getTableNameForClass(HibernateUtil.getTableClassNameForColumnReference(
                column.toLowerCase(), adminSessionFactory), adminSessionFactory);
        if (tableName == null) {
            tableName = HibernateUtil.getTableNameForClass(HibernateUtil.getTableClassNameForColumnReference(
                    column.toLowerCase(), coreSessionFactory), coreSessionFactory);
        }

        return tableName;
    }
}
