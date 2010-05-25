package org.youthnet.debug.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * User: Karl
 * Date: 22-May-2010
 */
public interface JdbcDao {

    public void executeQuery(String query);

    public List<Map<String, Object>> queryForRows(String query);

    public List<String> getTableNames();
}
