package org.youthnet.debug.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.mapping.PersistentClass;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.persistence.Column;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * User: karl
 * Date: 19-May-2010
 */
public class HibernateUtil {

    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    private HibernateUtil() {
    }

    public static String[] getSchema(AnnotationSessionFactoryBean sessionFactory) {
        Configuration hibernateConfiguration = sessionFactory.getConfiguration();
        String hibernateDialect = hibernateConfiguration.getProperty("hibernate.dialect");

        Dialect dialect = null;
        try {
            Class[] classParm = null;
            Object[] objectParm = null;
            Class cl = Class.forName(hibernateDialect);
            Constructor co = cl.getConstructor(classParm);
            dialect = (Dialect) co.newInstance(objectParm);
        } catch (ClassNotFoundException e) {
            log.error("Class Not Found: " + hibernateDialect + " Exception Message: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            log.error("Method Not Found: " + hibernateDialect + " Exception Message: " + e.getMessage());
        } catch (Exception e) {
            log.error("Initialisation Failed: " + hibernateDialect + " Exception Message: " + e.getMessage());
        }

        return hibernateConfiguration.generateSchemaCreationScript(dialect);
    }

    public static void logSchema(AnnotationSessionFactoryBean sessionFactory) {
        for (String line : getSchema(sessionFactory)) {
            log.info(line);
        }
    }

    public static Class getClassForTableName(String tableName, AnnotationSessionFactoryBean sessionFactory) {

        Iterator mappingIterator = sessionFactory.getConfiguration().getClassMappings();
        PersistentClass rootClass = null;
        while (mappingIterator.hasNext()) {
            rootClass = (PersistentClass) mappingIterator.next();
            log.debug("Checking mapped table " + rootClass.getTable().getName() + " against table name " + tableName);
            if (rootClass.getTable().getName().equals(tableName)) {
                try {
                    log.debug("Class " + rootClass.getClassName() + " found for table: " + tableName);
                    return Class.forName(rootClass.getClassName());
                } catch (ClassNotFoundException e) {
                    log.error("Failed to create the domain class. " + e.getMessage());
                    log.error(e.getStackTrace());
                }
            }
        }

        log.warn("Domain class not found for table: " + tableName);
        return null;
    }

    public static Map<String, Object> createRowMap(Object row) {
        Map<String, Object> rowMap = new HashMap<String, Object>();
        String columnName = null;
        Object columnValue = null;
        for (Field field : row.getClass().getDeclaredFields()) {
            log.debug("Field: " + field.getName());
            field.setAccessible(true);
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                log.debug(" -- Found column.");
                columnName = columnAnnotation.name();
                if (columnName == null || columnName.equals("")) {
                    columnName = field.getName();
                }
                try {
                    columnValue = field.get(row);
                    log.debug(" -- Column name: " + columnName + " value: " + columnValue);
                    rowMap.put(columnName, columnValue);
                } catch (IllegalAccessException e) {
                    log.error("Unable to access field: " + e.getMessage());
                    log.error(e.getStackTrace());
                }
            }
        }
        return rowMap;
    }

    public static List<Map<String, Object>> createRowList(List rows) {
        List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
        for(Object row : rows) {
            rowList.add(createRowMap(row));
        }
        return rowList;
    }

    public static List<String> getDomainTableNames(AnnotationSessionFactoryBean sessionFactory) {
        Iterator mappingIterator = sessionFactory.getConfiguration().getClassMappings();
        List<String> domainTableNames = new ArrayList<String>();
        while(mappingIterator.hasNext()) {
            PersistentClass persistentClass = (PersistentClass)mappingIterator.next();
            domainTableNames.add(persistentClass.getTable().getName());
        }
        return domainTableNames;
    }
}
