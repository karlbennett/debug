package org.youthnet.debug;

import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.youthnet.debug.util.HibernateUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * User: karl
 * Date: 19-May-2010
 */
@Component("debugClient")
public class DebugClient {

    @Resource(name = "&adminSessionFactory")
    AnnotationSessionFactoryBean sessionFactory;

    @PostConstruct
    public void init() {
        HibernateUtil.logSchema(sessionFactory);
    }
}
