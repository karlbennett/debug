package org.youthnet.debug.dao.jdbc.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.youthnet.debug.dao.jdbc.AbstractJdbcDao;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;
import org.youthnet.debug.util.SqlSyntaxUtil;
import org.youthnet.debug.util.UuidConverter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Karl
 * Date: 22-May-2010
 */
@Repository("adminJdbcDao")
public class AdminJdbcDaoImpl extends AbstractJdbcDao {

    private static final Log log = LogFactory.getLog(AdminJdbcDaoImpl.class);

    @Resource(name = "adminJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        setJdbcTemplate(jdbcTemplate);
    }
}
