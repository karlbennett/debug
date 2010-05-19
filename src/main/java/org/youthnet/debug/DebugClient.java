package org.youthnet.debug;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.youthnet.debug.util.HibernateUtil;

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

        HibernateUtil.logSchema(sessionFactory);
    }
}
