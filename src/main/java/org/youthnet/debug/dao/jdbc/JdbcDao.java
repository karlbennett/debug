package org.youthnet.debug.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.youthnet.debug.domain.common.UuidType;

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

    public List<Map <String, Object>> getTableRows(String tableName);

    public Map <String, Object> getRowById(UuidType id);

    public Map <String, Object> getRowById(String id);
}
