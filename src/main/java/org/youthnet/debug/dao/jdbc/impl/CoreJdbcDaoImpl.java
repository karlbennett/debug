package org.youthnet.debug.dao.jdbc.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.youthnet.debug.dao.jdbc.AbstractJdbcDao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * User: Karl
 * Date: 22-May-2010
 */
@Repository("coreJdbcDao")
public class CoreJdbcDaoImpl extends AbstractJdbcDao {

    private static final Log log = LogFactory.getLog(CoreJdbcDaoImpl.class);

    @Resource(name = "coreJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        setJdbcTemplate(jdbcTemplate);
    }
}