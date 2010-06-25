package org.youthnet.debug;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.youthnet.debug.util.hibernate.HibernateUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * User: karl
 * Date: 19-May-2010
 */
@Component("debugClient")
public class DebugClient implements ApplicationContextAware {

    private static final Log log = LogFactory.getLog(DebugClient.class);

    @Resource(name = "&adminSessionFactory")
    private AnnotationSessionFactoryBean sessionFactory;

    private static ApplicationContext CONTEXT;

    @PostConstruct
    public void init() {
        HibernateUtil.logSchema(sessionFactory);
        HibernateUtil.getClassForTableName("collective", sessionFactory);
        log.info("Context type: " + CONTEXT.getClass().getName());
        for (String beanName : CONTEXT.getBeanDefinitionNames()) {
            log.info("Bean name: " + beanName);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }
}
