package org.youthnet.debug;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    @Resource(name = "&adminSessionFactory")
    AnnotationSessionFactoryBean sessionFactory;

    @PostConstruct
    public void init() {
        HibernateUtil.logSchema(sessionFactory);
        HibernateUtil.getClassForTableName(sessionFactory, "collective");
    }
}
