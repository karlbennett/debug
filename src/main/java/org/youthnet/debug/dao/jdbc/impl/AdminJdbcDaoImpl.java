package org.youthnet.debug.dao.jdbc.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.youthnet.debug.dao.jdbc.JdbcDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: Karl
 * Date: 22-May-2010
 */
@Repository("adminJdbcDaoImpl")
public class AdminJdbcDaoImpl implements JdbcDao {

    @Resource(name = "adminJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @Override
    public void executeQuery(String query) {
        jdbcTemplate.execute(query);
    }

    @Override
    public List<Map<String, Object>> queryForRows(String query) {
        return jdbcTemplate.queryForList(query);
    }
}
