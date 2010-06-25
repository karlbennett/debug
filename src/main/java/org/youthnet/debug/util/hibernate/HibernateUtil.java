package org.youthnet.debug.util.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.mapping.*;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * User: karl
 * Date: 19-May-2010
 */
public class HibernateUtil {

    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    private static String[] schema = null;
    private static Map<String, Class> tableToClassMap = new HashMap<String, Class>();
    private static Map<String, String> classToTableMap = new HashMap<String, String>();
    private static Map<String, String> columnToTableMap = new HashMap<String, String>();

    private HibernateUtil() {
    }

    public static String[] getSchema(AnnotationSessionFactoryBean sessionFactory) {
        if (schema != null) return schema;

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

        schema = hibernateConfiguration.generateSchemaCreationScript(dialect);
        return schema;
    }

    public static void logSchema(AnnotationSessionFactoryBean sessionFactory) {
        for (String line : getSchema(sessionFactory)) {
            log.info(line);
        }
    }

    public static Class getClassForTableName(String tableName, AnnotationSessionFactoryBean sessionFactory) {
        Class tableClass = tableToClassMap.get(tableName);

        if (tableClass != null) return tableClass;

        try {
            Iterator mappingIterator = sessionFactory.getConfiguration().getClassMappings();
            PersistentClass persistentClass = null;
            String className = null;
            while (mappingIterator.hasNext()) {
                persistentClass = (PersistentClass) mappingIterator.next();
                if (persistentClass.getTable().getName().equals(tableName)) {
                    className = persistentClass.getClassName();
                    tableClass = Class.forName(className);
                    tableToClassMap.put(tableName, tableClass);
                    return tableClass;
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("Failed to create the domain class. " + e.getMessage());
            log.error(e.getStackTrace());
        }

        log.warn("Domain class not found for table: " + tableName);
        return null;
    }

    public static String getTableNameForClass(String classname, AnnotationSessionFactoryBean sessionFactory) {
        String tableName = classToTableMap.get(classname);

        if(tableName != null) return tableName;

        Iterator mappingIterator = sessionFactory.getConfiguration().getClassMappings();
        PersistentClass persistentClass = null;
        while (mappingIterator.hasNext()) {
            persistentClass = (PersistentClass) mappingIterator.next();
            if (persistentClass.getClassName().equals(classname)) {
                tableName = persistentClass.getTable().getName();
                classToTableMap.put(classname, tableName);
                return tableName;
            }
        }

        return null;
    }

    public static String getTableNameForClass(Class clazz, AnnotationSessionFactoryBean sessionFactory) {
        return getTableNameForClass(clazz.getName(), sessionFactory);   
    }

    public static Map<String, Object> createRowMap(Object row) {
        Map<String, Object> rowMap = new HashMap<String, Object>();
        String columnName = null;
        Object columnValue = null;
        for (Field field : row.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            javax.persistence.Column columnAnnotation = field.getAnnotation(javax.persistence.Column.class);
            if (columnAnnotation != null) {
                columnName = columnAnnotation.name();
                if (columnName == null || columnName.equals("")) {
                    columnName = field.getName();
                }
                try {
                    columnValue = field.get(row);
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
        for (Object row : rows) {
            rowList.add(createRowMap(row));
        }
        return rowList;
    }

    public static List<String> getDomainTableNames(AnnotationSessionFactoryBean sessionFactory) {
        Iterator mappingIterator = sessionFactory.getConfiguration().getTableMappings();
        List<String> domainTableNames = new ArrayList<String>();
        Table table = null;
        while (mappingIterator.hasNext()) {
            table = (Table) mappingIterator.next();
            domainTableNames.add(table.getName());
        }
        return domainTableNames;
    }

    public static String getTableClassNameForColumnReference(String columnName, AnnotationSessionFactoryBean sessionFactory) {
        String tableClassName = columnToTableMap.get(columnName);

        if(tableClassName != null) return tableClassName;

        log.info("Searching for table name");

        Iterator mappingIterator = sessionFactory.getConfiguration().getTableMappings();
        Iterator foreignKeyIterator = null;
        Iterator columnIterator = null;
        Table table = null;
        Column column = null;
        ForeignKey foreignKey = null;
        while (mappingIterator.hasNext()) {
            table = (Table) mappingIterator.next();
            foreignKeyIterator = table.getForeignKeyIterator();
            while (foreignKeyIterator.hasNext()) {
                foreignKey = (ForeignKey) foreignKeyIterator.next();
                tableClassName = foreignKey.getReferencedEntityName();
                columnIterator = foreignKey.getColumnIterator();
                while (columnIterator.hasNext()) {
                    column = (Column) columnIterator.next();
                    log.info("  -- Table name: " + tableClassName + " found column name: " + column.getName()
                            + " given column name: " + columnName);
                    if (column.getName().toLowerCase().equals(columnName.toLowerCase())) {
                        columnToTableMap.put(columnName, tableClassName);
                        return tableClassName;
                    }
                }
            }
        }

        return null;
    }
}
