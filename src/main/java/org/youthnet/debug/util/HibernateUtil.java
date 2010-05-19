package org.youthnet.debug.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;

/**
 * User: karl
 * Date: 19-May-2010
 */
public class HibernateUtil {

    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    private HibernateUtil() {}

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
}
