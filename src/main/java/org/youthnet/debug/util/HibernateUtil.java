package org.youthnet.debug.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.mapping.RootClass;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.util.Iterator;

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

    public static Class getClassForTableName(AnnotationSessionFactoryBean sessionFactory, String tableName) {

        Iterator mappingIterator = sessionFactory.getConfiguration().getClassMappings();
        RootClass rootClass = null;
        while (mappingIterator.hasNext()) {
            rootClass = (RootClass) mappingIterator.next();
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
}
