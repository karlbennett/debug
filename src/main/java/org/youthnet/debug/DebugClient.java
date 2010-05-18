package org.youthnet.debug;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import java.lang.reflect.Constructor;

/**
 * User: karl
 * Date: 18-May-2010
 */
public class DebugClient {
    private static AbstractApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AnnotationSessionFactoryBean sessionFactory = context.getBean("&adminSessionFactory", AnnotationSessionFactoryBean.class);

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
            System.out.println("Class Not Found: " + hibernateDialect + " Exception Message: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.out.println("Method Not Found: " + hibernateDialect + " Exception Message: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Initialisation Failed: " + hibernateDialect + " Exception Message: " + e.getMessage());
        }

        String[] lines = hibernateConfiguration.generateSchemaCreationScript(dialect);

        for (String line : lines) {
            System.out.println(line);
        }
    }
}
